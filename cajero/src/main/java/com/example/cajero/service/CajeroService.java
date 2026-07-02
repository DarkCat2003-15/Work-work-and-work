package com.example.cajero.service;

import com.example.cajero.dto.EstadisticasResponse;
import com.example.cajero.dto.SaldoResponse;
import com.example.cajero.entity.Cajero;
import com.example.cajero.entity.Cliente;
import com.example.cajero.entity.Cuenta;
import com.example.cajero.entity.Transaccion;
import com.example.cajero.repository.CajeroRepository;
import com.example.cajero.repository.ClienteRepository;
import com.example.cajero.repository.CuentaRepository;
import com.example.cajero.repository.TransaccionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class CajeroService {

    private final ClienteRepository clienteRepository;
    private final CuentaRepository cuentaRepository;
    private final CajeroRepository cajeroRepository;
    private final TransaccionRepository transaccionRepository;

    public CajeroService(ClienteRepository clienteRepository,
                         CuentaRepository cuentaRepository,
                         CajeroRepository cajeroRepository,
                         TransaccionRepository transaccionRepository) {
        this.clienteRepository = clienteRepository;
        this.cuentaRepository = cuentaRepository;
        this.cajeroRepository = cajeroRepository;
        this.transaccionRepository = transaccionRepository;
    }

    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }

    public List<Cuenta> listarCuentasPorCliente(Long clienteId) {
        return cuentaRepository.findByClienteId(clienteId);
    }

    public List<Cajero> listarCajeros() {
        return cajeroRepository.findAll();
    }

    @Transactional
    public Transaccion depositar(Long cuentaId, Long cajeroId, BigDecimal monto) {
        Cuenta cuenta = buscarCuenta(cuentaId);
        Cajero cajero = buscarCajero(cajeroId);

        BigDecimal saldoAnterior = cuenta.getSaldo();
        BigDecimal saldoActual = saldoAnterior.add(monto);

        cuenta.setSaldo(formatearDinero(saldoActual));
        cuentaRepository.save(cuenta);

        Transaccion transaccion = new Transaccion();
        transaccion.setTipo("DEPOSITO");
        transaccion.setMonto(formatearDinero(monto));
        transaccion.setFecha(LocalDateTime.now());
        transaccion.setSaldoAnterior(formatearDinero(saldoAnterior));
        transaccion.setSaldoActual(formatearDinero(saldoActual));
        transaccion.setCuenta(cuenta);
        transaccion.setCajero(cajero);

        return transaccionRepository.save(transaccion);
    }

    @Transactional
    public Transaccion retirar(Long cuentaId, Long cajeroId, BigDecimal monto) {
        Cuenta cuenta = buscarCuenta(cuentaId);
        Cajero cajero = buscarCajero(cajeroId);

        BigDecimal saldoAnterior = cuenta.getSaldo();

        if (saldoAnterior.compareTo(monto) < 0) {
            throw new IllegalArgumentException("Saldo insuficiente para realizar el retiro");
        }

        BigDecimal saldoActual = saldoAnterior.subtract(monto);

        cuenta.setSaldo(formatearDinero(saldoActual));
        cuentaRepository.save(cuenta);

        Transaccion transaccion = new Transaccion();
        transaccion.setTipo("RETIRO");
        transaccion.setMonto(formatearDinero(monto));
        transaccion.setFecha(LocalDateTime.now());
        transaccion.setSaldoAnterior(formatearDinero(saldoAnterior));
        transaccion.setSaldoActual(formatearDinero(saldoActual));
        transaccion.setCuenta(cuenta);
        transaccion.setCajero(cajero);

        return transaccionRepository.save(transaccion);
    }

    @Transactional
    public SaldoResponse consultarSaldo(Long cuentaId, Long cajeroId) {
        Cuenta cuenta = buscarCuenta(cuentaId);
        Cajero cajero = buscarCajero(cajeroId);

        Transaccion transaccion = new Transaccion();
        transaccion.setTipo("CONSULTA");
        transaccion.setMonto(BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP));
        transaccion.setFecha(LocalDateTime.now());
        transaccion.setSaldoAnterior(formatearDinero(cuenta.getSaldo()));
        transaccion.setSaldoActual(formatearDinero(cuenta.getSaldo()));
        transaccion.setCuenta(cuenta);
        transaccion.setCajero(cajero);
        transaccionRepository.save(transaccion);

        return new SaldoResponse(cuenta.getId(), cuenta.getNumeroCuenta(), formatearDinero(cuenta.getSaldo()));
    }

    public List<Transaccion> listarTransacciones() {
        return transaccionRepository.findAllByOrderByFechaDesc();
    }

    public List<Transaccion> listarTransaccionesPorCuenta(Long cuentaId) {
        return transaccionRepository.findByCuentaIdOrderByFechaDesc(cuentaId);
    }

    public EstadisticasResponse obtenerEstadisticas() {
        BigDecimal promedioDepositos = convertirPromedio(transaccionRepository.promedioPorTipo("DEPOSITO"));
        BigDecimal promedioRetiros = convertirPromedio(transaccionRepository.promedioPorTipo("RETIRO"));
        BigDecimal promedioConsultas = convertirPromedio(transaccionRepository.promedioPorTipo("CONSULTA"));

        return new EstadisticasResponse(promedioDepositos, promedioRetiros, promedioConsultas);
    }

    public EstadisticasResponse obtenerEstadisticasPorMes(int mes, int year) {
        if (mes < 1 || mes > 12) {
            throw new IllegalArgumentException("El mes debe estar entre 1 y 12");
        }

        BigDecimal promedioDepositos = convertirPromedio(transaccionRepository.promedioPorTipoMesYear("DEPOSITO", mes, year));
        BigDecimal promedioRetiros = convertirPromedio(transaccionRepository.promedioPorTipoMesYear("RETIRO", mes, year));
        BigDecimal promedioConsultas = convertirPromedio(transaccionRepository.promedioPorTipoMesYear("CONSULTA", mes, year));

        return new EstadisticasResponse(promedioDepositos, promedioRetiros, promedioConsultas);
    }


    private Cuenta buscarCuenta(Long cuentaId) {
        return cuentaRepository.findById(cuentaId)
                .orElseThrow(() -> new IllegalArgumentException("Cuenta no encontrada"));
    }

    private Cajero buscarCajero(Long cajeroId) {
        return cajeroRepository.findById(cajeroId)
                .orElseThrow(() -> new IllegalArgumentException("Cajero no encontrado"));
    }

    private BigDecimal formatearDinero(BigDecimal valor) {
        return valor.setScale(2, RoundingMode.HALF_UP);
    }

    private BigDecimal convertirPromedio(Double valor) {
        if (valor == null) {
            return BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
        }

        return BigDecimal.valueOf(valor).setScale(2, RoundingMode.HALF_UP);
    }
}
