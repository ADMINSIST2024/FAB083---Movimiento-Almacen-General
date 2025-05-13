package com.example.fab083.api;

import com.example.fab083.dtos.DtoObtenerFechaSistemaAyerInput;

public class ApiResponseObtenerFechaSistemaAyer {
    public String success;
    public String message;
    public String errorMessage;
    public DtoObtenerFechaSistemaAyerInput result;

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

    public DtoObtenerFechaSistemaAyerInput getResult() {
        return result;
    }

    public void setResult(DtoObtenerFechaSistemaAyerInput result) {
        this.result = result;
    }
}
