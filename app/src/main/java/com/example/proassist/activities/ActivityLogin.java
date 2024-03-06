package com.example.proassist.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.proassist.R;
import com.example.proassist.interfaces.TaskCompleted;
import com.example.proassist.mappers.UserMapper;
import com.example.proassist.model.User;
import com.example.proassist.services.UserService;
import com.example.proassist.utils.SharedPreferencesUtils;

import org.json.JSONException;

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

        loadSavedSession();
        
    }

    private void setEvents() {
        this.btnLogin.setOnClickListener((e) -> {
            String inputUser = inputUsername.getText().toString();
            String inputPass = inputPassword.getText().toString();

            //TODO RED WHEN NO USER
            if(inputUser.isEmpty()) return;
            if(inputPass.isEmpty()) return;

            UserService serv = new UserService(ActivityLogin.this);
            serv.execute(new User(inputUser,inputPass));


        });
    }

    private void loadSavedSession(){
        String usernm = SharedPreferencesUtils.getUsername(this);
        String passwd = SharedPreferencesUtils.getPassword(this);
        if(usernm != null && passwd != null){
            UserService serv = new UserService(ActivityLogin.this);
            serv.execute(new User(usernm,passwd));
        }
    }

    private void saveSession(User user){
        SharedPreferencesUtils.saveSession(this, user);
    }

    private void loadElements() {
        this.inputPassword = findViewById(R.id.inputPassword);
        this.inputUsername = findViewById(R.id.inputUsuario);
        this.btnLogin = findViewById(R.id.btnLogin);
    }

    private void goToMenu(User usuario){
        startActivity(new Intent(ActivityLogin.this, ActivityMenu.class).putExtra("user", usuario));
    }

    @Override
    public void onTaskCompleted(String userString) {

        if (userString == null || userString.equals("[]")) {
            Toast.makeText(this, "NO ENCONTRADO", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            User usuario = new UserMapper().map(userString);

            saveSession(usuario);

            goToMenu(usuario);
            finish();
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
}