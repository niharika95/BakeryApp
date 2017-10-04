package com.example.bakery.bakery;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

public class Start extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        new Handler().postDelayed(new Runnable() {

            // Using handler with postDelayed called runnable run method

            @Override
            public void run() {
                Intent i = new Intent(getApplicationContext(), Page1.class);
                startActivity(i);
                Toast.makeText(getBaseContext(), "Swipe Left For More Options", Toast.LENGTH_SHORT).show();
                // close this activity
                finish();
            }
        }, 3 * 1000); // wait for 3 seconds

    }
}
