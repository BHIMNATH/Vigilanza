package com.vgilanza.app.vigilanza.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.vgilanza.app.vigilanza.R;
import com.vgilanza.app.vigilanza.helper.SQLiteHandler;
import com.vgilanza.app.vigilanza.helper.SessionManager;

import java.util.HashMap;

public class Home extends AppCompatActivity {
    private TextView welcome;
    private SQLiteHandler db;
    private SessionManager session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

         welcome=(TextView)findViewById(R.id.home_welcome);
        createListeners();
//        Bundle bundle=getIntent().getExtras();
//        String name="Welcome "+bundle.getString("uname");
        welcome.setText("Welcome");
        welcome.setVisibility(View.VISIBLE);

    }
    private void logoutUser(){

    }
    private void createListeners(){
        Button b1=(Button) findViewById(R.id.home_contacts);
        Button b2=(Button) findViewById(R.id.home_capture);
        Button b3=(Button) findViewById(R.id.home_help);
        Button b4=(Button) findViewById(R.id.home_settings);
        Button b5=(Button) findViewById(R.id.home_pause);
        Button b6=(Button) findViewById(R.id.home_alert);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startContactActivity();
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startCaptureActivity();
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startHelpActivity();
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startSettingsActivity();
            }
        });
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startPauseAll();
            }
        });
        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAlertMessage();
            }
        });
    }
    private void startContactActivity(){
        Intent intent=new Intent(this,Contacts.class);
        startActivity(intent);
    }
    private void startCaptureActivity(){
        Intent intent=new Intent(this,Capture.class);
        startActivity(intent);
    }
    private void startHelpActivity(){
        Intent intent=new Intent(this,Help.class);
        startActivity(intent);
    }
    private void startSettingsActivity(){
        Intent intent=new Intent(this,Settings.class);
        startActivity(intent);
    }
    private void startPauseAll(){
        final AlertDialog alertDialog=new AlertDialog.Builder(this).create();
        alertDialog.setTitle("Pause");
        alertDialog.setMessage("The complete working of the background functions are paused");
        alertDialog.setButton(RESULT_OK, "OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alertDialog.show();
    }
    private void startAlertMessage(){
        Log.i("Send SMS","");
        String phoneNo="8086346115";
        String message="Help Me! I am in Danger ->Vigilanza";
        try{
            //SmsManager smsManager=SmsManager.getDefault();
            //smsManager.sendTextMessage(phoneNo,null,message,null,null);
            Toast.makeText(getApplicationContext(),"SMS Sent - "+message,Toast.LENGTH_LONG).show();
        }
        catch (Exception e){
            Toast.makeText(getApplicationContext(),"SMS failed, Please try again",Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }
}