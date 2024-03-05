package com.example.proassist.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.proassist.R;

public class ActivityLogin extends AppCompatActivity {

    private EditText inputUsername;
    private EditText inputPassword;

    private Button btnLogin;

    private String testUser = "test";
    private String testPass = "1234";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        
        
        loadElements();
        
        setEvents();
        
    }

    private void setEvents() {
        this.btnLogin.setOnClickListener((e) -> {
            String inputUser = inputUsername.getText().toString();
            String inputPass = inputPassword.getText().toString();

            if(inputUser.isEmpty() || !inputUser.equals(testUser)) return;
            if(inputPass.isEmpty() || !inputPass.equals(testPass)) return;

            goToMenu();

        });
    }

    private void loadElements() {
        this.inputPassword = findViewById(R.id.inputPassword);
        this.inputUsername = findViewById(R.id.inputUsuario);
        this.btnLogin = findViewById(R.id.btnLogin);
    }

    private void goToMenu(){
        startActivity(new Intent(ActivityLogin.this, ActivityMenu.class));
    }
}