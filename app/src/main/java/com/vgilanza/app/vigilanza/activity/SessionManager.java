package com.vgilanza.app.vigilanza.activity;

import android.content.SharedPreferences;

import java.util.Stack;

/**
 * Created by Aswin on 19-Feb-17.
 */
public class SessionManager {
    static String name;
    public void setName(String sessionName){
        SharedPreferences sharedPreferences;

        name=sessionName;
    }
}
