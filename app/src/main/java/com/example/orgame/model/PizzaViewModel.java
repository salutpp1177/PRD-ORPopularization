package com.example.orgame.model;

import androidx.lifecycle.ViewModel;

import com.example.orgame.controller.Algorithm;

import java.util.Map;

public class PizzaViewModel extends ViewModel {

    public Map<Integer, Pizza> pizzaMap = Algorithm.initAllPizza(); /** the map of pizza */
    public int used_total_time = 0; /** the total time which the player has used, in minute */
    public int score = 0; /** the final score gained by player */

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
}
