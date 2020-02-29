package com.example.orgame.model;

import android.graphics.Color;

public class Pizza {

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
        this.color = Color.parseColor(color_list[id]);
    }

    public Pizza(int id, int preparingTime, int bakingTime) {
        this.id = id;
        this.preparingTime = preparingTime;
        this.bakingTime = bakingTime;
        this.preselectionPosition = id;
        this.flowshopPositon = -1;
        this.color = Color.parseColor(color_list[id]);
        this.isChosen = false;
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
