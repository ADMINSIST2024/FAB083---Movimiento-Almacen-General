package com.example.fab083.dtos;

public class DtoBloquearRegistroCorrelativoOutput {
    public int codCia;
    public int codAlg ;
    public String tmvcor;
    public String pcName ;

    public int getCodCia() {
        return codCia;
    }

    public void setCodCia(int codCia) {
        this.codCia = codCia;
    }

    public int getCodAlg() {
        return codAlg;
    }

    public void setCodAlg(int codAlg) {
        this.codAlg = codAlg;
    }

    public String getTmvcor() {
        return tmvcor;
    }

    public void setTmvcor(String tmvcor) {
        this.tmvcor = tmvcor;
    }

    public String getPcName() {
        return pcName;
    }

    public void setPcName(String pcName) {
        this.pcName = pcName;
    }
}
