package com.example.fab083.dtos;

import com.google.gson.annotations.SerializedName;

public class DtoTipoDocumento {



    public  String codTipDoc;


    public  String desTipDoc;

    public String getCodTipDoc() {
        return codTipDoc;
    }

    public void setCodTipDoc(String codTipDoc) {
        this.codTipDoc = codTipDoc;
    }

    public String getDesTipDoc() {
        return desTipDoc;
    }

    public void setDesTipDoc(String desTipDoc) {
        this.desTipDoc = desTipDoc;
    }

    @Override
    public String toString() {
        return desTipDoc; // Asegúrate de devolver el nombre del documento aquí
    }
}
