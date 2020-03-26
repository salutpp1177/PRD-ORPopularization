package com.example.orgame.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class PizzaList extends ArrayList<Pizza> implements Parcelable {

    public PizzaList() {

    }

    public PizzaList(Parcel parcel) {
        this.getFromParcel(parcel);
    }

    private void getFromParcel(Parcel parcel) {
        this.clear();
        int size = parcel.readInt();
        for (int i = 0; i < size; i++) {
            Pizza pizza = new Pizza();
            pizza.setId(parcel.readInt());
            pizza.setName(parcel.readString());
            pizza.setPreselectionPosition(parcel.readInt());
            pizza.setFlowshopPositon(parcel.readInt());
            pizza.setPreparingTime(parcel.readInt());
            pizza.setBakingTime(parcel.readInt());
            pizza.setJohnsonPosition(parcel.readInt());
            pizza.setIsChosen(parcel.readByte()!=0);
            this.add(pizza);
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        int size = this.size();
        parcel.writeInt(size);
        for (int j = 0; j < size; j++) {
            Pizza pizza = this.get(j);
            parcel.writeInt(pizza.getId());
            parcel.writeString(pizza.getName());
            parcel.writeInt(pizza.getPreselectionPosition());
            parcel.writeInt(pizza.getFlowshopPositon());
            parcel.writeInt(pizza.getPreparingTime());
            parcel.writeInt(pizza.getBakingTime());
            parcel.writeInt(pizza.getJohnsonPosition());
            parcel.writeInt(pizza.getColor());
            parcel.writeByte((byte) (pizza.getIsChosen() ? 1 : 0));
        }

    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        @Override
        public Object createFromParcel(Parcel parcel) {
            return new PizzaList(parcel);
        }

        @Override
        public Object[] newArray(int i) {
            return null;
        }
    };
}
