package com.example.proassist.services;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.proassist.interfaces.TaskCompleted;
import com.example.proassist.model.User;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class UserService extends AsyncTask<Object, Object, String> {

    private String linea = null;

    private TaskCompleted listener;

    public UserService(TaskCompleted listener){
        this.listener = listener;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        listener.onTaskCompleted(linea);
    }

    @Override
    protected String doInBackground(Object... params) {

        User user = (User) params[0];

        HttpURLConnection urlConnection;

        try{
            String query = "?nombre=" + user.getNombre() + "&pass=" + user.get_password();

            URL url = new URL("https://sanmigcea.000webhostapp.com/verify-profesor.php" + query);

            Log.i("REQUEST ERROR", url.toString());

            urlConnection = (HttpURLConnection) url.openConnection();

            InputStream is = urlConnection.getInputStream();
            InputStreamReader isReader = new InputStreamReader(is, "UTF-8");

            BufferedReader reader = new BufferedReader(isReader);

            linea = reader.readLine();


        } catch (Exception e) {
            Log.e("ERROR REQUEST" , e.getMessage());
        }

        return linea;
    }


}
