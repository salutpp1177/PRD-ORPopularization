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
    public static Map<Integer, Pizza> initAllPizza() {
        Map<Integer, Pizza> pizzaMap = new HashMap<>();
        pizzaMap.put(0, new Pizza(0,40,50));
        pizzaMap.put(1, new Pizza(1,30,50));
        pizzaMap.put(2, new Pizza(2,20,50));
        pizzaMap.put(3, new Pizza(3,50,10));
        pizzaMap.put(4, new Pizza(4,60,50));
        pizzaMap.put(5, new Pizza(5,55,20));
        pizzaMap.put(6, new Pizza(6,40,25));
        pizzaMap.put(7, new Pizza(7,41,34));
        pizzaMap.put(8, new Pizza(8,42,23));
        pizzaMap.put(9, new Pizza(9,43,14));
        return pizzaMap;
    }

    /**
     * generate preparing time and baking time for each pizza one by one
     * @return
     */
    public static List<Pizza> initAllPizzaList() {
        List<Pizza> pizzaList = new ArrayList<>();
        pizzaList.add( new Pizza(0,1,4));
        pizzaList.add( new Pizza(1,2,1));
        pizzaList.add( new Pizza(2,4,2));
        pizzaList.add( new Pizza(3,2,5));
        pizzaList.add( new Pizza(4,60,50));
        pizzaList.add( new Pizza(5,55,20));
        pizzaList.add( new Pizza(6,40,25));
        pizzaList.add( new Pizza(7,41,34));
        pizzaList.add( new Pizza(8,42,23));
        pizzaList.add(new Pizza(9,43,14));
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


}
