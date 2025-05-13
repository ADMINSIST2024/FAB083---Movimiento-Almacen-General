package com.example.fab083.api;

import com.example.fab083.dtos.DtoAlmacenXCodigoInput;
import com.example.fab083.dtos.DtoValidarAlamcenXCcostoInput;
import com.example.fab083.dtos.DtoValidarAlamcenXCcostoOutput;

import java.util.List;

public class ApiResponseValidarAlamcenXCcosto {
    private boolean success;
    private String errorMessage;
    private List<DtoValidarAlamcenXCcostoInput> result;
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

    public List<DtoValidarAlamcenXCcostoInput> getResult() {
        return result;
    }

    public void setResult(List<DtoValidarAlamcenXCcostoInput> result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
