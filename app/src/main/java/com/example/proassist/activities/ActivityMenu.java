package com.example.proassist.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.proassist.R;

public class ActivityMenu extends AppCompatActivity {

    private Button btnVerHoy;
    private Button btnVerManana;
    private Button btnAltaAsistencia;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        loadElements();

        setEvents();
    }

    private void setEvents() {
        this.btnAltaAsistencia.setOnClickListener(e -> {
            startActivity(new Intent(ActivityMenu.this, AltaAsistenciasActivity.class));
        });

        this.btnVerHoy.setOnClickListener(e -> {
            //TODO ABRIR ACTIVITY VER HOY
        });

        this.btnVerManana.setOnClickListener(e -> {
            //TODO ABRIR ACTIVITY VER MAÑANA
        });
    }

    private void loadElements() {
        this.btnAltaAsistencia = findViewById(R.id.btnAltaAsistencias);
        this.btnVerHoy = findViewById(R.id.btnVerHoy);
        this.btnVerManana = findViewById(R.id.btnVerManana);
    }
}