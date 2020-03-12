package com.example.orgame.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.orgame.model.Pizza;

import java.util.ArrayList;
import java.util.List;

public class FlowshopViewModel extends ViewModel {
    MutableLiveData<List<Pizza>> flowshop;

    public FlowshopViewModel() {
        flowshop = new MutableLiveData<>();
        flowshop.setValue(new ArrayList<>());
    }


    public void updateFlowshopList(List<Pizza> list) {
        if(!this.flowshop.getValue().isEmpty()) {
            this.flowshop.getValue().clear();
        }
        if(!list.isEmpty()) {
            for (int i = 0; i < list.size(); i++) {
                Pizza pizza = list.get(i);
                if(pizza.getIsChosen()) {
                    this.flowshop.getValue().add(pizza);
                }
            }
        }

    }

    public MutableLiveData<List<Pizza>> getFlowshop() {
        return flowshop;
    }

    public void setFlowshop(MutableLiveData<List<Pizza>> flowshop) {
        this.flowshop = flowshop;
    }
}
