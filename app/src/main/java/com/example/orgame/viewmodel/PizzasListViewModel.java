package com.example.orgame.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.orgame.Algorithm;
import com.example.orgame.model.Pizza;

import java.util.List;

public class PizzasListViewModel extends ViewModel {

    private MutableLiveData<List<Pizza>> pizzas; /** the map of pizza*/

    /**
     * default constructor
     */
    public PizzasListViewModel() {
        pizzas = new MutableLiveData<>();
        pizzas.setValue(Algorithm.initAllPizzaList());
        initAllPizzaPreselectionPostion();
    }

    /**
     * default constructor
     */
    public PizzasListViewModel(List<Pizza> p) {
        pizzas.setValue(p);
        initAllPizzaPreselectionPostion();
    }

    /**
     * After choosing or dismissing a pizza in this window,
     * the pizza's list in this window will be changed,
     * We used @param pizzas to record the whole list in the window
     * @param pizza the pizza which is clicked
     */
    public void updatePizzaList(Pizza pizza) {
        int index = pizzas.getValue().indexOf(pizza);
        // is chosen
        Boolean status = pizzas.getValue().get(index).getIsChosen();
        if(status) {
            pizzas.getValue().get(index).setIsChosen(false);
        } else {
            pizzas.getValue().get(index).setIsChosen(true);
        }
    }

    /**
     * initialize the preselection postion of pizza
     * this postion is equal to its index in pizzaList
     */
    public void initAllPizzaPreselectionPostion() {
        if (!pizzas.getValue().isEmpty()) {
            int size = pizzas.getValue().size();
            for (int i = 0; i < size; i++) {
                pizzas.getValue().get(i).setPreselectionPosition(i);
            }
        }
    }


    /**
     * update data in the whole list
     * @return
     */
    public void updateWholeList() {
        if (!pizzas.getValue().isEmpty()) {
            int size = pizzas.getValue().size();
            for (int i = 0; i < size; i++) {
                pizzas.getValue().get(i).setPreselectionPosition(i);
            }
        }
    }


    public MutableLiveData<List<Pizza>> getPizzas() {
        return pizzas;
    }

    public void setPizzas(MutableLiveData<List<Pizza>> pizzas) {
        this.pizzas = pizzas;
    }
}
