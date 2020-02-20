package com.example.orgame;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import com.example.orgame.controller.Algorithm;
import com.example.orgame.controller.PizzaItemsAdapter;
import com.example.orgame.model.Pizza;
import java.util.Map;

public class MainActivity extends Activity {

    Map<Integer, Pizza> pizzaMap;
    RecyclerView recyclerView;
    PizzaItemsAdapter pizzaItemsAdapter;
    View childView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // create the page
        super.onCreate(savedInstanceState);
        // full screen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        // set up total canvas
        setContentView(R.layout.activity_main);

        // set up the chosen pizza window
        Log.d("Step 1 :", "onCreate: chosen pizza window set-up ");
        // initialize pizza's data
        this.pizzaMap = Algorithm.initAllPizza();
        // set up recyclerView
        this.recyclerView = findViewById(R.id.window_above_layout);
//        RecyclerView.LayoutManager recyclerViewLayoutManager = new LinearLayoutManager(getApplicationContext());
//        this.recyclerView.setLayoutManager(recyclerViewLayoutManager);
        // set up adapter
        this.pizzaItemsAdapter = new PizzaItemsAdapter(pizzaMap);
        // connect adapter and view
        LinearLayoutManager horizontalLayout = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
        this.recyclerView.setLayoutManager(horizontalLayout);
        this.recyclerView.setAdapter(pizzaItemsAdapter);

//        recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
//            @Override
//            public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
//                return false;
//            }
//
//            @Override
//            public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
//
//            }
//
//            @Override
//            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
//
//            }
//        });
    }


    public Map<Integer, Pizza> getPizzaMap() {
        return pizzaMap;
    }

    public void setPizzaMap(Map<Integer, Pizza> pizzaMap) {
        this.pizzaMap = pizzaMap;
    }


}
