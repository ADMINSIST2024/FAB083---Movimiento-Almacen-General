package com.example.fab083.api;

import com.example.fab083.dtos.DtoValidarCargaInput;
import com.example.fab083.dtos.DtoValidarCargaOutput;
import com.example.fab083.dtos.DtoValidarNroOrdenInput;

import java.util.List;

public class ApiResponseValidarEstadoCarga {
    private boolean success;
    public String message;
    public String errorMessage;
    public List<DtoValidarCargaInput> result;

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

    public List<DtoValidarCargaInput> getResult() {
        return result;
    }

    public void setResult(List<DtoValidarCargaInput> result) {
        this.result = result;
    }
}
