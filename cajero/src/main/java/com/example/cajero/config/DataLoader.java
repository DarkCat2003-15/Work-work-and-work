package com.example.cajero.config;

import com.example.cajero.entity.Cajero;
import com.example.cajero.entity.Cliente;
import com.example.cajero.entity.Cuenta;
import com.example.cajero.entity.User;
import com.example.cajero.repository.CajeroRepository;
import com.example.cajero.repository.ClienteRepository;
import com.example.cajero.repository.CuentaRepository;
import com.example.cajero.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner cargarDatos(UserRepository userRepository,
                                  ClienteRepository clienteRepository,
                                  CuentaRepository cuentaRepository,
                                  CajeroRepository cajeroRepository) {
        return args -> {
            if (userRepository.findByUsername("admin").isEmpty()) {
                User usuario = new User(null, "admin", "12345", "ADMIN");
                userRepository.save(usuario);
            }

            if (userRepository.findByUsername("developer").isEmpty()) {
                User usuario = new User(null, "developer", "1508", "DEVELOPER");
                userRepository.save(usuario);
            }


            if (clienteRepository.count() == 0) {
                Cliente cliente = new Cliente(null, "0102030405", "Juan", "Buenaño", "juan@email.com", "0996101033");
                clienteRepository.save(cliente);

                Cliente cliente2 = new Cliente(null, "2000082962", "Ivan", "Murillo", "IvanM@email.com", "0982018862");
                clienteRepository.save(cliente2);

                Cuenta cuentaAhorros = new Cuenta(null, "CTA-001", "AHORROS", new BigDecimal("500.00"), "ACTIVA", cliente);
                Cuenta cuentaCorriente = new Cuenta(null, "CTA-002", "CORRIENTE", new BigDecimal("250.00"), "ACTIVA", cliente);
                cuentaRepository.save(cuentaAhorros);
                cuentaRepository.save(cuentaCorriente);

                Cuenta cuentaAhorros2 = new Cuenta(null, "CTA-003", "AHORROS", new BigDecimal("67.00"), "ACTIVA", cliente);
                cuentaRepository.save(cuentaAhorros2);
            }


            if (cajeroRepository.count() == 0) {
                Cajero cajero = new Cajero(null, "Cajero Principal", "Sucursal Centro", "ACTIVO");
                cajeroRepository.save(cajero);

                Cajero cajero2 = new Cajero(null, "Cajero Secundario", "Sucursal Mall del SOl", "ACTIVO");
                cajeroRepository.save(cajero2);
            }

        };
    }
}
