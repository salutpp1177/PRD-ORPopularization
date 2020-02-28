package com.example.orgame.controller;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;


import com.example.orgame.R;
import com.example.orgame.model.Pizza;

import java.util.Map;

public class PizzaItemsAdapter extends RecyclerView.Adapter<PizzaItemsAdapter.MyViewHolder> {

    private Map<Integer,Pizza> pizzaMap;
    private OnItemClickListener onItemClickListener = null;

    public interface OnItemClickListener{
        void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        onItemClickListener = listener;
    }



    static class MyViewHolder extends RecyclerView.ViewHolder {
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pizza_items, parent,false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    @NonNull
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        //get pizza by position
        Pizza pizza = pizzaMap.get(position);
        //set item border color
//        GradientDrawable drawable = new GradientDrawable();
//        drawable.setCornerRadius(25);
//        drawable.setStroke(10, pizza.getColor());
        Drawable drawable = getSelectorDrawable(pizza.getColor());
        holder.itemView.setBackground(drawable);
        //set item's data
        //set prepraing time rectangle color and text
        GradientDrawable rectDrawable1 =  (GradientDrawable)holder.pizzaPreparingTime.getBackground();
        rectDrawable1.setColor(pizza.getColor());
        String preparingtimeString, bakingtimeString;
        preparingtimeString = new String(Integer.toString(pizza.getPreparingTime())+"\n");
        bakingtimeString = new String(Integer.toString(pizza.getBakingTime()) + "\n");
        if (pizza.getPreparingTime() <= 1 ) {
            preparingtimeString += "min";
        } else {
            preparingtimeString += "mins";
        }
        holder.pizzaPreparingTime.setText(preparingtimeString);

        //set baking time rectangle color and text
        GradientDrawable rectDrawable2 =  (GradientDrawable)holder.pizzaBakingTime.getBackground();
        rectDrawable2.setColor(pizza.getColor());
        if (pizza.getBakingTime() <= 1 ) {
            bakingtimeString += "min";
        } else {
            bakingtimeString += "mins";
        }
        holder.pizzaBakingTime.setText(bakingtimeString);

        // set checkIcon invisible
        holder.checkIcon.setVisibility(View.INVISIBLE);

        // set pizza icon color
        GradientDrawable ovalDrawable =  (GradientDrawable)holder.pizzaBorder.getBackground();
        ovalDrawable.setColor(pizza.getColor());

        if (onItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onItemClick(view, position);
                }
            });
        }

//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onItemClickListener.onItemClick(holder.itemView, position);
//            }
//        });

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

    /** 获取一个selector */
    public Drawable getSelectorDrawable(int color) {
        // normal background
        GradientDrawable drawable = new GradientDrawable();
        drawable.setCornerRadius(25);
        drawable.setStroke(10, color);
        drawable.setColor(Color.parseColor("#FFFFFF"));

        // background after a click
        GradientDrawable drawableClick = new GradientDrawable();
        drawableClick.setCornerRadius(25);
        drawableClick.setStroke(10, color);
        drawableClick.setColor(Color.parseColor("#FCE8D5"));

        StateListDrawable sld = new StateListDrawable();
        sld.addState(new int[] { -android.R.attr.state_pressed }, drawable);
        sld.addState(new int[] { android.R.attr.state_pressed }, drawableClick);
        return sld;
    }



}
