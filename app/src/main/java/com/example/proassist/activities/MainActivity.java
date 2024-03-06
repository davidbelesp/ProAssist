package com.example.proassist.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.proassist.R;

public class MainActivity extends AppCompatActivity {

    private Button btnEntrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadElements();

        setEvents();

    }

    private void loadElements() {
        this.btnEntrar = findViewById(R.id.btnEnter);
    }

    private void setEvents() {
        this.btnEntrar.setOnClickListener(e -> {
            startActivity(new Intent(MainActivity.this, ActivityLogin.class));
            finish();
        });
    }
}