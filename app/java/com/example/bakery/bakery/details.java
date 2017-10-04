package com.example.bakery.bakery;

import android.annotation.TargetApi;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class details extends AppCompatActivity {
    ImageView iv1;
    private RecyclerView recyclerView1;
    private adapter adapter1;

    SQLiteDatabase db;
    String Title,detail,c;
    int type,price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent=getIntent();
         Title=intent.getExtras().getString("title");
        int image=intent.getExtras().getInt("image");
        iv1= (ImageView) findViewById(R.id.iv1);
        iv1.setImageResource(image);
        c="V";
        type=R.drawable.nonveg;

        Selectdata();


        recyclerView1 = (RecyclerView) findViewById(R.id.list);
         adapter1 = new adapter(this, getData());
        recyclerView1.setAdapter(adapter1);
        recyclerView1.setLayoutManager(new LinearLayoutManager(this));

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

        while(!cr.isAfterLast())
        {
            if(Objects.equals(cr.getString(1),Title))

            {
                detail=cr.getString(3);
                price=cr.getInt(4);
                if(Objects.equals(c, cr.getString(5))){
                    type=R.drawable.veg;
                }

            }

            cr.moveToNext();
        }
        cr.close();
        db.close();
    }

    public List<data1> getData() {
        List<data1> Data = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            data1 current = new data1();
            current.icon = type;
            current.Title = Title;
            current.Detail=detail;
            current.p=price;
            Data.add(current);

        }

                return Data;
    }
}
