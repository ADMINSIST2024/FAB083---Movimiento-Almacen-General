package com.example.fab083.ui.slideshow;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.fab083.api.ApiResponseMovimiento;
import com.example.fab083.api.ApiResponseValidarEstadoCarga;
import com.example.fab083.api.ApiResponseValidarEstadoConcecutivo;
import com.example.fab083.dtos.DtoMovimientoAlmacenOutput;
import com.example.fab083.dtos.DtoValidarConsecutivoOutput;
import com.example.fab083.interfaces.IAlmacen;
import com.example.fab083.interfaces.IAlta;
import com.example.fab083.interfaces.ICentroCosto;
import com.example.fab083.interfaces.ICompañia;
import com.example.fab083.interfaces.IEtiqueta;
import com.example.fab083.interfaces.IGeneral;
import com.example.fab083.interfaces.IMovimiento;
import com.example.fab083.interfaces.ITipoDocumento;
import com.example.fab083.interfaces.ITipoMovimiento;
import com.example.fab083.retrofit.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SlideshowViewModel extends ViewModel {

    private MutableLiveData<ApiResponseMovimiento> resultConsultaMovimiento = new MutableLiveData<>();
    private final MutableLiveData<Boolean> isLoading = new MutableLiveData<>(false);
    private final MutableLiveData<String> errorMessage = new MutableLiveData<>();
    private final IMovimiento Movimiento;
    public SlideshowViewModel() {
        // LOCAL
        //Retrofit retrofit = RetrofitClient.getClient("http://10.0.2.2:5234/");
        //Retrofit retrofit = RetrofitClient.getClient("http://192.168.166.194:8082/");
        //Retrofit retrofit = RetrofitClient.getClient("http://192.168.166.149:8096/");
        //PRODUCCION
        Retrofit retrofit = RetrofitClient.getClient("http://192.168.166.149:8097/");

        Movimiento = retrofit.create(IMovimiento.class);
    }
    public LiveData<ApiResponseMovimiento> getresultConsultaMovimiento()
    {
        return resultConsultaMovimiento;
    }

    public void ConsultarMovimiento(DtoMovimientoAlmacenOutput obj_dto) {
        isLoading.setValue(true);
        errorMessage.setValue(null);
        resultConsultaMovimiento.setValue(null);


        Call<ApiResponseMovimiento> call = Movimiento.ConsultaMovimiento(obj_dto);
        call.enqueue(new Callback<ApiResponseMovimiento>() {
            @Override
            public void onResponse(Call<ApiResponseMovimiento> call, Response<ApiResponseMovimiento> response) {
                isLoading.setValue(false);

                if (response.isSuccessful() && response.body() != null) {

                    ApiResponseMovimiento apiResponse = response.body();

                    resultConsultaMovimiento.setValue(apiResponse);


                } else {
                    String error = "Error en la respuesta ConsultarMovimiento: " + response.message();
                    errorMessage.setValue(error);
                    Log.e("API Response", error);
                }
            }

            @Override
            public void onFailure(Call<ApiResponseMovimiento> call, Throwable t) {
                isLoading.setValue(false);
                String error = "Error al realizar la solicitud: " + t.getMessage();
                errorMessage.setValue(error);
                Log.e("API Error", error, t);
            }
        });
    }



}