package com.example.proassist.mappers;

import android.util.Log;

import com.example.proassist.model.Falta;
import com.example.proassist.model.User;
import com.google.android.material.bottomappbar.BottomAppBar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class FaltaMapper implements IMapper<List<Falta>, String>{
    @Override
    public List<Falta> map(String string) throws JSONException {
        List<Falta> result = new ArrayList<>();

        JSONArray array = new JSONArray(string);

        if(array.length() < 1){
            return result;
        }

        for (int i = 1 ; i <= array.length() ; i++){
            JSONObject uniObject = array.getJSONObject(i-1);

            String nombre = uniObject.getString("nombre");
            String apellido = uniObject.getString("apellido");
            Integer hora = Integer.valueOf(uniObject.getString("hora"));
            String grupo = uniObject.getString("grupo");

            Falta falta = new Falta(nombre, apellido, hora, grupo);
            result.add(falta);
        }

        return result;
    }
}
