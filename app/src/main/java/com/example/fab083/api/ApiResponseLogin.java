package com.example.fab083.api;

import com.example.fab083.dtos.DtoObtenerDatosEtiquetaInput;
import com.example.fab083.dtos.DtoObtenerDatosLogin;

import java.util.List;

public class ApiResponseLogin {
    private boolean success;
    private DtoObtenerDatosLogin result;
    private String errorMessage;
    private String message;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }


    public DtoObtenerDatosLogin getResult() {
        return result;
    }

    public void setResult(DtoObtenerDatosLogin result) {
        this.result = result;
    }

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
