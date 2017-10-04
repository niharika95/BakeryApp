package com.example.bakery.bakery;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collections;
import java.util.List;

public class recadapter extends RecyclerView.Adapter <recadapter.MyviewHolder> {
    private LayoutInflater inflater;
    private Context ctx,ctx1;

    List<data> Data= Collections.emptyList();

    public  recadapter(Context context ,List<data> Data){
        inflater=LayoutInflater.from(context);
        this.Data=Data;
        ctx=context;

    }
    @Override
    public MyviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.customrow, parent,false);
        MyviewHolder holder =new MyviewHolder(view);

        return holder;

    }


    @Override
    public void onBindViewHolder(MyviewHolder holder, final int position) {

        final data current=Data.get(position);
        holder.textView.setText(current.title+"     "+current.price+"Rs.\n"+current.des);

        holder.imageView.setImageResource(current.iconid);

        holder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String food=current.title;
                int price = current.price;
                Page1 d = new Page1();

               d.insert(ctx,v,food,price,current.iconid);
                Toast.makeText(ctx,"ADDED TO CART :"+current.title, Toast.LENGTH_SHORT).show();
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ctx,""+current.title, Toast.LENGTH_SHORT).show();
                Intent obj = new Intent(ctx, details.class);

                obj.putExtra("title", current.title);

                obj.putExtra("image",current.iconid);

                ctx.startActivity(obj);

            }
        });

    }

    @Override
    public int getItemCount() {
        return Data.size();
    }

    class MyviewHolder extends  RecyclerView.ViewHolder{
        TextView textView;
        ImageView imageView;
        Button btn;
        Context ctx;
        public MyviewHolder(View itemView) {
            super(itemView);
            textView= (TextView) itemView.findViewById(R.id.listText);
            imageView = (ImageView) itemView.findViewById(R.id.listicon);
            btn= (Button) itemView.findViewById(R.id.btn);

        }
    }
}
