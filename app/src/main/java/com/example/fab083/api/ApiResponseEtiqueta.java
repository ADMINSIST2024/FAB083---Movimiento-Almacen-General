package com.example.fab083.api;


import com.example.fab083.dtos.DtoObtenerDatosEtiquetaInput;

import java.util.List;

public class ApiResponseEtiqueta {
    private boolean success;
    private List<DtoObtenerDatosEtiquetaInput> result;
    private String errorMessage;
    private String message;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<DtoObtenerDatosEtiquetaInput> getResult() {
        return result;
    }

    public void setResult(List<DtoObtenerDatosEtiquetaInput> result) {
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
