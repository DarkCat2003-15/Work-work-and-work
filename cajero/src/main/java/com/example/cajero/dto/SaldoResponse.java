package com.example.cajero.dto;

import java.math.BigDecimal;

public class SaldoResponse {
    private Long cuentaId;
    private String numeroCuenta;
    private BigDecimal saldo;

    public SaldoResponse() {
    }

    public SaldoResponse(Long cuentaId, String numeroCuenta, BigDecimal saldo) {
        this.cuentaId = cuentaId;
        this.numeroCuenta = numeroCuenta;
        this.saldo = saldo;
    }

    public Long getCuentaId() {
        return cuentaId;
    }

    public void setCuentaId(Long cuentaId) {
        this.cuentaId = cuentaId;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }
}
