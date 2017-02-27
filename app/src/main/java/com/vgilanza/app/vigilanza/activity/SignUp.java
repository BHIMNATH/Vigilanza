package com.vgilanza.app.vigilanza.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import com.vgilanza.app.vigilanza.LoginDataBaseAdapter;
import com.vgilanza.app.vigilanza.R;

import com.vgilanza.app.vigilanza.helper.SessionManager;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class SignUp extends AppCompatActivity {

    private EditText editTextName;
    private EditText editTextPassword;
    private EditText editTextEmail;
    private EditText editTextMobile;
    private EditText editTextRecovery;
    private Button buttonRegister;
    private Button buttonLogin;
    LoginDataBaseAdapter loginDataBaseAdapter;

    private static final String REGISTER_URL = "http://192.168.43.184/vigilanza_api/register.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        editTextName = (EditText) findViewById(R.id.sign_name);
        editTextPassword = (EditText) findViewById(R.id.sign_pswd);
        editTextEmail = (EditText) findViewById(R.id.sign_email);
        editTextMobile = (EditText) findViewById(R.id.sign_mob);
        editTextRecovery = (EditText) findViewById(R.id.sign_rec_no);

        buttonRegister = (Button) findViewById(R.id.sign_but);
        buttonLogin = (Button) findViewById(R.id.sign_login);
        loginDataBaseAdapter=new LoginDataBaseAdapter(this);
        try {
            loginDataBaseAdapter = loginDataBaseAdapter.open();
        }
        catch (Exception e){
            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
        }
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backLogin();
            }
        });
    }
    
    private void registerUser() {
        String name = editTextName.getText().toString().trim().toLowerCase();
        String password = editTextPassword.getText().toString().trim().toLowerCase();
        String email = editTextEmail.getText().toString().trim().toLowerCase();
        String mobile = editTextMobile.getText().toString().trim();
        String recovery = editTextRecovery.getText().toString().trim();
        if(!name.isEmpty() && !password.isEmpty() && !email.isEmpty() && !mobile.isEmpty() && !recovery.isEmpty()){
            loginDataBaseAdapter.insertEntry(name, password, email, mobile, recovery);
            Toast.makeText(getApplicationContext(), "Account Successfully Created ", Toast.LENGTH_LONG).show();
            finish();
            loginDataBaseAdapter.close();
        }
        else{
            Toast.makeText(getApplicationContext(),"Enter all credentials",Toast.LENGTH_LONG).show();
        }
    }

    private void backLogin(){
       Intent intent = new Intent(SignUp.this, Login.class);
        startActivity(intent);
    }
}