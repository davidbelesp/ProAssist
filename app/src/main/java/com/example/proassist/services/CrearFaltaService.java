package com.example.proassist.services;

import android.os.AsyncTask;
import android.util.Log;

import com.example.proassist.interfaces.TaskCompleted;
import com.example.proassist.model.AddFaltaDto;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class CrearFaltaService extends AsyncTask<Object, Object, String> {

    private String linea = null;

    private TaskCompleted listener;

    public CrearFaltaService(TaskCompleted listener){
        this.listener = listener;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        listener.onTaskCompleted(linea);
    }

    @Override
    protected String doInBackground(Object... params) {

        AddFaltaDto newFalta = (AddFaltaDto) params[0];

        HttpURLConnection urlConnection;

        try{

            String query = "?profesor=" + newFalta.getIdProfesor() +
                            "&fecha=" + newFalta.getFecha() +
                            "&hora_numero=" + newFalta.getHora();

            URL url = new URL("https://sanmigcea.000webhostapp.com/add-faltas.php" + query);

            Log.i("FALTAS INFO", url.toString());

            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("POST");
            urlConnection.setDoOutput(true);

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
