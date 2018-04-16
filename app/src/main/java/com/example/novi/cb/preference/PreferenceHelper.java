package com.example.novi.cb.preference;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceHelper {

    private static String PREF_KEY = "1";

    Activity context;
    SharedPreferences pref;
    SharedPreferences.Editor editor;

    public PreferenceHelper(Activity context) {
        this.context = context;

        pref = context.getSharedPreferences(PREF_KEY, Context.MODE_PRIVATE);
    }

    public String getUser(){
        return pref.getString("user", null);
    }

    public void addUser(String user) {
        editor = pref.edit();

        editor.putString("user", user);
        editor.commit();
    }

    public void deleteUser() {
        editor = pref.edit();
        editor.remove("user");
        editor.commit();
    }

}
