package com.example.fab083;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.core.view.GravityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.fab083.databinding.ActivityMainNavigationBinding;

import java.io.IOException;
import java.util.Locale;

import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;

public class MainActivityNavigation extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainNavigationBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainNavigationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        setSupportActionBar(binding.appBarMainActivityNavigation.toolbar);
        binding.appBarMainActivityNavigation.fab.setOnClickListener(new View.OnClickListener() {
           /* @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }*/
            @Override
            public void onClick(View view) {
                DrawerLayout drawer = binding.drawerLayout;
                drawer.openDrawer(GravityCompat.START);
            }
        });
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;

        View headerView = navigationView.getHeaderView(0); // Obtiene la vista de encabezado
        String nombreUsuario = getIntent().getStringExtra("nombreUsuario");
        TextView textViewNombreUsuario = headerView.findViewById(R.id.txtNombreUsaurioMenu); // Asegúrate de que este ID coincida con el TextView en tu diseño de encabezado
        textViewNombreUsuario.setText(nombreUsuario);


        GifImageView gifImageView = headerView.findViewById(R.id.myGifImageUser);

        try {
            GifDrawable gifDrawable = new GifDrawable(getResources(), R.drawable.user);
            gifImageView.setImageDrawable(gifDrawable);
        } catch (IOException e) {
            e.printStackTrace();
        }


        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow,R.id.nav_rep_stock)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main_activity_navigation);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);


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
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_activity_navigation, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main_activity_navigation);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}