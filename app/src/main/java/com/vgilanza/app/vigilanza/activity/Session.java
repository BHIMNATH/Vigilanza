package com.vgilanza.app.vigilanza.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by Aswin on 11-Feb-17.
 */
public class Session {
    private SharedPreferences sp;
    public Session(Context ctx){
        sp= PreferenceManager.getDefaultSharedPreferences(ctx);
    }
}
