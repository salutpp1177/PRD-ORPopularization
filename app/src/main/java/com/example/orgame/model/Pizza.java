package com.example.orgame.model;

import android.graphics.Color;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Pizza implements Cloneable, Serializable {

    public static String[] color_list= { "#F69531", "#59F8B4", "#59E9FD", "#D18CF0", "#4E5AE6", "#A7AAAC",
            "#F281A8", "#F82828", "#FCDC5E", "#19A89B" };

    private int id; /** pizza's Id */
    private String name; /** pizza's name, form as Pizza?(id) */
    private int preselectionPosition; /** the position of pizza in preselection */
    private int flowshopPositon; /** the position of pizza in flowshop */
    private int preparingTime; /** the preparing time of the pizza, unity in  minutes */
    private int bakingTime; /**  the baking time of the pizza, unity in minutes */
    private int johnsonPosition; /** the position of pizza by Johnson Algorithm's solution */
    private int color; /** the color of the pizza */
    private boolean isChosen; /** tell whether the pizza is chosen or not */


    /**
     * constructor
     */
    public Pizza() {
    }

    public Pizza(int id) {
        this.setId(id);
    }

    public Pizza(int id, int preparingTime, int bakingTime) {
        this.setId(id);
        this.preparingTime = preparingTime;
        this.bakingTime = bakingTime;
        this.preselectionPosition = id;
        this.flowshopPositon = -1;
        this.color = Color.parseColor(color_list[id]);
        this.isChosen = false;
    }



    @NonNull
    @Override
    public Object clone() {
        try{
            Pizza pizza = (Pizza) super.clone();
            return pizza;
        }catch (CloneNotSupportedException e){
            return null;
        }
    }

//    @Override
//    public void writeToParcel(Parcel parcel, int i) {
//        parcel.writeInt(id);
//        parcel.writeString(name);
//        parcel.writeInt(preselectionPosition);
//        parcel.writeInt(flowshopPositon);
//        parcel.writeInt(preparingTime);
//        parcel.writeInt(bakingTime);
//        parcel.writeInt(johnsonPosition);
//        parcel.writeInt(color);
//        parcel.writeByte((byte) (isChosen ? 1 : 0));
//    }


//    public static final Parcelable.Creator<Pizza> CREATOR = new Parcelable.Creator<Pizza>() {
//        /**
//         * Returns a single instance that implements the Parcelable interface
//         */
//        public Pizza createFromParcel(Parcel source){
//            Pizza pizza = new Pizza();
//            pizza.setId(source.readInt());
//            pizza.setName(source.readString());
//            pizza.setPreselectionPosition(source.readInt());
//            pizza.setFlowshopPositon(source.readInt());
//            pizza.setPreparingTime(source.readInt());
//            pizza.setBakingTime(source.readInt());
//            pizza.setJohnsonPosition(source.readInt());
//            pizza.setIsChosen(source.readByte()!=0);
//            return pizza;
//        }
//        /**
//         * Returns multiple instances that implement the Parcelable interface
//         */
//        public Pizza[] newArray(int size){
//            return new Pizza[size];
//        }
//
//    };
//
//    @Override
//    public int describeContents() {
//        return 0;
//    }

    @Override
    public String toString() {
        return "Pizza{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", preparingTime=" + preparingTime +
                ", bakingTime=" + bakingTime +
                ", isChosen=" + isChosen +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
        this.name = new String("Pizza"+id);
    }

    public String getName() {
        return name;
    }

    public void setName(int id) {
        this.name = new String("pizza"+id);
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPreselectionPosition() {
        return preselectionPosition;
    }

    public void setPreselectionPosition(int preselectionPosition) {
        this.preselectionPosition = preselectionPosition;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }


    public int getFlowshopPositon() {
        return flowshopPositon;
    }

    public void setFlowshopPositon(int flowshopPositon) {
        this.flowshopPositon = flowshopPositon;
    }

    public int getPreparingTime() {
        return preparingTime;
    }

    public void setPreparingTime(int preparingTime) {
        this.preparingTime = preparingTime;
    }

    public int getBakingTime() {
        return bakingTime;
    }

    public void setBakingTime(int bakingTime) {
        this.bakingTime = bakingTime;
    }

    public int getJohnsonPosition() {
        return johnsonPosition;
    }

    public void setJohnsonPosition(int johnsonPosition) {
        this.johnsonPosition = johnsonPosition;
    }

    public boolean getIsChosen() {
        return isChosen;
    }

    public void setIsChosen(boolean isChosen) {
        this.isChosen = isChosen;
    }
}
