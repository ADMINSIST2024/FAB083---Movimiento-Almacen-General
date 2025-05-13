package com.example.fab083.api;

import com.example.fab083.dtos.DtoObtenerCorelativoAlmacenInput;
import com.example.fab083.dtos.DtoObtenerDiasPermitidosInput;

import java.util.List;

public class ApiResponseObtenerCorelativoAlmacen {
    public String success;
    public String message;
    public String errorMessage;
    public List<DtoObtenerCorelativoAlmacenInput> result;

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

    public List<DtoObtenerCorelativoAlmacenInput> getResult() {
        return result;
    }

    public void setResult(List<DtoObtenerCorelativoAlmacenInput> result) {
        this.result = result;
    }
}
