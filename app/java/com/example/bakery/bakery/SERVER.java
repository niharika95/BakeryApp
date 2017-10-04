package com.example.bakery.bakery;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Locale;

public class SERVER extends AppCompatActivity {
    Button submitbtn,delete,complete;
    SQLiteDatabase db;
    EditText category,dish,description,detail,price,type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_server);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        db = openOrCreateDatabase("test1.db", SQLiteDatabase.CREATE_IF_NECESSARY, null);
        db.execSQL(
                "create table if not exists Bakery " +
                        "( category text,dish text,description text,detail text,cost text,type text)"
        );

        db.setVersion(1);
        db.setLocale(Locale.getDefault());
        db.setLockingEnabled(true);


        submitbtn = (Button) findViewById(R.id.btnSave);

        category = (EditText) findViewById(R.id.txtCategory);
        dish = (EditText) findViewById(R.id.txtDish);
        description= (EditText) findViewById(R.id.Description);
        detail= (EditText) findViewById(R.id.Detail);
        price= (EditText) findViewById(R.id.cost);
        type= (EditText) findViewById(R.id.type);

        delete = (Button) findViewById(R.id.delete);
        complete= (Button) findViewById(R.id.btnComplete);

        submitbtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                insert(v);


            }
        });


        delete.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                del(v);


            }
        });

        complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent obj = new Intent(getApplicationContext(), Page1.class);
                startActivity(obj);
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    void del(View v)
    {
        this.deleteDatabase("test1.db");
        db.close();
    }



    void insert(View v)
    {

        db=openOrCreateDatabase("test1.db", SQLiteDatabase.CREATE_IF_NECESSARY, null);

        ContentValues cv=new ContentValues();
        cv.put("category", category.getText().toString());
        cv.put("dish", dish.getText().toString());
        cv.put("description",description.getText().toString());
        cv.put("detail",detail.getText().toString());
        cv.put("cost",price.getText().toString());
        cv.put("type",type.getText().toString());
        long a= db.insert("Bakery", null, cv);
        db.close();
        if(a!=0)
        {
            Toast.makeText(this, "inserted", Toast.LENGTH_SHORT).show();


        }

        else
            Toast.makeText(this, "Error occured", Toast.LENGTH_SHORT).show();


    }

    }


