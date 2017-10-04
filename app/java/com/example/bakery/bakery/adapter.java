package com.example.bakery.bakery;

import android.content.Context;
import android.support.v7.widget.CardView;
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


public class adapter extends RecyclerView.Adapter <adapter.viewHolder> {
    private LayoutInflater inflater;
    private Context ctx;
    String food;
    int price;
    List<data1> Data= Collections.emptyList();
    public  adapter(Context context ,List<data1> Data){
        inflater=LayoutInflater.from(context);
        this.Data=Data;
        ctx=context;

    }
    @Override
    public viewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        switch (viewType){
            case 0:

                View view=inflater.inflate(R.layout.name, parent,false);
                viewHolder holder =new viewHolder(view);
                return holder;

            case 1:
                View view1=inflater.inflate(R.layout.det, parent,false);
                viewHolder holder1 =new viewHolder(view1);
                return holder1;

            case 2:
                View view2=inflater.inflate(R.layout.cost, parent,false);
                viewHolder holder2 =new viewHolder(view2);
                return holder2;

            case 3:
                View view3=inflater.inflate(R.layout.buy, parent,false);
                viewHolder holder3 =new viewHolder(view3);
                return holder3;
        }

        return null;
    }
    @Override
    public void onBindViewHolder(viewHolder holder,int position) {

         final data1 current = Data.get(position);
        if (position == 0) {
            holder.textView.setText(current.Title);

        } else if (position == 1) {

            holder.textView1.setText("\n"+current.Detail+"\n");
        } else if(position==2){
            holder.textView2.setText("PRICE :"+current.p);
            holder.imageView.setImageResource(current.icon);

        }else if (position == 3) {
            holder.btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Page1 d=new Page1();
                    food=current.Title;
                    price=current.p;
                    d.insert(ctx,v,food,price,current.icon);
                    Toast.makeText(ctx, "ADDED TO CART :"+food , Toast.LENGTH_SHORT).show();
                }
            });

        }
    }
    @Override
    public int getItemCount() {
        return Data.size();
    }
    public int getItemViewType(int position){
        if(position==0){
            return 0;
        }else if (position == 1)
        { return 1;}
        else if(position==2){
            return 2;
        }else if(position==3)
        {
            return 3;
        }
        return 0;
    }

    class viewHolder extends  RecyclerView.ViewHolder{
        TextView textView,textView1,textView2;
        ImageView imageView;
        Button btn;
        CardView cardView,cardView1;
        public viewHolder(View itemView) {
            super(itemView);
             cardView = (CardView) itemView.findViewById(R.id.cv);
            cardView1= (CardView) itemView.findViewById(R.id.cv1);
            textView= (TextView) itemView.findViewById(R.id.title);
            textView1= (TextView) itemView.findViewById(R.id.detail);
            textView2= (TextView) itemView.findViewById(R.id.price);
            imageView = (ImageView) itemView.findViewById(R.id.type);
            btn= (Button) itemView.findViewById(R.id.buy);

        }
    }
}
