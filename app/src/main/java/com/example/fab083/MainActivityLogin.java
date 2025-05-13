package com.example.fab083;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fab083.api.ApiResponseLogin;
import com.example.fab083.dtos.DtoLoginRequest;
import com.example.fab083.dtos.DtoObtenerDatosEtiquetaInput;
import com.example.fab083.dtos.DtoObtenerDatosLogin;
import com.example.fab083.interfaces.IEtiqueta;
import com.example.fab083.interfaces.ILogin;
import com.example.fab083.retrofit.RetrofitClient;
import com.example.fab083.ui.progres.ProgressDialogFragment;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.List;

import javax.net.ssl.SSLHandshakeException;

import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivityLogin extends AppCompatActivity {
    private ProgressDialogFragment progressDialog;

    //Local
    //Retrofit retrofit = RetrofitClient.getClient("http://10.0.2.2:5234/");

    //Desarrollo
    //Retrofit retrofit = RetrofitClient.getClient("http://192.168.166.194:8082/");
    //Retrofit retrofit = RetrofitClient.getClient("http://192.168.166.149:8096/");
    //PRODUCCION
    Retrofit retrofit = RetrofitClient.getClient("http://192.168.166.149:8097/");

    ILogin Ilogin = retrofit.create(ILogin.class);
    EditText usuario;
    EditText clave;
    ImageView button;
    CheckBox chkrecordar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_login);
        hideSystemUI();
        GifImageView gifImageView = findViewById(R.id.myGifImageView);

        usuario = findViewById(R.id.editTextUsuario);
        clave = findViewById(R.id.editTextClave);
        button = findViewById(R.id.btnlogin);
        chkrecordar = findViewById(R.id.chkrecordar);

        try {
            GifDrawable gifDrawable = new GifDrawable(getResources(), R.drawable.segurigif);
            gifImageView.setImageDrawable(gifDrawable);
        } catch (IOException e) {
            e.printStackTrace();
        }


        cargarPreferencias();
        mostrarClave();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    ProgressDialogFragment progressDialog = new ProgressDialogFragment();
                    progressDialog.show(getSupportFragmentManager(), "progressDialog");
                    String usuarioText = usuario.getText().toString();
                    String claveText = clave.getText().toString();
                    // Crear el objeto de solicitud
                    DtoLoginRequest loginRequest = new DtoLoginRequest(usuarioText, claveText);


                    if(!validarCampos(usuarioText, claveText)) {
                        Call<ApiResponseLogin> call = Ilogin.ValidarLogin(loginRequest);
                        call.enqueue(new Callback<ApiResponseLogin>() {
                            @Override
                            public void onResponse(Call<ApiResponseLogin> call, Response<ApiResponseLogin> response) {
                                if(response.isSuccessful() && response.body() != null) {
                                    // Aquí manejas la respuesta exitosa
                                    ApiResponseLogin loginResponse = response.body();
                                    if(loginResponse.isSuccess()) {
                                        DtoObtenerDatosLogin datos = loginResponse.getResult();

                                        String nombreUsuario = datos.nomUsu;
                                        // Guardar preferencias si el CheckBox está seleccionado
                                        if(chkrecordar.isChecked()) {
                                            guardarPreferencias(usuarioText, claveText);
                                        }else{
                                            eliminarPreferencias();  // Si no está seleccionado, elimina las preferencias
                                        }

                                        if(progressDialog != null) {
                                            progressDialog.dismiss();
                                        }
                                        Intent intent = new Intent(MainActivityLogin.this, MainActivityNavigation.class);
                                        intent.putExtra("nombreUsuario", nombreUsuario);
                                        startActivity(intent);
                                    }else{
                                        if(progressDialog != null) {
                                            progressDialog.dismiss();
                                        }
                                        mostrarDialog(loginResponse.getMessage());
                                    }
                                }else{
                                    if(progressDialog != null) {
                                        progressDialog.dismiss();
                                    }
                                    // Manejar error en la respuesta
                                    Toast.makeText(MainActivityLogin.this, "Error en la autenticación", Toast.LENGTH_SHORT).show();
                                }


                            }


                            @Override
                            public void onFailure(Call<ApiResponseLogin> call, Throwable t) {
                                if(progressDialog != null) {
                                    progressDialog.dismiss();
                                }

                                /*if(t instanceof SocketTimeoutException || t instanceof UnknownHostException){
                                    Toast.makeText(MainActivityLogin.this, "El servicio no está disponible. Inténtalo más tarde.", Toast.LENGTH_SHORT).show();
                                }else{
                                    // Manejar el fallo en la conexión o la solicitud
                                    Toast.makeText(MainActivityLogin.this, "Fallo de conexión: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                                    Log.e("API_ERROR", "Error procesando la respuesta: " + t.getMessage(), t);
                                }*/


                                if (t instanceof UnknownHostException) {
                                    Toast.makeText(MainActivityLogin.this, "No se pudo conectar con el servidor. Verifica tu conexión a Internet.", Toast.LENGTH_SHORT).show();
                                } else if (t instanceof SocketTimeoutException) {
                                    Toast.makeText(MainActivityLogin.this, "El servidor está tardando demasiado en responder. Inténtalo más tarde.", Toast.LENGTH_SHORT).show();
                                } else if (t instanceof ConnectException) {
                                    Toast.makeText(MainActivityLogin.this, "No se pudo establecer conexión con el servidor.", Toast.LENGTH_SHORT).show();
                                } else if (t instanceof SSLHandshakeException) {
                                    Toast.makeText(MainActivityLogin.this, "Error de seguridad al conectar con el servidor.", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(MainActivityLogin.this, "Error de conexión: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

                    }else{
                        if(progressDialog != null) {
                            progressDialog.dismiss();
                        }
                    }


                } catch (Exception e) {
                    if(progressDialog != null) {
                        progressDialog.dismiss();
                    }
                    // Capturar y manejar cualquier excepción que ocurra durante el procesamiento de la respuesta
                    Log.e("API_ERROR", "Error procesando la respuesta: " + e.getMessage(), e);
                    Toast.makeText(MainActivityLogin.this, "Error procesando la respuesta", Toast.LENGTH_SHORT).show();
                }


            }
        });

        try {
            PackageInfo packageInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            String versionName = packageInfo.versionName; //

            TextView versionTextView = findViewById(R.id.versionTextView);
            versionTextView.setText("Versión: " + versionName);

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

    }

    private void mostrarClave() {
        final boolean[] isPasswordVisible = {false};

        clave.setOnTouchListener((v, event) -> {
            if(event.getAction() == MotionEvent.ACTION_UP) {
                // Detecta si el usuario tocó el icono de ojo
                int drawableEnd = clave.getRight() - clave.getCompoundDrawables()[2].getBounds().width();
                if(event.getRawX() >= drawableEnd) {
                    if(isPasswordVisible[0]) {
                        clave.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                        clave.setCompoundDrawablesWithIntrinsicBounds(
                                clave.getCompoundDrawables()[0],  // drawableStart (clave)
                                null,                             // top
                                getResources().getDrawable(R.drawable.invisible),  // drawableEnd (ojo cerrado)
                                null);                           // bottom
                        isPasswordVisible[0] = false;
                    }else{
                        clave.setInputType(InputType.TYPE_CLASS_TEXT);
                        clave.setCompoundDrawablesWithIntrinsicBounds(
                                clave.getCompoundDrawables()[0],  // drawableStart (clave)
                                null,                             // top
                                getResources().getDrawable(R.drawable.visible),    // drawableEnd (ojo abierto)
                                null);                           // bottom
                        isPasswordVisible[0] = true;
                    }
                    // Coloca el cursor al final
                    clave.setSelection(clave.getText().length());
                    return true;
                }
            }
            return false;
        });

    }


    private void guardarPreferencias(String usuarioText, String claveText) {
        SharedPreferences preferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("usuario", usuarioText);
        editor.putString("clave", claveText);  // Puedes omitir la clave si no deseas almacenarla
        editor.putBoolean("recordar", true);
        editor.apply();  // No uses commit ya que es bloqueante
    }


    private void cargarPreferencias() {
        SharedPreferences preferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);
        String usuarioText = preferences.getString("usuario", "");
        String claveText = preferences.getString("clave", "");
        boolean recordar = preferences.getBoolean("recordar", false);

        // Rellenar los campos si recordar es true
        if(recordar) {
            usuario.setText(usuarioText);
            clave.setText(claveText);
            chkrecordar.setChecked(true);
        }
    }


    private void eliminarPreferencias() {
        SharedPreferences preferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();  // Borra todo el contenido
        editor.apply();
    }

    public boolean validarCampos(String usuarioText, String claveText) {
        boolean hayErrores = false;

        if(usuarioText == null || usuarioText.trim().isEmpty() || usuarioText.trim() == "") {
            mostrarDialog("El campo Usuario no debe estar vacío.");
            hayErrores = true;
        }else if(claveText == null || claveText.trim().isEmpty() || claveText.trim() == "") {
            mostrarDialog("El campo Clave no debe estar vacío.");
            hayErrores = true;
        }


        return hayErrores;
    }

    private void mostrarDialog(String mensaje) {
        // Inflar el diseño personalizado del diálogo
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.alert, null);

        // Referencias a los elementos del diálogo
        TextView dialogTitle = dialogView.findViewById(R.id.dialog_title);
        TextView dialogMessage = dialogView.findViewById(R.id.dialog_message);
        Button closeButton = dialogView.findViewById(R.id.btn_close);

        // Personalizar el contenido del diálogo (si es necesario)
        dialogTitle.setText("Alerta");
        dialogMessage.setText(mensaje);

        // Crear el diálogo
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setView(dialogView);

        // Crear y mostrar el diálogo
        final AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();

        // Acción para cerrar el diálogo al presionar el botón
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        // Ocultar la barra de estado cuando la actividad se reanuda
        hideSystemUI();
    }

    private void hideSystemUI() {
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_FULLSCREEN |
                        View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                        View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY |
                        View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                        View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION |
                        View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        );
    }


}