package com.example.proassist.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.proassist.model.User;

public class SharedPreferencesUtils {

    private static final String PREFS_NAME = "MyPreferences";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_PASSWORD = "password";

    public static void deleteSession(Context ctx){

        SharedPreferences preferences = ctx.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(KEY_USERNAME, null);
        editor.putString(KEY_PASSWORD, null);
        editor.apply();
    }

    public static void saveSession(Context ctx, User user){
        String username = user.getNombre();
        String password = user.get_password();
        SharedPreferences preferences = ctx.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(KEY_USERNAME, username);
        editor.putString(KEY_PASSWORD, password);
        editor.apply();
    }

    // Retrieve username from SharedPreferences
    public static String getUsername(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return preferences.getString(KEY_USERNAME, null);
    }

    // Retrieve password from SharedPreferences
    public static String getPassword(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return preferences.getString(KEY_PASSWORD, null);
    }

}
