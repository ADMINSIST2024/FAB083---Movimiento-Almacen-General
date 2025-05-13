package com.example.fab083.dtos;

public class DtoEtiquetas {
    private String codigo_etiqueta;
    private String codigo_articulo;
    private String color;
    private String lote;
    private String stock;
    private String salida;
    private String unidad_medida;
    private String codigoExistencia;
    private String umreal;
    private String cod_prov;
    private String cantidad;
    private int  secuencia;

    public DtoEtiquetas(String codigo_etiqueta, String codigo_articulo, String color, String lote, String stock, String salida, String unidad_medida, String codigoExistencia, String umreal, String cod_prov, String cantidad, int secuencia) {
        this.codigo_etiqueta = codigo_etiqueta;
        this.codigo_articulo = codigo_articulo;
        this.color = color;
        this.lote = lote;
        this.stock = stock;
        this.salida = salida;
        this.unidad_medida = unidad_medida;
        this.codigoExistencia = codigoExistencia;
        this.umreal = umreal;
        this.cod_prov = cod_prov;
        this.cantidad = cantidad;
        this.secuencia = secuencia;
    }

    public String getCodigo_etiqueta() {
        return codigo_etiqueta;
    }

    public void setCodigo_etiqueta(String codigo_etiqueta) {
        this.codigo_etiqueta = codigo_etiqueta;
    }

    public String getCodigo_articulo() {
        return codigo_articulo;
    }

    public void setCodigo_articulo(String codigo_articulo) {
        this.codigo_articulo = codigo_articulo;
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

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public String getSalida() {
        return salida;
    }

    public void setSalida(String salida) {
        this.salida = salida;
    }

    public String getUnidad_medida() {
        return unidad_medida;
    }

    public void setUnidad_medida(String unidad_medida) {
        this.unidad_medida = unidad_medida;
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

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public int getSecuencia() {
        return secuencia;
    }

    public void setSecuencia(int secuencia) {
        this.secuencia = secuencia;
    }
}
