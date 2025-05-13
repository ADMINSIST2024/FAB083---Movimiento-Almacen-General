package com.example.fab083.dtos;

public class DtoObtenerDatosEtiqueta {

    public  String CodigoEtiqueta;
    public  String codigoArticulo;
    public  String color;
    public  String lote;
    public  String um;
    public  String codigoExistencia;
    public  String umreal;
    public  String cod_prov;
    public  String secuencia;
    public  int stock_cantidad;
    public double stock_peso;
    public  int salida_cantidad;
    public  double salida_peso;
    private String desMaq;

    public String getDesMaq() {
        return desMaq;
    }

    public void setDesMaq(String desMaq) {
        this.desMaq = desMaq;
    }


    public int getStock_cantidad() {
        return stock_cantidad;
    }

    public void setStock_cantidad(int stock_cantidad) {
        this.stock_cantidad = stock_cantidad;
    }

    public double getStock_peso() {
        return stock_peso;
    }

    public void setStock_peso(double stock_peso) {
        this.stock_peso = stock_peso;
    }

    public double getSalida_peso() {
        return salida_peso;
    }

    public void setSalida_peso(double salida_peso) {
        this.salida_peso = salida_peso;
    }
    public DtoObtenerDatosEtiqueta() {
    }

    public String getSecuencia() {
        return secuencia;
    }

    public void setSecuencia(String secuencia) {
        this.secuencia = secuencia;
    }

    public String getCodigoEtiqueta() {
        return CodigoEtiqueta;
    }

    public void setCodigoEtiqueta(String codigoEtiqueta) {
        CodigoEtiqueta = codigoEtiqueta;
    }

    public String getCodigoArticulo() {
        return codigoArticulo;
    }

    public void setCodigoArticulo(String codigoArticulo) {
        this.codigoArticulo = codigoArticulo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

    public int getSalida_cantidad() {
        return salida_cantidad;
    }

    public void setSalida_cantidad(int salida_cantidad) {
        this.salida_cantidad = salida_cantidad;
    }

    public String getUm() {
        return um;
    }

    public void setUm(String um) {
        this.um = um;
    }

    public String getCodigoExistencia() {
        return codigoExistencia;
    }

    public void setCodigoExistencia(String codigoExistencia) {
        this.codigoExistencia = codigoExistencia;
    }

    public String getUmreal() {
        return umreal;
    }

    public void setUmreal(String umreal) {
        this.umreal = umreal;
    }

    public String getCod_prov() {
        return cod_prov;
    }

    public void setCod_prov(String cod_prov) {
        this.cod_prov = cod_prov;
    }
}
