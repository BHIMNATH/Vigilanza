package com.vgilanza.app.vigilanza.activity;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.vgilanza.app.vigilanza.ContactDataBaseAdapter;
import com.vgilanza.app.vigilanza.LoginDataBaseAdapter;
import com.vgilanza.app.vigilanza.R;

public class AddContact extends AppCompatActivity {
    private Button add_contact;
    private EditText textName,textMob,textEmail;
    ContactDataBaseAdapter contactDataBaseAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);
        add_contact=(Button) findViewById(R.id.add_contact_but);
        textName=(EditText) findViewById(R.id.add_contact_name);
        textMob=(EditText) findViewById(R.id.add_contact_no);
        textEmail=(EditText) findViewById(R.id.add_contact_mail);
        contactDataBaseAdapter=new ContactDataBaseAdapter(this);
        try {
            contactDataBaseAdapter = contactDataBaseAdapter.open();
        }
        catch (Exception e){
            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
        }
        add_contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addContact();
            }
        });
    }
    public void addContact(){
        String name = textName.getText().toString().trim().toLowerCase();
        String no = textMob.getText().toString().trim().toLowerCase();
        String email = textEmail.getText().toString().trim().toLowerCase();
        if(!name.isEmpty() && !no.isEmpty() && !email.isEmpty()){
            contactDataBaseAdapter.insertEntry(name, no,email);
            Toast.makeText(getApplicationContext(), "Contact Added ", Toast.LENGTH_LONG).show();
            finish();
        }
        else {
            Toast.makeText(getApplicationContext(),"Enter all credentials",Toast.LENGTH_LONG).show();
        }
    }
}