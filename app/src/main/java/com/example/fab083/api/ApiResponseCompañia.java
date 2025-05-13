package com.example.fab083.api;

import com.example.fab083.dtos.DtoCompañia;

import java.util.List;

public class ApiResponseCompañia {
    private boolean success;
    private List<DtoCompañia> result;

    public boolean isSuccess() {
        return success;
    }
    public List<DtoCompañia> getResult() {
        return result;
    }


}
