package com.example.fab083.api;

import com.example.fab083.dtos.DtoAlmacenXCodigoInput;

import java.util.List;

public class ApiResponseAlmacenXCodigo {

    private boolean success;
    private String errorMessage;
    private List<DtoAlmacenXCodigoInput> result;
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

    public List<DtoAlmacenXCodigoInput> getResult() {
        return result;
    }

    public void setResult(List<DtoAlmacenXCodigoInput> result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
