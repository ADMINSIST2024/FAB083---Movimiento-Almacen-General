package com.example.fab083.dtos;

public class DtoProcesarlectura {
    public String etiqueta ;
    public String pcName ;
    public String fechaIngresada ;

    public String codcompania ;
    public String almacen;
    public Integer nroOrden;
    public String ListCodExis;


    public String getListCodExis() {
        return ListCodExis;
    }

    public void setListCodExis(String listCodExis) {
        ListCodExis = listCodExis;
    }
    public Integer getNroOrden() {
        return nroOrden;
    }

    public void setNroOrden(Integer nroOrden) {
        this.nroOrden = nroOrden;
    }

    public String getEtiqueta() {
        return etiqueta;
    }

    public void setEtiqueta(String etiqueta) {
        this.etiqueta = etiqueta;
    }

    public String getPcName() {
        return pcName;
    }

    public void setPcName(String pcName) {
        this.pcName = pcName;
    }

    public String getFechaIngresada() {
        return fechaIngresada;
    }

    public void setFechaIngresada(String fechaIngresada) {
        this.fechaIngresada = fechaIngresada;
    }

    public String getCodcompania() {
        return codcompania;
    }

    public void setCodcompania(String codcompania) {
        this.codcompania = codcompania;
    }

    public String getAlmacen() {
        return almacen;
    }

    public void setAlmacen(String almacen) {
        this.almacen = almacen;
    }
}
