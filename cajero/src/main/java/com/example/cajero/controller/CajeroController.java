package com.example.cajero.controller;

import com.example.cajero.dto.EstadisticasResponse;
import com.example.cajero.dto.OperacionRequest;
import com.example.cajero.dto.SaldoResponse;
import com.example.cajero.entity.Cajero;
import com.example.cajero.entity.Cliente;
import com.example.cajero.entity.Cuenta;
import com.example.cajero.entity.Transaccion;
import com.example.cajero.service.CajeroService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CajeroController {

    private final CajeroService service;

    public CajeroController(CajeroService service) {
        this.service = service;
    }

    @GetMapping("/clientes")
    public List<Cliente> listarClientes() {
        return service.listarClientes();
    }

    @GetMapping("/clientes/{clienteId}/cuentas")
    public List<Cuenta> listarCuentasPorCliente(@PathVariable Long clienteId) {
        return service.listarCuentasPorCliente(clienteId);
    }

    @GetMapping("/cajeros")
    public List<Cajero> listarCajeros() {
        return service.listarCajeros();
    }

    @PostMapping("/cajero/depositos")
    public Transaccion depositar(@Valid @RequestBody OperacionRequest request) {
        return service.depositar(request.getCuentaId(), request.getCajeroId(), request.getMonto());
    }

    @PostMapping("/cajero/retiros")
    public Transaccion retirar(@Valid @RequestBody OperacionRequest request) {
        return service.retirar(request.getCuentaId(), request.getCajeroId(), request.getMonto());
    }

    @GetMapping("/cajero/saldo")
    public SaldoResponse consultarSaldo(@RequestParam Long cuentaId, @RequestParam Long cajeroId) {
        return service.consultarSaldo(cuentaId, cajeroId);
    }

    @GetMapping("/cajero/transacciones")
    public List<Transaccion> listarTransacciones() {
        return service.listarTransacciones();
    }

    @GetMapping("/cuentas/{cuentaId}/transacciones")
    public List<Transaccion> listarTransaccionesPorCuenta(@PathVariable Long cuentaId) {
        return service.listarTransaccionesPorCuenta(cuentaId);
    }

    @GetMapping("/cajero/estadisticas")
    public EstadisticasResponse estadisticas() {
        return service.obtenerEstadisticas();
    }

    @GetMapping("/cajero/estadisticas/periodo")
    public EstadisticasResponse estadisticasPorPeriodo(@RequestParam int mes, @RequestParam int year) {
        return service.obtenerEstadisticasPorMes(mes, year);
    }
}
