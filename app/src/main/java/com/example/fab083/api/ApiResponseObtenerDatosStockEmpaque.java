package com.example.fab083.api;

import com.example.fab083.dtos.DtoObtenerDatosStockEmpaqueInput;
import com.example.fab083.dtos.DtoObtenerDiasPermitidosAyerInput;

import java.util.List;

public class ApiResponseObtenerDatosStockEmpaque {
    public String success;
    public String message;
    public String errorMessage;
    public List<DtoObtenerDatosStockEmpaqueInput> result;

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

    public List<DtoObtenerDatosStockEmpaqueInput> getResult() {
        return result;
    }

    public void setResult(List<DtoObtenerDatosStockEmpaqueInput> result) {
        this.result = result;
    }
}
