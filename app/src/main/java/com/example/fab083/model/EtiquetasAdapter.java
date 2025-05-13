package com.example.fab083.model;

import com.example.fab083.dtos.DtoObtenerDatosEtiquetaInput;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;

import java.lang.reflect.Type;
import java.util.List;

public class EtiquetasAdapter {
    public  List<DtoObtenerDatosEtiquetaInput> convertJsonArray(JSONArray jsonArray){
        Gson gson = new Gson();
        Type listType = new TypeToken<List<DtoObtenerDatosEtiquetaInput>>(){}.getType();
        return gson.fromJson(jsonArray.toString(), listType);
    }
}
