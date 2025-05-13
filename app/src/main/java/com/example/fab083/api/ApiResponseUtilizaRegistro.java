package com.example.fab083.api;

import com.example.fab083.dtos.DtoObtenerDatosEtiquetaInput;
import com.example.fab083.dtos.DtoutilizaRegistroInput;

import java.util.List;

public class ApiResponseUtilizaRegistro
{
    private boolean success;
    private DtoutilizaRegistroInput   result;
    private String errorMessage;
    private String message;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
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

    public DtoutilizaRegistroInput getResult() {
        return result;
    }

    public void setResult(DtoutilizaRegistroInput result) {
        this.result = result;
    }
}
