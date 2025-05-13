package com.example.fab083.dtos;

public class DtoObtenerTipoMovTransferenciaInput {
    public String codTipMov;
    public String desTipMov;

    public String getCodTipMov() {
        return codTipMov;
    }

    public void setCodTipMov(String codTipMov) {
        this.codTipMov = codTipMov;
    }

    public String getDesTipMov() {
        return desTipMov;
    }

    public void setDesTipMov(String desTipMov) {
        this.desTipMov = desTipMov;
    }
    @Override
    public String toString() {
        return desTipMov; // Devuelve la descripción de la compañía
    }
}
