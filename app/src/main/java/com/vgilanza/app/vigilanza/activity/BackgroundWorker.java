package com.vgilanza.app.vigilanza.activity;

import com.vgilanza.app.vigilanza.activity.Login;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by Aswin on 11-Feb-17.
 */

public class BackgroundWorker extends AsyncTask<String,String,String> {
    Context context;
    ProgressDialog load;
    SharedPreferences sp;
    SharedPreferences.Editor edit;
    public static String result;
    static Intent i;

    BackgroundWorker(Context ctx){
        context=ctx;
    }
    @Override
    protected String doInBackground(String... params) {
        String login_url="http://192.168.43.184/vigilanza_api/login.php";
        try{
            String email=params[0];
            String pswd=params[1];
            URL url=new URL(login_url);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            OutputStream outputStream=httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
            String post_data= URLEncoder.encode("email","UTF-8")+"="+URLEncoder.encode(email,"UTF-8")+"&"+URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(pswd,"UTF-8");
            bufferedWriter.write(post_data);
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();
            InputStream inputStream=httpURLConnection.getInputStream();
            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
            result="";
            String line=null;
            while ((line=bufferedReader.readLine())!=null){
                result +=line;
            }
            bufferedReader.close();
            httpURLConnection.disconnect();
            sessionWork(result);
            return result;
        }
        catch (Exception e){
            //Toast.makeText(context,e.getMessage(),Toast.LENGTH_LONG).show();
        }
        return null;
    }

    @Override
    protected void onPreExecute() {
        load = ProgressDialog.show(context, "Please Wait",null, true, true);
    }

    @Override
    protected void onPostExecute(String aVoid) {
        load.dismiss();
        if(result.trim().equals("ERROR")){
            Toast.makeText(context,"Enter Correct Email/Password",Toast.LENGTH_LONG).show();
           // i=new Intent(context,Login.class);
        }
        else {
            Toast.makeText(context, "Welcome " + result, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);
    }
    public void sessionWork(String sessionName){

    }
}