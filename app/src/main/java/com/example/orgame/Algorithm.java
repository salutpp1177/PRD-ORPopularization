package com.example.orgame;

import com.example.orgame.model.Pizza;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Algorithm {
    /**
     * change time format from MM mins to HH hours MM mins
     * @param time the origin time format : MM mins
     * @return the modified format : HH hours MM mins
     */
    public static int[] changeTimeFormat(int time) {
        int[] time_array = new int[2];
        time_array[0] = time / 60;
        time_array[1] = time % 60;
        return time_array;
    }


    /**
     * generate preparing time and baking time for each pizza one by one
     * @return
     */
    public static List<Pizza> initAllPizzaList() {
        List<Pizza> pizzaList = new ArrayList<>();
        pizzaList.add( new Pizza(0,5,20));
        pizzaList.add( new Pizza(1,10,25));
        pizzaList.add( new Pizza(2,20,10));
        pizzaList.add( new Pizza(3,10,25));
        pizzaList.add( new Pizza(4,15,25));
        pizzaList.add( new Pizza(5,25,10));
        pizzaList.add( new Pizza(6,20,10));
        pizzaList.add( new Pizza(7,15,20));
        pizzaList.add( new Pizza(8,5,10));
        pizzaList.add( new Pizza(9,20,5));
        return pizzaList;
    }

    /**
     * Java Object Deep Copy deepCopy(List src)
     * @return
     */
    public static List<Pizza> deepCopy(List<Pizza> pizzas) {
        if (!pizzas.isEmpty()){
            List<Pizza> res = new ArrayList<Pizza>();
            for (int i = 0; i < pizzas.size(); i++) {
                Pizza p = (Pizza)pizzas.get(i).clone();
                res.add(p);

            }
            return res;
        }
        return null;
    }

    /**
     * Calculate Max preparing time to mesure the size of time ruler
     * @param pizzaList
     * @return
     */
    public static int CalculateMaxPreparingTime(List<Pizza> pizzaList) {
        int res = 0;
        if(!pizzaList.isEmpty()) {
            for (Pizza p : pizzaList) {
                res += p.getPreparingTime();

            }
        }
        return res;
    }


}
