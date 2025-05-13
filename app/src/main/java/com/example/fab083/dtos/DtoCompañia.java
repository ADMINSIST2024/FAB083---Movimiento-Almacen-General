package com.example.fab083.dtos;

public class DtoCompañia {
    public int codCia;
    public String desCia;

    public DtoCompañia(int codCia, String desCia) {
        this.codCia = codCia;
        this.desCia = desCia;
    }

    // Getters y setters

    public int getCodCia() {
        return codCia;
    }

    public void setCodCia(int codCia) {
        this.codCia = codCia;
    }

    public String getDesCia() {
        return desCia;
    }

    public void setDesCia(String desCia) {
        this.desCia = desCia;
    }

    @Override
    public String toString() {
        return desCia; // Devuelve la descripción de la compañía
    }
}
