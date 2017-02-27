package com.vgilanza.app.vigilanza;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import android.util.Log;
import android.widget.Toast;


public class TapReceiver extends BroadcastReceiver {
    public static boolean wasScreenOn=true;

    public TapReceiver() {
    }

    @Override
    public void onReceive(final Context context, final Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        Log.e("LOB","OnRecieve");
        if(intent.getAction().equals(Intent.ACTION_SCREEN_OFF)){
            wasScreenOn=false;
            Log.e("LOG","wasScreenOn"+wasScreenOn);
        }
        else if(intent.getAction().equals(Intent.ACTION_SCREEN_ON)){
            wasScreenOn=true;
        }
        else if(intent.getAction().equals(Intent.ACTION_USER_PRESENT)){
            Log.e("LOB","userpresent");
            Log.e("LOB","wasScreenOn"+wasScreenOn);
            Toast.makeText(context,"You tapped button",Toast.LENGTH_LONG).show();
            //context.startActivity(Login.class);
        }
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
