package com.example.proassist.mappers;

import android.util.Log;

import com.example.proassist.model.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class UserMapper implements IMapper<User, String>{

    @Override
    public User map(String string) throws JSONException {

        JSONArray array = new JSONArray(string);

        if(array.length() < 1){
            return null;
        }

        JSONObject uniObject = array.getJSONObject(0);


        Integer id = uniObject.getInt("id_profesor");
        String nombre = uniObject.getString("nombre");
        String apellido = uniObject.getString("apellido");
        String password = uniObject.getString("pass");
        String especialidad = uniObject.getString("especialidad");

        User user = new User(id, nombre, apellido, especialidad, password);
        Log.i("ERROR MAPPER", user.toString());

        return user;
    }
}
