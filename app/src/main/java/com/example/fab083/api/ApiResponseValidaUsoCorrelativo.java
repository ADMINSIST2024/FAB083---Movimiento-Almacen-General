package com.example.fab083.api;

import com.example.fab083.dtos.DtoObtenerDatosStockEmpaqueInput;
import com.example.fab083.dtos.DtoValidaUsoCorrelativoInput;

import java.util.List;

public class ApiResponseValidaUsoCorrelativo {

    public String success;
    public String message;
    public String errorMessage;
    public List<DtoValidaUsoCorrelativoInput> result;

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

    public List<DtoValidaUsoCorrelativoInput> getResult() {
        return result;
    }

    public void setResult(List<DtoValidaUsoCorrelativoInput> result) {
        this.result = result;
    }
}
