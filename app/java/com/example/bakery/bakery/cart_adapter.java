package com.example.bakery.bakery;



import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collections;
import java.util.List;



public class cart_adapter extends RecyclerView.Adapter <cart_adapter.MyviewHolder> {
    private LayoutInflater inflater;
    private Context ctx,ctx1;

    List<c_data> Data= Collections.emptyList();

    public  cart_adapter(Context context ,List<c_data> Data){
        inflater=LayoutInflater.from(context);
        this.Data=Data;
        ctx=context;

    }
    @Override
    public MyviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.cartview, parent,false);
        MyviewHolder holder =new MyviewHolder(view);

        return holder;

    }
    public void delete(int position){
        Data.remove(position);
        notifyItemRemoved(position);

    }

    @Override
    public void onBindViewHolder(MyviewHolder holder, final int position) {

        final c_data current=Data.get(position);
        holder.textView.setText(current.title + "\nCost:" + current.price + "Rs.");

        holder.imageView.setImageResource(current.icon);

        holder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete(position);
                String Title=current.title;
               int val= Cart.del_row(ctx, Title);
                if(val>0){
                    Toast.makeText(ctx, "Remove " + current.title, Toast.LENGTH_SHORT).show();
                }else
                    Toast.makeText(ctx, "BOO!! " + current.title, Toast.LENGTH_SHORT).show();
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
        ImageButton btn;


        public MyviewHolder(View itemView) {
            super(itemView);
            textView= (TextView) itemView.findViewById(R.id.tv1);
            imageView = (ImageView) itemView.findViewById(R.id.carticon);
            btn= (ImageButton) itemView.findViewById(R.id.btn);

        }
    }
}
