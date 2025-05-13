package com.example.fab083.api;

import com.example.fab083.dtos.DtoValidarNroOrdenInput;

import java.util.List;

public class ApiResponseValidarNroOrden {
    private boolean success;
    public String message;
    public String errorMessage;
    public List<DtoValidarNroOrdenInput> result;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
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

    public List<DtoValidarNroOrdenInput> getResult() {
        return result;
    }

    public void setResult(List<DtoValidarNroOrdenInput> result) {
        this.result = result;
    }
}
