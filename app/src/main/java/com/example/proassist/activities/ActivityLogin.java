package com.example.proassist.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.proassist.R;
import com.example.proassist.interfaces.TaskCompleted;
import com.example.proassist.model.User;
import com.example.proassist.services.UserService;

public class ActivityLogin extends AppCompatActivity implements TaskCompleted {

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

            //TODO CHECK USER
            if(inputUser.isEmpty()) return;
            if(inputPass.isEmpty()) return;

            //goToMenu();


            UserService serv = new UserService(ActivityLogin.this);
            serv.execute(new User(inputUser,inputPass));
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

    @Override
    public void onTaskCompleted(String s) {
        Toast.makeText(ActivityLogin.this, "Res:" + s, Toast.LENGTH_SHORT).show();
    }
}