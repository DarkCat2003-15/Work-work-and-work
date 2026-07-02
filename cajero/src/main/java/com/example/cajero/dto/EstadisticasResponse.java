package com.example.cajero.dto;

import java.math.BigDecimal;

public class EstadisticasResponse {
    private BigDecimal promedioDepositos;
    private BigDecimal promedioRetiros;
    private BigDecimal promedioConsultas;

    public EstadisticasResponse() {
    }

    public EstadisticasResponse(BigDecimal promedioDepositos, BigDecimal promedioRetiros, BigDecimal promedioConsultas) {
        this.promedioDepositos = promedioDepositos;
        this.promedioRetiros = promedioRetiros;
        this.promedioConsultas = promedioConsultas;
    }

    public BigDecimal getPromedioDepositos() {
        return promedioDepositos;
    }

    public void setPromedioDepositos(BigDecimal promedioDepositos) {
        this.promedioDepositos = promedioDepositos;
    }

    public BigDecimal getPromedioRetiros() {
        return promedioRetiros;
    }

    public void setPromedioRetiros(BigDecimal promedioRetiros) {
        this.promedioRetiros = promedioRetiros;
    }

    public BigDecimal getPromedioConsultas() {
        return promedioConsultas;
    }

    public void setPromedioConsultas(BigDecimal promedioConsultas) {
        this.promedioConsultas = promedioConsultas;
    }
}
