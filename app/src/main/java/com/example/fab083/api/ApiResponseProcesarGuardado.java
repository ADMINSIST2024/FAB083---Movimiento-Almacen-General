package com.example.fab083.api;

import com.example.fab083.dtos.DtoObtenerDatosEtiqueta;

import java.util.List;

public class ApiResponseProcesarGuardado {
    public String success;
    public String message;
    public String errorMessage;
    public int result;

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

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


}
