package com.example.fab083.dtos;

import java.util.List;

public class DtoProcesarGuardado {
    private List<DtoObtenerDatosEtiqueta> TotalEtiquetas;
    private int codcompania;
    private int codalg;
    private String tmvmag;
    private String nmvmag;
    private String codtmv;
    private String cencos;
    private int ademag;
    private String refere;
    private String ctdor1;
    private String anoor1;
    private String nroor1;
    private String cscor2;
    private String fecmag;
    private String ncrma2;
    private int almacen;
    private String fechaMovimiento;
    private String pcName;
    private List<DtoObtenerDatosStockEmpaqueInput> etiquetasStock;
    private String obtenerTodasEtiquetas;
    private String usuario;

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public List<DtoObtenerDatosEtiqueta> getTotalEtiquetas() {
        return TotalEtiquetas;
    }

    public void setTotalEtiquetas(List<DtoObtenerDatosEtiqueta> totalEtiquetas) {
        TotalEtiquetas = totalEtiquetas;
    }

    public int getCodcompania() {
        return codcompania;
    }

    public void setCodcompania(int codcompania) {
        this.codcompania = codcompania;
    }

    public int getCodalg() {
        return codalg;
    }

    public void setCodalg(int codalg) {
        this.codalg = codalg;
    }

    public String getTmvmag() {
        return tmvmag;
    }

    public void setTmvmag(String tmvmag) {
        this.tmvmag = tmvmag;
    }

    public String getNmvmag() {
        return nmvmag;
    }

    public void setNmvmag(String nmvmag) {
        this.nmvmag = nmvmag;
    }

    public String getCodtmv() {
        return codtmv;
    }

    public void setCodtmv(String codtmv) {
        this.codtmv = codtmv;
    }

    public String getCencos() {
        return cencos;
    }

    public void setCencos(String cencos) {
        this.cencos = cencos;
    }

    public int getAdemag() {
        return ademag;
    }

    public void setAdemag(int ademag) {
        this.ademag = ademag;
    }

    public String getRefere() {
        return refere;
    }

    public void setRefere(String refere) {
        this.refere = refere;
    }

    public String getCtdor1() {
        return ctdor1;
    }

    public void setCtdor1(String ctdor1) {
        this.ctdor1 = ctdor1;
    }

    public String getAnoor1() {
        return anoor1;
    }

    public void setAnoor1(String anoor1) {
        this.anoor1 = anoor1;
    }

    public String getNroor1() {
        return nroor1;
    }

    public void setNroor1(String nroor1) {
        this.nroor1 = nroor1;
    }

    public String getCscor2() {
        return cscor2;
    }

    public void setCscor2(String cscor2) {
        this.cscor2 = cscor2;
    }

    public String getFecmag() {
        return fecmag;
    }

    public void setFecmag(String fecmag) {
        this.fecmag = fecmag;
    }

    public String getNcrma2() {
        return ncrma2;
    }

    public void setNcrma2(String ncrma2) {
        this.ncrma2 = ncrma2;
    }

    public int getAlmacen() {
        return almacen;
    }

    public void setAlmacen(int almacen) {
        this.almacen = almacen;
    }

    public String getFechaMovimiento() {
        return fechaMovimiento;
    }

    public void setFechaMovimiento(String fechaMovimiento) {
        this.fechaMovimiento = fechaMovimiento;
    }

    public String getPcName() {
        return pcName;
    }

    public void setPcName(String pcName) {
        this.pcName = pcName;
    }

    public List<DtoObtenerDatosStockEmpaqueInput> getEtiquetasStock() {
        return etiquetasStock;
    }

    public void setEtiquetasStock(List<DtoObtenerDatosStockEmpaqueInput> etiquetasStock) {
        this.etiquetasStock = etiquetasStock;
    }

    public String getObtenerTodasEtiquetas() {
        return obtenerTodasEtiquetas;
    }

    public void setObtenerTodasEtiquetas(String obtenerTodasEtiquetas) {
        this.obtenerTodasEtiquetas = obtenerTodasEtiquetas;
    }
}
