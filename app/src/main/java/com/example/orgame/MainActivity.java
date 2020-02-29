package com.example.orgame;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.orgame.controller.Algorithm;
import com.example.orgame.controller.PizzaItemsAdapter;
import com.example.orgame.controller.PizzaViewModel;
import com.example.orgame.model.Pizza;
import java.util.Map;

public class MainActivity extends  AppCompatActivity {

//    ViewModelStore viewModelStore = new ViewModelStore();
//    @NonNull
//    @Override
//    public ViewModelStore getViewModelStore() {
//        return viewModelStore;
//    }

    Map<Integer, Pizza> pizzaMap;
    RecyclerView recyclerView;
    PizzaItemsAdapter pizzaItemsAdapter;
    View ChildView ;
    int RecyclerViewItemPosition ;
    //        RecyclerView.LayoutManager recyclerViewLayoutManager = new LinearLayoutManager(getApplicationContext());
//        this.recyclerView.setLayoutManager(recyclerViewLayoutManager);
    PizzaViewModel pizzaViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // create the page
        super.onCreate(savedInstanceState);
        // set up total canvas
        setContentView(R.layout.activity_main);
        // full screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        // set up the chosen pizza window
        Log.d("onCreate: :", "chosen pizza window set-up ");
        // initialize ViewModel
        this.pizzaViewModel = new ViewModelProvider(this).get(PizzaViewModel.class);

        // initialize pizza's data
        this.pizzaMap = pizzaViewModel.getPizzaMap().getValue();

        // set up recyclerView
        this.recyclerView = findViewById(R.id.window_above_layout);
        this.recyclerView.setDescendantFocusability(ViewGroup.FOCUS_AFTER_DESCENDANTS);

        // set up adapter
        this.pizzaItemsAdapter = new PizzaItemsAdapter(pizzaMap);
//        pizzaItemsAdapter.setOnItemClickListener(new PizzaItemsAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(View view, int position) {
//                Toast.makeText(MainActivity.this, pizzaMap.get(position).getName(),Toast.LENGTH_LONG).show();
//                if(view.findViewById(R.id.checkIcon).getVisibility() == View.VISIBLE) {
//                    view.findViewById(R.id.checkIcon).setVisibility(View.INVISIBLE);
//                } else if(view.findViewById(R.id.checkIcon).getVisibility() == View.INVISIBLE) {
//                    view.findViewById(R.id.checkIcon).setVisibility(View.VISIBLE);
//                }
//
//            }
//        });

        // connect adapter and view
        LinearLayoutManager horizontalLayout = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
        this.recyclerView.setLayoutManager(horizontalLayout);
        this.recyclerView.setAdapter(pizzaItemsAdapter);

        // set up ViewModel
        pizzaViewModel.getPizzaMap().observe(this, new Observer<Map<Integer, Pizza>>() {
            @Override
            public void onChanged(Map<Integer, Pizza> map) {
                pizzaItemsAdapter.setPizzaMap(map);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("onStart :", "hello, I'm onStart  ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("onPause:", "hello, I'm onPause   ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("onStop:", "hello, I'm onStop   ");
    }

    public Map<Integer, Pizza> getPizzaMap() {
        return pizzaMap;
    }

    public void setPizzaMap(Map<Integer, Pizza> pizzaMap) {
        this.pizzaMap = pizzaMap;
    }

    public PizzaViewModel getPizzaViewModel() {
        return pizzaViewModel;
    }

    public void setPizzaViewModel(PizzaViewModel pizzaViewModel) {
        this.pizzaViewModel = pizzaViewModel;
    }
}
