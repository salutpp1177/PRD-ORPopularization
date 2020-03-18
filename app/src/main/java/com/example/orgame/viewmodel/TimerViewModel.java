package com.example.orgame.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.orgame.Algorithm;
import com.example.orgame.model.Pizza;

import java.util.ArrayList;
import java.util.List;

public class TimerViewModel extends ViewModel {

    private MutableLiveData<Integer> usedTotalTime; /** the total time which the player has used, in minute */
//    private MutableLiveData<SelectedPizzas> selectedList;

    /**
     * default constructor
     */
    public TimerViewModel() {
        usedTotalTime = new MutableLiveData<>();
        usedTotalTime.setValue(0);
    }


    public TimerViewModel(MutableLiveData<Integer> usedTotalTime) {
        this.usedTotalTime = usedTotalTime;
    }

    /**
     * update the @usedTotalTime in this class
     * @param list pizzalist
     */
    public void updateUsedTotalTime(List<Pizza> list) {
        //draw all the chosen pizza into a new list
        List<Pizza> flowshopPizzaList = new ArrayList<>();
        if(list.isEmpty() != true) {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getIsChosen()) {
                    Pizza pizza = (Pizza) list.get(i).clone();
                    flowshopPizzaList.add(pizza);
                }
            }
        }
        int value = Algorithm.calculateUsedTotalTime(flowshopPizzaList);
        this.usedTotalTime.setValue(value);
    }



    public MutableLiveData<Integer> getUsedTotalTime() {

        return usedTotalTime;
    }

    public void setUsedTotalTime(MutableLiveData<Integer> usedTotalTime) {
        this.usedTotalTime = usedTotalTime;
    }



}
