package com.example.fab083.api;

import com.example.fab083.dtos.DtoValidarCargaInput;

import java.util.List;

public class ApiResponseValidarEstadoConcecutivo {
    private boolean success;
    public String message;
    public String errorMessage;
    public String result;

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

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
