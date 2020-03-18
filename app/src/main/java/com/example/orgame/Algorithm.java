package com.example.orgame;

import com.example.orgame.model.Pizza;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Algorithm {
    /**
     * change time format from MM mins to HH hours MM mins
     *
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
     *
     * @return
     */
    public static List<Pizza> initAllPizzaList() {
        List<Pizza> pizzaList = new ArrayList<>();
        pizzaList.add(new Pizza(0, 5, 20));
        pizzaList.add(new Pizza(1, 10, 25));
        pizzaList.add(new Pizza(2, 20, 10));
        pizzaList.add(new Pizza(3, 10, 25));
        pizzaList.add(new Pizza(4, 15, 25));
        pizzaList.add(new Pizza(5, 25, 10));
        pizzaList.add(new Pizza(6, 20, 10));
        pizzaList.add(new Pizza(7, 15, 20));
        pizzaList.add(new Pizza(8, 5, 10));
        pizzaList.add(new Pizza(9, 20, 5));
        return pizzaList;
    }

    /**
     * Java Object Deep Copy deepCopy(List src)
     *
     * @return
     */
    public static List<Pizza> deepCopy(List<Pizza> pizzas) {
        if (!pizzas.isEmpty()) {
            List<Pizza> res = new ArrayList<Pizza>();
            for (int i = 0; i < pizzas.size(); i++) {
                Pizza p = (Pizza) pizzas.get(i).clone();
                res.add(p);

            }
            return res;
        }
        return null;
    }

    /**
     * Calculate Max preparing time to mesure the size of time ruler
     *
     * @param pizzas
     * @return
     */
    public static int CalculateMaxPreparingTime(List<Pizza> pizzas) {
        List<Pizza> pizzaList = Algorithm.deepCopy(pizzas);
        int res = 0;
        if (!pizzaList.isEmpty()) {
            for (Pizza p : pizzaList) {
                res += p.getPreparingTime();

            }
        }
        return res;
    }

    /**
     * After some modification in flowshop list, the total used time will be changed too
     * This function is to calculate the total used time
     *
     * @param pizzaInFlowshop
     * @return the total used time
     */
    public static int calculateUsedTotalTime(List<Pizza> pizzaInFlowshop) {
        if (pizzaInFlowshop.isEmpty()) {
            return 0;
        } else {
            if (pizzaInFlowshop.size() == 1) {
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
    }

    /**
     * calculate the list of pizza by Johnson Algorithm
     *
     * @param pizzas
     * @return
     */
    public static List<Pizza> solutionJohnson(List<Pizza> pizzas) {
        List<Pizza> pizzaList = Algorithm.deepCopy(pizzas);
        List<Pizza> listOne = new ArrayList<>(); // Preparing Time < Baking  Time
        List<Pizza> listTwo = new ArrayList<>(); // Preparing Time >= Baking  Time
        for (Pizza p : pizzaList) {
            if (p.getPreparingTime() < p.getBakingTime()) {
                p.setIsChosen(true);
                listOne.add(p);
            } else {
                p.setIsChosen(true);
                listTwo.add(p);
            }
        }

        // sort listOne in Ascending order of their preparing time
        if (!listOne.isEmpty()) {
            Collections.sort(listOne, new Comparator<Pizza>() {
                @Override
                public int compare(Pizza pizza1, Pizza pizza2) {
                    int diff = pizza1.getPreparingTime() - pizza2.getPreparingTime();
                    if (diff > 0) {
                        return 1;
                    } else if (diff < 0) {
                        return -1;
                    }
                    return 0;
                }
            });
        }


        // sort listTwo in Descending order of their baking time
        if (!listTwo.isEmpty()) {
            Collections.sort(listTwo, new Comparator<Pizza>() {
                @Override
                public int compare(Pizza pizza1, Pizza pizza2) {
                    int diff = pizza1.getBakingTime() - pizza2.getBakingTime();
                    if (diff < 0) {
                        return 1;
                    } else if (diff > 0) {
                        return -1;
                    }
                    return 0;
                }
            });
            listOne.addAll(listTwo);
        }

        return listOne;
    }


}
