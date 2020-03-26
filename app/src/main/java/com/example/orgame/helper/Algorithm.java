package com.example.orgame.helper;

import com.example.orgame.model.Pizza;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

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
        Random random = new Random();
        int num = random.nextInt(5)+6; //number of pizza [6,10]
        for (int i = 0; i < num; i++) {
            int pre = 5*(random.nextInt(5)+1);
            int bak = 5*(random.nextInt(5)+1);
            pizzaList.add(new Pizza(i,pre,bak));
        }
//        pizzaList.add(new Pizza(0, 5, 20));
//        pizzaList.add(new Pizza(1, 10, 25));
//        pizzaList.add(new Pizza(2, 20, 10));
//        pizzaList.add(new Pizza(3, 10, 25));
//        pizzaList.add(new Pizza(4, 15, 25));
//        pizzaList.add(new Pizza(5, 25, 10));
//        pizzaList.add(new Pizza(6, 20, 10));
//        pizzaList.add(new Pizza(7, 15, 20));
//        pizzaList.add(new Pizza(8, 5, 10));
//        pizzaList.add(new Pizza(9, 20, 5));
        return pizzaList;
    }

    public static int[] checkList(List<Integer> list) {
        int[] check = {0,0,0,0,0};
        if(!list.isEmpty()) {
            for (Integer ele : list) {
                check[ele-1]++;
            }
        }
        return check;
    }

    /**
     * generate a new pizza list by setting preparing time and baking time one by one
     *
     * @return
     */
    public static List<Pizza> createPizzaList() {
        List<Pizza> pizzaList = new ArrayList<>();
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        int[] check1 = new int[5];
        int[] check2 = new int[5];
        Random random = new Random();
        int num = random.nextInt(5)+6; //number of pizza [6,10]
        int flag = 0;
        for (int i = 0; i < num; i++) {
            int ele1 = random.nextInt(5)+1;
            int ele2 = random.nextInt(5)+1;
            if(flag>1) {
                while (ele1==ele2) {
                    ele2 = random.nextInt(5)+1;
                }
                list1.add(ele1);
                list2.add(ele2);
            } else  {
                list1.add(ele1);
                list2.add(ele2);
                if (ele1==ele2)  {
                    flag++;
                }
            }
        }
        check1 = checkList(list1);
        check2 = checkList(list2);

        int i = 0;
        while(i<num) {
            int val = list1.get(i);
            if (check1[val-1]>2) {
                list1.set(i, random.nextInt(5)+1);
                if (list1.get(i) == val || check1[list1.get(i)-1]>2) {
                    continue;
                }
                check1 = checkList(list1);
            }

            if(num>8) {
                if(check1[list1.get(i)-1] > 3) {
                    continue;
                }
                else {
                    i++;
                }
            } else {
                if(check1[list1.get(i)-1] > 2) {
                    continue;
                }
                else {
                    i++;
                }
            }
        }

        int m = 0;
        while (m<num) {
            int val = list2.get(m);
            if (check2[val-1]>2) {
                int newval = random.nextInt(5)+1;
                if (newval == val || check2[newval-1]>2) {
                    continue;
                }
                if (newval == list1.get(m)) {
                    if (flag>1) {
                        continue;
                    } else {
                        flag++;
                    }
                }
                list2.set(m,newval);
                check2 = checkList(list2);
            }
            if(num>8) {
                if(check2[list2.get(m)-1] > 3) {
                    continue;
                }
                else {
                    m++;
                }
            } else {
                if(check2[list2.get(m)-1] > 2) {
                    continue;
                }
                else {
                    m++;
                }
            }
        }

        for (int j = 0; j <num ; j++) {
            int prepare = 5*list1.get(j);
            int baking = 5*list2.get(j);
            pizzaList.add(new Pizza(j, prepare, baking));
        }
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
     * Java Object Deep Copy deepCopy(List src)
     *
     * @return
     */
    public static List<Pizza> deepCopyInitPizzaList(List<Pizza> pizzas) {
        if (!pizzas.isEmpty()) {
            List<Pizza> res = new ArrayList<Pizza>();
            for (int i = 0; i < pizzas.size(); i++) {
                Pizza p = (Pizza) pizzas.get(i).clone();
                p.setIsChosen(false);
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
