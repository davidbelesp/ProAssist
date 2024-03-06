package com.example.proassist.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.example.proassist.R;
import com.example.proassist.model.User;
import com.example.proassist.utils.SharedPreferencesUtils;

public class ActivityMenu extends AppCompatActivity {

    private Button btnVerHoy;
    private Button btnVerManana;
    private Button btnAltaAsistencia;

    private Toolbar toolbar;

    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        loadElements();

        setEvents();

        setData();
    }

    private void setData() {
        this.user = (User) getIntent().getSerializableExtra("user");

        Log.i("ACTIVITY MENU", user.toString());
    }

    private void setEvents() {
        this.btnAltaAsistencia.setOnClickListener(e -> {
            startActivity(new Intent(ActivityMenu.this, AltaAsistenciasActivity.class).putExtra("user", user));
        });

        this.btnVerHoy.setOnClickListener(e -> {
            startActivity(new Intent(ActivityMenu.this, FaltasHoyAcivity.class));
        });

        this.btnVerManana.setOnClickListener(e -> {
            startActivity(new Intent(ActivityMenu.this, FaltasMananaActivity.class));
        });

        this.toolbar.setNavigationOnClickListener(e -> {
            showConfirmationDialog();
        });
    }

    private void loadElements() {
        this.btnAltaAsistencia = findViewById(R.id.btnAltaAsistencias);
        this.btnVerHoy = findViewById(R.id.btnVerHoy);
        this.btnVerManana = findViewById(R.id.btnVerManana);
        this.toolbar = findViewById(R.id.toolbar);
    }

    private void showConfirmationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Cerrar Sesión");
        builder.setMessage("¿Estás seguro que quieres cerrar sesión?");

        builder.setPositiveButton("Sí", (dialog, which) -> {
            SharedPreferencesUtils.deleteSession(this);
            startActivity(new Intent(ActivityMenu.this, ActivityLogin.class));
            finish();
        });

        builder.setNegativeButton("No", (dialog, which) -> {
           dialog.dismiss();
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}