package com.example.orgame.controller;

import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;


import com.example.orgame.R;
import com.example.orgame.model.Pizza;

import java.util.Map;

public class PizzaItemsAdapter extends RecyclerView.Adapter<PizzaItemsAdapter.MyViewHolder> {
    private Map<Integer,Pizza> pizzaMap;

    class MyViewHolder extends RecyclerView.ViewHolder {
        ConstraintLayout constraintLayout;
        ImageView pizzaIcon;
        TextView pizzaPreparingTime;
        TextView pizzaBakingTime;
        ImageView checkIcon;
        ImageView pizzaBorder;

        MyViewHolder(View view) {
            super(view);
            constraintLayout = (ConstraintLayout)view.findViewById(R.id.itemConstraintLayout);
            pizzaPreparingTime = (TextView)view.findViewById(R.id.pizzaPreparingTime);
            pizzaBakingTime = (TextView)view.findViewById(R.id.pizzaBakingTime);
            checkIcon = (ImageView)view.findViewById(R.id.checkIcon);
            pizzaBorder = (ImageView)view.findViewById(R.id.pizzaBorder);
        }

    }

    public PizzaItemsAdapter(Map<Integer,Pizza> map) {
        this.pizzaMap = map;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pizza, parent,false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
//        return null;
    }

    @Override
    @NonNull
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        //get pizza by position
        Pizza pizza = pizzaMap.get(position);
        //set item border color
        GradientDrawable drawable = new GradientDrawable();
        drawable.setCornerRadius(25);
        drawable.setStroke(15, pizza.getColor());
        holder.itemView.setBackground(drawable);
        //set item's data
        //set prepraing time rectangle color and text
        GradientDrawable rectDrawable1 =  (GradientDrawable)holder.pizzaPreparingTime.getBackground();
        rectDrawable1.setColor(pizza.getColor());
        holder.pizzaPreparingTime.setText(Integer.toString(pizza.getPreparingTime()) + " mins");
        //set baking time rectangle color and text
        GradientDrawable rectDrawable2 =  (GradientDrawable)holder.pizzaBakingTime.getBackground();
        rectDrawable2.setColor(pizza.getColor());
        holder.pizzaBakingTime.setText(Integer.toString(pizza.getBakingTime()) + " mins");
        // set checkIcon invisible
        holder.checkIcon.setVisibility(View.INVISIBLE);
        // set pizza icon color
        GradientDrawable ovalDrawable =  (GradientDrawable)holder.pizzaBorder.getBackground();
        ovalDrawable.setColor(pizza.getColor());
    }

    @Override
    public int getItemCount() {
        return pizzaMap.size();
    }


    public int getImageResourceId(String name) {
        R.drawable drawables=new R.drawable();
        int resId=0x7f02000b;
        try {
            java.lang.reflect.Field field=R.drawable.class.getField(name);
            resId=(Integer)field.get(drawables);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return resId;
    }



}
