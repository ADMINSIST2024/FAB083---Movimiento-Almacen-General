package com.example.fab083.dtos;

public class DtoObtenerCentroCostosXAlmacenInput {
    public int codCentroCosto;
    public String desCentroCosto;

    public int getCodCentroCosto() {
        return codCentroCosto;
    }

    public void setCodCentroCosto(int codCentroCosto) {
        this.codCentroCosto = codCentroCosto;
    }

    public String getDesCentroCosto() {
        return desCentroCosto;
    }

    public void setDesCentroCosto(String desCentroCosto) {
        this.desCentroCosto = desCentroCosto;
    }
    @Override
    public String toString() {return desCentroCosto;}
}
