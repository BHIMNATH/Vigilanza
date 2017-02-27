package com.vgilanza.app.vigilanza.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Entity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.BaseInputConnection;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.HttpClientStack;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpResponse;
import com.vgilanza.app.vigilanza.LockService;
import com.vgilanza.app.vigilanza.LoginDataBaseAdapter;
import com.vgilanza.app.vigilanza.R;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.sql.Connection;

public class Login extends AppCompatActivity {

    private EditText email;
    private EditText password;
    private Button enterBut;
    private Button newBut;
    private Button forgotBut;

    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String name = "nameKey";
    public static final String pass = "passwordKey";

    private static final String REGISTER_URL = "http://192.168.43.184/vigilanza_api/register.php";
    ProgressBar progressBar;

    Connection con;
    LoginDataBaseAdapter loginDataBaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email=(EditText) findViewById(R.id.login_email);
        password=(EditText) findViewById(R.id.login_pswd);
        enterBut=(Button) findViewById(R.id.login_enter);
        forgotBut=(Button) findViewById(R.id.login_forgot);
        newBut=(Button) findViewById(R.id.login_new);

        loginDataBaseAdapter=new LoginDataBaseAdapter(this);
        try {
            loginDataBaseAdapter = loginDataBaseAdapter.open();
        }
        catch (Exception e){
            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
        }

        enterBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    onLogIn();
            }
        });
        forgotBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),Forgot.class);
                startActivity(i);
            }
        });
        newBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), SignUp.class);
                startActivity(i);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void onLogIn(){
        String mail=email.getText().toString().trim();
        String pass=password.getText().toString().trim();

        if(mail.isEmpty() && pass.isEmpty()){
            Toast.makeText(getApplicationContext(),"Please enter Credentials",Toast.LENGTH_LONG).show();
        }
        else{
            String storedPassword=loginDataBaseAdapter.getSinlgeEntry(mail);
            if(pass.equals(storedPassword)){
                Toast.makeText(Login.this, "Congrats: Login Successfull", Toast.LENGTH_LONG).show();
                Intent i=new Intent(this,Home.class);
                //i.putExtra("uname", name);
                startActivity(i);
            }
            else {
                Toast.makeText(Login.this, "Enter correct details", Toast.LENGTH_LONG).show();
            }
        }
    }
}