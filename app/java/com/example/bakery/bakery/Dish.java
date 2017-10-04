package com.example.bakery.bakery;

import android.annotation.TargetApi;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;


import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class Dish extends AppCompatActivity {
    public     SQLiteDatabase db,db1;
    String dish;
    private RecyclerView recyclerView;
    private recadapter adapter;
    public String[] title = {null,null,null};
    public String[] des = {null,null,null};
    int icon[];
    int price[]={0,0,0};

    ImageView imageView;
    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cake);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);




        Intent i = getIntent();
        dish=i.getExtras().getString("dish");
        Selectdata();
        imageView = (ImageView) findViewById(R.id.dishback);

       if(Objects.equals(dish, "cake")){
        imageView.setImageResource(R.drawable.cake_back);}
        else {
           if(Objects.equals(dish, "bev")){
               imageView.setImageResource(R.drawable.bev_back);

           }else{
               imageView.setImageResource(R.drawable.snack_back);
           }

       }
        if (Objects.equals(dish,"cake")){
            icon = new int[]{R.drawable.cake1, R.drawable.cake2, R.drawable.cake3};
        }else {
            if(Objects.equals(dish,"bev")){

                icon = new int[]{R.drawable.beverage1, R.drawable.beverage2, R.drawable.beverage3};
            }else{
                icon = new int[]{R.drawable.snacks1, R.drawable.snacks2, R.drawable.snacks3};
            }
        }

        recyclerView = (RecyclerView) findViewById(R.id.list);
        recyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(this).showLastDivider().color(Color.BLACK).build());
        adapter = new recadapter(this, getData());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }



    void Selectdata()
    {


        up();
    }
    @TargetApi(Build.VERSION_CODES.KITKAT)
    void up()
    {
        db=openOrCreateDatabase("test1.db", SQLiteDatabase.CREATE_IF_NECESSARY, null);

        Cursor cr=db.query("Bakery", null, null, null, null, null, null, null);
        cr.moveToFirst();
        int i=0;
        while(!cr.isAfterLast())
        {
            if(Objects.equals(cr.getString(0),dish))

            {

                title[i]=
                        cr.getString(1);
                des[i]=
                        cr.getString(2);
                price[i]=
                        cr.getInt(4);
                i++;
            }

            cr.moveToNext();
            }
        cr.close();
        db.close();
    }

    public  List<data> getData() {
        List<data> Data = new ArrayList<>();

        for (int i = 0; i < title.length && i < icon.length; i++) {
            data current = new data();
            current.iconid = icon[i];
            current.title = title[i];
            current.des=des[i];
            current.price=price[i];
            Data.add(current);

        }
        return Data;
    }
}
