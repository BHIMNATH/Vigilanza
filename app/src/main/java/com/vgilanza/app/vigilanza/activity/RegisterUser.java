package com.vgilanza.app.vigilanza.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Aswin on 20-Feb-17.
 */
public class RegisterUser extends AsyncTask<String, Void, String> {
    private static final String REGISTER_URL = "http://192.168.43.184/vigilanza_api/register.php";

    ProgressDialog loading;
        Context context;
        ProgressDialog load;
        SharedPreferences sp;
        SharedPreferences.Editor edit;
        public static String result;
        static Intent i;

        RegisterUser(Context ctx){
            context=ctx;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            loading = ProgressDialog.show(context, "Please Wait",null, true, true);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            loading.dismiss();
        }

        @Override
        protected String doInBackground(String... params) {
            String s = params[0];
            BufferedReader bufferedReader = null;
            try {
                URL url = new URL(REGISTER_URL+s);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

                String result;

                result = bufferedReader.readLine();
                Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show();
                return result;
            }
            catch(Exception e){
                Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
                return null;
            }
        }
    }
