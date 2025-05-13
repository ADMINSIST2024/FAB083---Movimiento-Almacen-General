package com.example.fab083;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.FileProvider;

import android.app.AlertDialog;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fab083.api.ApiResponseVersion;
import com.example.fab083.interfaces.IVersion;
import com.example.fab083.retrofit.RetrofitClient;
import com.google.firebase.messaging.FirebaseMessaging;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    private static String CURRENT_VERSION = ""; // Versión actual de la app
    private long downloadID; // ID del archivo descargado

    //Local
    //Retrofit retrofit = RetrofitClient.getClient("http://10.0.2.2:5234/");
    //Retrofit retrofit = RetrofitClient.getClient("http://10.0.2.2:5234/");

    //Desarrollo
    //Retrofit retrofit = RetrofitClient.getClient("http://192.168.166.194:8082/");
    //Retrofit retrofit = RetrofitClient.getClient("http://192.168.166.149:8096/");
    //PRODUCCION
    Retrofit retrofit = RetrofitClient.getClient("http://192.168.166.149:8097/");

    IVersion iVersion = retrofit.create(IVersion.class);

    File apkFile = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            PackageInfo packageInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            CURRENT_VERSION = packageInfo.versionName;

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        hideSystemUI();
        Button button = findViewById(R.id.btniniciar);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Crear el Intent para llamar a SecondActivity
                Intent intent = new Intent(MainActivity.this, MainActivityLogin.class);
                startActivity(intent);
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

        // Configurar el idioma en español
        Locale locale = new Locale("es", "ES");
        Locale.setDefault(locale);
        getResources().getConfiguration().setLocale(locale);
        getResources().updateConfiguration(getResources().getConfiguration(), getResources().getDisplayMetrics());

        /* Alertas con Firebase - En prueba
        FirebaseMessaging.getInstance().getToken().addOnCompleteListener(task -> {
            if (!task.isSuccessful()) {
                Log.w("FCM", "No se pudo obtener el token", task.getException());
                return;
            }
            // Token del dispositivo
            String token = task.getResult();
            Log.d("FCM", "Token: " + token);
        });*/

        // Registrar un BroadcastReceiver para detectar cuando la descarga del apk termine
        //registerReceiver(onDownloadComplete, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));

        // Verificar si hay una nueva versión disponible
        //en revicion, hay escenario que da el error de evaluacion de paquete al instalar
        //verificarVersion();
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

    private void verificarVersion() {

        try {
            Call<ApiResponseVersion> call = iVersion.getVersion();
            call.enqueue(new Callback<ApiResponseVersion>() {
                @Override
                public void onResponse(Call<ApiResponseVersion> call, Response<ApiResponseVersion> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        String latestVersion = response.body().getVersion();
                        String apkUrl = response.body().getUrl();

                        if (!CURRENT_VERSION.equals(latestVersion)) {
                            mostrarDialogoDeActualizacion(apkUrl);
                        }
                    }
                }

                @Override
                public void onFailure(Call<ApiResponseVersion> call, Throwable t) {
                    Log.e("RetrofitError", "Error verificando la versión", t);
                }
            });
        }catch (Exception ex){ }
    }

    private void mostrarDialogoDeActualizacion(String apkUrl) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Actualización Disponible")
                .setMessage("Una nueva versión de la app está disponible. Debes actualizar para continuar.")
                .setCancelable(false)
                .setPositiveButton("Actualizar", (dialog, which) -> descargarAPK(apkUrl))
                .setNegativeButton("Salir", (dialog, which) -> finish());

        builder.show();
    }

    private void descargarAPK(String apkUrl) {
        apkFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "app-debug.apk");
        if (apkFile.exists()) {
            apkFile.delete(); // Eliminar APK anterior si existe
        }

        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(apkUrl));
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "app-debug.apk");
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setTitle("Descargando actualización...");
        request.setDescription("Espere mientras se descarga la nueva versión.");
        DownloadManager manager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
        downloadID = manager.enqueue(request);
    }

    // BroadcastReceiver para detectar cuando la descarga haya finalizado
    private final BroadcastReceiver onDownloadComplete = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            long id = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);

            // Verificar que el ID de la descarga es el esperado
            if (id == downloadID) {
                if (apkFile.exists()) {
                    Log.d("APK", "El archivo APK se descargó correctamente: " + apkFile.getAbsolutePath());
                } else {
                    Log.e("APK", "El archivo APK NO SE DESCARGÓ.");
                }

                Log.d("DownloadManager", "Descarga completada. Iniciando instalación...");
                instalarAPK(apkFile);// Llamar a la instalación solo si es el ID correcto
            } else {
                Log.e("DownloadManager", "ID de descarga incorrecto. No se instalará el APK.");
            }
        }
    };

    private void instalarAPK(File apkFile) {
        if (!apkFile.exists()) {
            Toast.makeText(this, "Error: el archivo APK no se descargó", Toast.LENGTH_SHORT).show();
            return;
        }

        Uri apkUri = FileProvider.getUriForFile(this, getApplicationContext().getPackageName() + ".provider", apkFile);
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(apkUri, "application/vnd.android.package-archive");
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(onDownloadComplete);
    }
}