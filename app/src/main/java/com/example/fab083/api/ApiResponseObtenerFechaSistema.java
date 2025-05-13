package com.example.fab083.api;

import com.example.fab083.dtos.DtoObtenerFechaSistemaAyerInput;
import com.example.fab083.dtos.DtoObtenerFechaSistemaInput;

public class ApiResponseObtenerFechaSistema {

    public String success;
    public String message;
    public String errorMessage;
    public DtoObtenerFechaSistemaInput result;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public DtoObtenerFechaSistemaInput getResult() {
        return result;
    }

    public void setResult(DtoObtenerFechaSistemaInput result) {
        this.result = result;
    }
}
