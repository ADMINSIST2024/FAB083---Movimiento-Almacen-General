package com.example.fab083.dtos;

public class DtoValidarNroOrdenInput {

    public String anio ;
    public String tipoMovimiento ;
    public String codigoProceso;
    public String descripcionProceso;
    public String nroCarga;
    public String  estadoOrden;
    public String descripcionArticulo;
    public String list_CodExis;

    public String getList_CodExis() {
        return list_CodExis;
    }

    public void setList_CodExis(String list_CodExis) {
        list_CodExis = list_CodExis;
    }
    public String getDescripcionArticulo() {
        return descripcionArticulo;
    }

    public void setDescripcionArticulo(String descripcionArticulo) {
        this.descripcionArticulo = descripcionArticulo;
    }

    public String getEstadoOrden() {
        return estadoOrden;
    }

    public void setEstadoOrden(String estadoOrden) {
        this.estadoOrden = estadoOrden;
    }

    public String getNroCarga() {
        return nroCarga;
    }

    public void setNroCarga(String nroCarga) {
        this.nroCarga = nroCarga;
    }

    public String getAnio() {
        return anio;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }

    public String getTipoMovimiento() {
        return tipoMovimiento;
    }

    public void setTipoMovimiento(String tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }

    public String getCodigoProceso() {
        return codigoProceso;
    }

    public void setCodigoProceso(String codigoProceso) {
        this.codigoProceso = codigoProceso;
    }

    public String getDescripcionProceso() {
        return descripcionProceso;
    }

    public void setDescripcionProceso(String descripcionProceso) {
        this.descripcionProceso = descripcionProceso;
    }
}
