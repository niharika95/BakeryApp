package com.example.bakery.bakery;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;
import java.util.Locale;

public class Page1 extends AppCompatActivity
implements NavigationView.OnNavigationItemSelectedListener {

    String category;
    ImageButton imgButton;
    String food;
    String price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page1);
        price="200";

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.bev);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "BEV", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Intent obj = new Intent(getApplicationContext(), Dish.class);
                category="bev";
                obj.putExtra("dish", category);
                startActivity(obj);
            }
        });

        //CAKE

        FloatingActionButton fab1 = (FloatingActionButton) findViewById(R.id.cake);
        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "CAKE", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Intent obj = new Intent(getApplicationContext(), Dish.class);
                category="cake";
                obj.putExtra("dish", category);
                startActivity(obj);
            }
        });

        //Cookie
        FloatingActionButton fab2 = (FloatingActionButton) findViewById(R.id.cookie);
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Snack", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Intent obj = new Intent(getApplicationContext(), Dish.class);
                category="snack";
                obj.putExtra("dish", category);
                startActivity(obj);
                   }
        });

        imgButton =(ImageButton)findViewById(R.id.pasta);
                imgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                food="Pasta";
                insert(getApplicationContext(),v,food,200,R.drawable.pasta);
                Toast.makeText(getApplicationContext(), food, Toast.LENGTH_LONG).show();

            }
        });
        imgButton =(ImageButton)findViewById(R.id.Choclate_cake);
        imgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                food="Choclate Cake";
                insert(getApplicationContext(),v,food,200,R.drawable.choclate_cake);
                Toast.makeText(getApplicationContext(),food,Toast.LENGTH_SHORT).show();
            }
            });
        imgButton =(ImageButton)findViewById(R.id.burger);
        imgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                food="Samosa";
                insert(getApplicationContext(),v,food,200,R.drawable.snacks1);
                Toast.makeText(getApplicationContext(),food,Toast.LENGTH_SHORT).show();
            }
        });
        imgButton =(ImageButton)findViewById(R.id.beverage);
        imgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                food="Green Tea";
                insert(getApplicationContext(),v,food,200,R.drawable.beverage3);
                Toast.makeText(getApplicationContext(),food,Toast.LENGTH_SHORT).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

        navigationView.setNavigationItemSelectedListener(this);
    }

  public   static void insert(Context ctx,View v, String food, int price,int img)
    {   SQLiteDatabase db1;
        db1   = ctx.openOrCreateDatabase("test2.db", SQLiteDatabase.CREATE_IF_NECESSARY, null);
        db1.execSQL(
                "create table if not exists Cart " +
                        "( dish text,cost text,image text )"
        );
        db1.setVersion(1);
        db1.setLocale(Locale.getDefault());
        db1.setLockingEnabled(true);

        ContentValues cv=new ContentValues();

        cv.put("dish", food);
        cv.put("cost", price);
        cv.put("image",img);

        long a= db1.insert("Cart", null, cv);
        db1.close();
        if(a!=0)
        {
            Toast.makeText(ctx, "inserted", Toast.LENGTH_SHORT).show();
        }

        else
            Toast.makeText(ctx, "Error occured", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer;
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_page1, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();


        if (id == R.id.cartsc) {
            Toast.makeText(Page1.this, "Opening Cart", Toast.LENGTH_SHORT).show();
            Intent obj = new Intent(getApplicationContext(), Cart.class);
            startActivity(obj);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.cart) {
            Toast.makeText(getBaseContext(), "open cart", Toast.LENGTH_SHORT).show();

            Intent obj = new Intent(getApplicationContext(), Cart.class);
            startActivity(obj);

        } else if (id == R.id.nav_share) {
            Uri imageUri = Uri.parse("android.resource://" + getPackageName()
                    + "/drawable/" + "ic_launcher");
            Intent shareIntent = new Intent();
            shareIntent.setAction(Intent.ACTION_SEND);
            shareIntent.putExtra(Intent.EXTRA_TEXT, "Do visit our Bakery or order from our app CAKESTOP\nThis message is sent by CAKESTOP©");
            shareIntent.putExtra(Intent.EXTRA_STREAM, imageUri);
            shareIntent.setType("image/jpeg");
            shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            startActivity(Intent.createChooser(shareIntent, "send"));
            Toast.makeText(getBaseContext(), "Shared", Toast.LENGTH_SHORT).show();

        } else if (id == R.id.contact) {


            Intent obj = new Intent(getApplicationContext(), Contact_us.class);
            startActivity(obj);
        } else if (id == R.id.server) {


            Intent obj = new Intent(getApplicationContext(), SERVER.class);
            startActivity(obj);

            Toast.makeText(getBaseContext(), "SERVER", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.client) {
            del1();
            Toast.makeText(getBaseContext(), "Clear Database", Toast.LENGTH_SHORT).show();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    void del1()
    {       SQLiteDatabase db1;
        db1   = openOrCreateDatabase("test2.db", SQLiteDatabase.CREATE_IF_NECESSARY, null);
        this.deleteDatabase("test2.db");
        db1.close();
    }
}

