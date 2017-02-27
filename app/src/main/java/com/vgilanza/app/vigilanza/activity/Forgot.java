package com.vgilanza.app.vigilanza.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.vgilanza.app.vigilanza.R;

public class Forgot extends AppCompatActivity {
    int random1,random2,random3,random4;
    private EditText enteredOtp;
    private Button resetPassword,resendOTP,backLogin;
    private String oneTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);
        enteredOtp=(EditText)findViewById(R.id.forgot_otp);
        resetPassword=(Button) findViewById(R.id.forgot_reset);
        resendOTP=(Button) findViewById(R.id.forgot_resend);
        backLogin=(Button) findViewById(R.id.forgot_back_login);
        generateOTP();
        setListners();
    }
    public void setListners(){
        resetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textOTP = enteredOtp.getText().toString().trim();
                if (textOTP.equals(oneTime)) {
                    Toast.makeText(getApplicationContext(), "Password changed. Default password 0000", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Wrong OTP", Toast.LENGTH_LONG).show();
                    resendOTP.setVisibility(View.VISIBLE);
                }
            }
        });
        resendOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generateOTP();
            }
        });
        backLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(Forgot.this,Login.class);
                startActivity(i);
            }
        });

    }
    public void generateOTP(){
        random1=(int)(Math.random()*10);
        random2=(int)(Math.random()*10);
        random3=(int)(Math.random()*10);
        random4=(int)(Math.random()*10);
        oneTime=random1+""+random2+""+random3+""+random4;
        Toast.makeText(getApplicationContext(),oneTime,Toast.LENGTH_LONG).show();
    }
}
