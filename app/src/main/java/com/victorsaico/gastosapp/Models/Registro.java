package com.victorsaico.gastosapp.Models;

/**
 * Created by JARVIS on 11/09/2017.
 */

public class Registro {
    private String tipo;
    private String cuenta;
    private Double monto;

    public Registro(String tipo,String cuenta, Double monto) {
        this.tipo = tipo;
        this.monto = monto;
        this.cuenta = cuenta;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public String getCuenta() {
        return cuenta;
    }

    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }
}
