package com.example.fab083.api;

import com.example.fab083.dtos.DtoAlmacen;
import com.example.fab083.dtos.DtoBloquearRegistroNullInput;
import com.example.fab083.dtos.DtoObtenerDatosEtiquetaInput;
import com.example.fab083.dtos.DtoObtenerHoraSistemaInput;

import java.util.List;

public class ApiResponseGeneral {
    public String success;
    public String errorMessage;
    public String result;
    public String isSuccessful;
    public int resultBloqueo;

    public DtoObtenerHoraSistemaInput resultHoraSistema;

    public DtoObtenerHoraSistemaInput getResultHoraSistema() {
        return resultHoraSistema;
    }

    public void setResultHoraSistema(DtoObtenerHoraSistemaInput resultHoraSistema) {
        this.resultHoraSistema = resultHoraSistema;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getIsSuccessful() {
        return isSuccessful;
    }

    public void setIsSuccessful(String isSuccessful) {
        this.isSuccessful = isSuccessful;
    }

    public int getResultBloqueo() {
        return resultBloqueo;
    }

    public void setResultBloqueo(int resultBloqueo) {
        this.resultBloqueo = resultBloqueo;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    private String message;


    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
