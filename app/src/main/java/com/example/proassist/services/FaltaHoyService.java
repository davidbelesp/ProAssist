package com.example.proassist.services;

import android.os.AsyncTask;
import android.util.Log;

import com.example.proassist.interfaces.TaskCompleted;
import com.example.proassist.model.User;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class FaltaHoyService extends AsyncTask<Object, Object, String> {


    private String linea = null;

    private TaskCompleted listener;

    public FaltaHoyService(TaskCompleted listener){
        this.listener = listener;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        listener.onTaskCompleted(linea);
    }

    @Override
    protected String doInBackground(Object... params) {

        String preUrl = (String) params[0];

        HttpURLConnection urlConnection;

        try{

            URL url = new URL(preUrl);

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
