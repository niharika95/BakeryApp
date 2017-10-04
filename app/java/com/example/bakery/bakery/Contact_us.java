package com.example.bakery.bakery;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Contact_us extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final EditText phoneNumber1;

        phoneNumber1 = (EditText) findViewById(R.id.PhoneNumber);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.call);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String p;
            p=phoneNumber1.getText().toString();
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:"+p));
            try{
                 startActivity(callIntent);
            }

                catch (android.content.ActivityNotFoundException ex){
                    Toast.makeText(getApplicationContext(),"yourActivity is not founded", Toast.LENGTH_SHORT).show();
                }
                Snackbar.make(view, "Call", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        FloatingActionButton fab1 = (FloatingActionButton) findViewById(R.id.sms);
        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message="I'm intrested in your product";
                String p2="9820063310";
               SmsManager smsManager = SmsManager.getDefault();
               smsManager.sendTextMessage(p2, null, message, null, null);
                Snackbar.make(view, "SMS", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
