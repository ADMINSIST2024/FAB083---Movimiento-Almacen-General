package com.example.fab083.api;

import com.example.fab083.dtos.DtoObtenerCentroCostosInput;


import java.util.List;

public class ApiResponseCentroCosto {
    private boolean success;
    private List<DtoObtenerCentroCostosInput> result;
    private String errorMessage;
    private String message;


    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<DtoObtenerCentroCostosInput> getResult() {
        return result;
    }

    public void setResult(List<DtoObtenerCentroCostosInput> result) {
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
