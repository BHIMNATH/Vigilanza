package com.vgilanza.app.vigilanza.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.vgilanza.app.vigilanza.R;

public class Contacts extends AppCompatActivity {
    private Button addContact,deleteContact,updateContact;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
        addContact=(Button) findViewById(R.id.contact_add_but);
        deleteContact=(Button) findViewById(R.id.contact_del_but);
        updateContact=(Button) findViewById(R.id.contact_upd_but);
        setListeners();
    }
    public void setListeners(){
        addContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Contacts.this,AddContact.class);
                startActivity(i);
            }
        });
        deleteContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        updateContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
