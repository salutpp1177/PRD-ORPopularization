package com.example.orgame.adapter;

import android.graphics.Color;
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

import java.util.Collections;
import java.util.List;

public class PizzaItemsAdapter extends RecyclerView.Adapter<PizzaItemsAdapter.MyViewHolder> {

//    private Map<Integer,Pizza> pizzaMap;
    private List<Pizza> pizzaList;

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

    private OnItemClickListener onItemClickListener = null;

    /*暴露给外部的方法*/
    public void setOnItemClickListener(OnItemClickListener listener) {
        onItemClickListener = listener;
    }

    public interface OnItemClickListener{
        void onItemClick(View view, int position);
    }

//    public PizzaItemsAdapter(Map<Integer,Pizza> map) {
//        this.pizzaMap = map;
//    }

    public PizzaItemsAdapter() {

    }

    public PizzaItemsAdapter(List<Pizza> pizzaList) {
        this.pizzaList = pizzaList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pizza_items, parent,false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    @NonNull
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        //get pizza by position
//        Pizza pizza = pizzaMap.get(position);
        Pizza pizza = pizzaList.get(position);
        //set item border color
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
        if(pizza.getIsChosen()) {
            holder.checkIcon.setVisibility(View.VISIBLE);
        } else {
            holder.checkIcon.setVisibility(View.INVISIBLE);
        }


        // set pizza icon color
        GradientDrawable ovalDrawable =  (GradientDrawable)holder.pizzaBorder.getBackground();
        ovalDrawable.setColor(pizza.getColor());

//            holder.itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
////                    onItemClickListener.onItemClick(view, position);
//                    if(pizzaList.get(position).getIsChosen()) {
////                            view.findViewById(R.id.checkIcon).setVisibility(View.INVISIBLE);
//                            pizzaList.get(position).setIsChosen(false);
//                    } else {
////                            view.findViewById(R.id.checkIcon).setVisibility(View.VISIBLE);
//                            pizzaList.get(position).setIsChosen(true);
//                    }
//
//                }
//            });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClick(holder.itemView, position);
            }
        });

    }

    @Override
    public int getItemCount() {
//        return pizzaMap.size();
        return pizzaList.size();
    }


    /**
     * create a selector drawable to reach the effect of changing the item's background color after clicking
     */
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


    public void onMove(int fromPosition, int toPosition) {
        //对原数据进行移动
        Collections.swap(pizzaList, fromPosition, toPosition);
        //通知数据移动
        notifyItemMoved(fromPosition, toPosition);
    }


    public List<Pizza> getPizzaList() {
        return pizzaList;
    }

    public void setPizzaList(List<Pizza> pizzaList) {
        this.pizzaList = pizzaList;
    }
}