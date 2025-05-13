package com.example.fab083.dtos;

public class DtoAlmacenXCodigoInput {

    public int codigoAlmacen ;


    public String descripcionAlmacen ;

    public String requiereNroOrden;

    public String requiereCscOrden ;

    public String tipoDocumento ;


    public String requiereNroCarga ;

    public String centroCosto ;


    public String getCentroCosto() {
        return centroCosto;
    }

    public void setCentroCosto(String centroCosto) {
        this.centroCosto = centroCosto;
    }

    public int getCodigoAlmacen() {
        return codigoAlmacen;
    }

    public void setCodigoAlmacen(int codigoAlmacen) {
        this.codigoAlmacen = codigoAlmacen;
    }

    public String getDescripcionAlmacen() {
        return descripcionAlmacen;
    }

    public void setDescripcionAlmacen(String descripcionAlmacen) {
        this.descripcionAlmacen = descripcionAlmacen;
    }

    public String getRequiereNroOrden() {
        return requiereNroOrden;
    }

    public void setRequiereNroOrden(String requiereNroOrden) {
        this.requiereNroOrden = requiereNroOrden;
    }

    public String getRequiereCscOrden() {
        return requiereCscOrden;
    }

    public void setRequiereCscOrden(String requiereCscOrden) {
        this.requiereCscOrden = requiereCscOrden;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getRequiereNroCarga() {
        return requiereNroCarga;
    }

    public void setRequiereNroCarga(String requiereNroCarga) {
        this.requiereNroCarga = requiereNroCarga;
    }
}
