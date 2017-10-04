package com.example.bakery.bakery;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import android.widget.TextView;


import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

public class Cart extends AppCompatActivity {
    TextView textView;
    SQLiteDatabase db1;
    String[] title=new String[30];
    int count;
    int[] price=new int[30];
    int[] icon=new int[30];
    RecyclerView recyclerView;
    cart_adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        textView= (TextView) findViewById(R.id.carttv);
        title[0]=null;
        Selectdata1();


        recyclerView= (RecyclerView) findViewById(R.id.c_list);
        recyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(this).showLastDivider().color(Color.BLACK).build());
        adapter = new cart_adapter(this, getData());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    void Selectdata1()
    {


        up1();
    }
    void up1()
    {
        db1=openOrCreateDatabase("test2.db", SQLiteDatabase.CREATE_IF_NECESSARY, null);

        Cursor cr1=db1.query("Cart", null, null, null, null, null, null, null);
        cr1.moveToFirst();
        int i=0;
        while(!cr1.isAfterLast())
        {
            title[i]= cr1.getString(0);
                    price[i]= (cr1.getInt(1));
            icon[i]=cr1.getInt(2);

            i++;
            cr1.moveToNext();
        }
        count=i;
        cr1.close();
        db1.close();
    }

    public static int del_row(Context ctx, String title){
        SQLiteDatabase db1;
        db1=ctx.openOrCreateDatabase("test2.db", SQLiteDatabase.CREATE_IF_NECESSARY, null);
        return db1.delete("Cart","dish=?" ,new String[] {title});

    }
    public List<c_data> getData() {
        List<c_data> Data = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            c_data current = new c_data();

            current.title = title[i];
            current.icon=icon[i];
            current.price=price[i];
            Data.add(current);

        }
        return Data;
    }

}
