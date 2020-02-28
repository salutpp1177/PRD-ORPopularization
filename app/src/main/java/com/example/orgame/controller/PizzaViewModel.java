package com.example.orgame.controller;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.orgame.model.Pizza;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PizzaViewModel extends ViewModel {

//    private Map<Integer, Pizza> pizzaMap; /** the map of pizza */
    private MutableLiveData<Map<Integer,Pizza>> pizzaMap; /** the map of pizza*/
    private MutableLiveData<Integer> usedTotalTime; /** the total time which the player has used, in minute */
//    private MutableLiveData<Integer> usedTotalPreparingTime; /** the total preparing time which the player has used, in minute */
    private MutableLiveData<List<Pizza>> flowshopPizzaList; /** the pizzas which have been set into flowshop window */
    public int score = 0; /** the final score gained by player */
//    private List<Pizza> flowshopPizzaList; /** the pizzas which have been set into flowshop window */

    /**
     * default constructor
     */
    public PizzaViewModel() {
//        pizzaMap = Algorithm.initAllPizza();
        pizzaMap = new MutableLiveData<>();
        pizzaMap.setValue(Algorithm.initAllPizza());
        usedTotalTime = new MutableLiveData<>();
        usedTotalTime.setValue(0);
//        usedTotalPreparingTime = new MutableLiveData<>();
//        usedTotalPreparingTime.setValue(0);
    }

    /**
     * default constructor
     */
    public PizzaViewModel(Map<Integer, Pizza> map) {
        pizzaMap = new MutableLiveData<>();
        pizzaMap.setValue(map);
        usedTotalTime = new MutableLiveData<>();
        usedTotalTime.setValue(0);
//        usedTotalPreparingTime = new MutableLiveData<>();
//        usedTotalPreparingTime.setValue(0);
    }


    /**
     * After some modification in flowshop list, the total used time will be changed too
     * This function is to calculate the total used time
     * @param pizzaInFlowshop
     * @return the total used time
     */
    public int calculateUsedTotalTime(List<Pizza> pizzaInFlowshop) {
        if(pizzaInFlowshop.size() == 1) {
            return pizzaInFlowshop.get(0).getPreparingTime() + pizzaInFlowshop.get(0).getBakingTime();
        } else {
            List<Pizza> clone_flowshopPizzaList = new ArrayList<Pizza>(pizzaInFlowshop);
            int sumPreparingTime = 0 ;
            int size = clone_flowshopPizzaList.size();
            for (int i = 0; i < size; i++) {
                sumPreparingTime += clone_flowshopPizzaList.get(i).getPreparingTime();
            }
            Pizza lastPizza = clone_flowshopPizzaList.get(size-1);
            clone_flowshopPizzaList.remove(size-1);
            int res = Math.max(sumPreparingTime, calculateUsedTotalTime(clone_flowshopPizzaList)) + lastPizza.getBakingTime();
            return res;
        }
    }


    /**
     * After adding a pizza into flowshop window,
     * the pizza's list in this window will be changed,
     * the total used time will be changed too.
     * We used @param flowshopPizzaList to record the present pizza list in the window
     * @param pizza the pizza which is going to be add
     */
    public void addFlowshopPizzalist(Pizza pizza) {
        if(flowshopPizzaList.getValue().isEmpty()) {
            flowshopPizzaList.setValue(new ArrayList<Pizza>());
            flowshopPizzaList.getValue().add(pizza);
            pizza.setFlowshopPositon(0);
        } else {
            flowshopPizzaList.getValue().add(pizza);
            pizza.setFlowshopPositon(flowshopPizzaList.getValue().size() - 1);
        }
        this.usedTotalTime.setValue(calculateUsedTotalTime(flowshopPizzaList.getValue()));
    }

    /**
     * After removing a pizza into flowshop window,
     * the pizza's list in this window will be changed,
     * the total used time will be changed too.
     * We used @param flowshopPizzaList to record the present pizza list in the window
     * @param pizza the pizza which is going to be remove
     * @param position the pizza's position
     */
    public void removeFlowshopPizzalist(Pizza pizza, int position) {
        flowshopPizzaList.getValue().remove(position);

        if(flowshopPizzaList.getValue().isEmpty()) {
            this.usedTotalTime.setValue(0);
        } else {
            if(position != flowshopPizzaList.getValue().size()) {
                for (int i=position; i<flowshopPizzaList.getValue().size(); i++) {
                    int before = flowshopPizzaList.getValue().get(position).getFlowshopPositon();
                    flowshopPizzaList.getValue().get(position).setFlowshopPositon(before-1);
                }
            }
            this.usedTotalTime.setValue(calculateUsedTotalTime(flowshopPizzaList.getValue()));
        }
    }

    /**
     * After modifying a pizza 's position in this window,
     * the pizza's list in this window will be changed,
     * the total used time will be changed too.
     * We used @param fflowshopPizzaList to record the present pizza list in the window
     * @param pizza the pizza which is going to be add
     * @param before the position before update
     * @param after the postion after update
     */
    public void updateFlowshopPizzalist(Pizza pizza, int before, int after) {
        if (before != after) {
            flowshopPizzaList.getValue().remove(before);
            flowshopPizzaList.getValue().add(after,pizza);
            for (int i=0; i<flowshopPizzaList.getValue().size(); i++) {
                flowshopPizzaList.getValue().get(i).setFlowshopPositon(i);
            }
        }
        this.usedTotalTime.setValue(calculateUsedTotalTime(flowshopPizzaList.getValue()));
    }

    public MutableLiveData<Map<Integer, Pizza>> getPizzaMap() {
        return pizzaMap;
    }

    public void setPizzaMap(MutableLiveData<Map<Integer, Pizza>> pizzaMap) {
        this.pizzaMap = pizzaMap;
    }

    public MutableLiveData<Integer> getUsedTotalTime() {
        return usedTotalTime;
    }

    public void setUsedTotalTime(MutableLiveData<Integer> usedTotalTime) {
        this.usedTotalTime = usedTotalTime;
    }

//    public MutableLiveData<Integer> getUsedTotalPreparingTime() {
//        return usedTotalPreparingTime;
//    }
//
//    public void setUsedTotalPreparingTime(MutableLiveData<Integer> usedTotalPreparingTime) {
//        this.usedTotalPreparingTime = usedTotalPreparingTime;
//    }

    public MutableLiveData<List<Pizza>> getFlowshopPizzaList() {
        return flowshopPizzaList;
    }

    public void setFlowshopPizzaList(MutableLiveData<List<Pizza>> flowshopPizzaList) {
        this.flowshopPizzaList = flowshopPizzaList;
    }
}
