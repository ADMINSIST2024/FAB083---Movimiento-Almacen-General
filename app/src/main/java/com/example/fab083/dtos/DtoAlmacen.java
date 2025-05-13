package com.example.fab083.dtos;

public class DtoAlmacen {
    private int codalg;
    private String desalg;

    public int getCodalg() {
        return codalg;
    }

    public void setCodalg(int codalg) {
        this.codalg = codalg;
    }

    public String getDesalg() {
        return desalg;
    }

    public void setDesalg(String desalg) {
        this.desalg = desalg;
    }


    @Override
    public String toString() {
        return desalg; // Devuelve la descripción de la compañía
    }

}

