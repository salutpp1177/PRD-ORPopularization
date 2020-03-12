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
        int value = calculateUsedTotalTime(flowshopPizzaList);
        this.usedTotalTime.setValue(value);
    }

    /**
     * After some modification in flowshop list, the total used time will be changed too
     * This function is to calculate the total used time
     *
     * @param pizzaInFlowshop
     * @return the total used time
     */
    public int calculateUsedTotalTime(List<Pizza> pizzaInFlowshop) {
        if (pizzaInFlowshop.isEmpty()) {
            return 0;
        }
        else if (pizzaInFlowshop.size() == 1) {
            return pizzaInFlowshop.get(0).getPreparingTime() + pizzaInFlowshop.get(0).getBakingTime();
        } else {
            List<Pizza> clone_flowshopPizzaList = Algorithm.deepCopy(pizzaInFlowshop);
            int sumPreparingTime = 0;
            int size = clone_flowshopPizzaList.size();
            for (int i = 0; i < size; i++) {
                sumPreparingTime += clone_flowshopPizzaList.get(i).getPreparingTime();
            }
            Pizza lastPizza = clone_flowshopPizzaList.get(size - 1);
            clone_flowshopPizzaList.remove(size - 1);
            int res = Math.max(sumPreparingTime, calculateUsedTotalTime(clone_flowshopPizzaList)) + lastPizza.getBakingTime();
            return res;
        }
    }

    public MutableLiveData<Integer> getUsedTotalTime() {

        return usedTotalTime;
    }

    public void setUsedTotalTime(MutableLiveData<Integer> usedTotalTime) {
        this.usedTotalTime = usedTotalTime;
    }



}
