package com.example.orgame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.example.orgame.adapter.PizzaItemsAdapter;
import com.example.orgame.helper.OnItemTouchCallbackListener;
import com.example.orgame.helper.PizzaItemTouchHelperCallback;
import com.example.orgame.viewmodel.PizzaViewModel;
import com.example.orgame.viewmodel.PizzasListViewModel;
import com.example.orgame.viewmodel.TimerViewModel;
import com.example.orgame.model.Pizza;

import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //    Map<Integer, Pizza> pizzaMap;
    List<Pizza> pizzaList;
    RecyclerView recyclerView;
    TextView hourNumberTextView;
    TextView minuteNumberTextView;
    PizzaItemsAdapter pizzaItemsAdapter;
    PizzaViewModel pizzaViewModel;
    TimerViewModel timerViewModel;
    PizzasListViewModel pizzasListViewModel;


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
        this.timerViewModel = new ViewModelProvider(this).get(TimerViewModel.class);
        this.pizzasListViewModel = new ViewModelProvider(this).get(PizzasListViewModel.class);


        // initialize pizza's data
//        this.pizzaMap = pizzaViewModel.getPizzaMap();
//        this.pizzaList = Algorithm.initAllPizzaList();
        this.pizzaList = pizzasListViewModel.getPizzas().getValue();

        // set up recyclerView
        this.recyclerView = findViewById(R.id.window_above_layout);
        this.recyclerView.setDescendantFocusability(ViewGroup.FOCUS_AFTER_DESCENDANTS);

        //set up textView to show hour and minute
        this.hourNumberTextView = findViewById(R.id.hours_number);
        this.minuteNumberTextView = findViewById(R.id.mins_number);

        // set up adapter
        this.pizzaItemsAdapter = new PizzaItemsAdapter();

        // set up TimeViewModel
        timerViewModel.getUsedTotalTime().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                hourNumberTextView.setText(Integer.toString(Algorithm.changeTimeFormat(integer)[0]));
                minuteNumberTextView.setText(Integer.toString(Algorithm.changeTimeFormat(integer)[1]));
            }
        });

        //set up PizzasListViewModel
        pizzasListViewModel.getPizzas().observe(this, new Observer<List<Pizza>>() {
            @Override
            public void onChanged(List<Pizza> pizzas) {
                pizzaItemsAdapter.setPizzaList(pizzas);
            }
        });

        // one click on pizza : choose or dismiss a pizza
        pizzaItemsAdapter.setOnItemClickListener(new PizzaItemsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                Pizza pizza = pizzaItemsAdapter.getPizzaList().get(position);

                Toast.makeText(MainActivity.this, pizza.getName(), Toast.LENGTH_LONG).show();
                if (pizza.getIsChosen()) {
                    view.findViewById(R.id.checkIcon).setVisibility(View.INVISIBLE);
                } else {
                    view.findViewById(R.id.checkIcon).setVisibility(View.VISIBLE);
                }
                pizzasListViewModel.updatePizzaList(pizza);
                timerViewModel.updateUsedTotalTime(pizzaList);
                pizzaItemsAdapter.notifyDataSetChanged();
                Log.d("RecyclerView", pizza.getName() + ", " + "onItemClick: " + "Is chosen: "+pizza.getIsChosen());

            }
        });

        // connect adapter and view
        LinearLayoutManager horizontalLayout = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
        this.recyclerView.setLayoutManager(horizontalLayout);
        this.recyclerView.setAdapter(pizzaItemsAdapter);



        // drag and drop in the RecyclerView
        PizzaItemTouchHelperCallback callback = new PizzaItemTouchHelperCallback(new OnItemTouchCallbackListener() {
            @Override
            public void onSwiped(int adapterPosition) {

            }

            @Override
            public boolean onMove(int srcPosition, int targetPosition) {
                if (pizzaList != null) {
                    try {
                        // 更换数据源中的数据Item的位置。更改list中开始和结尾position的位置
                        Collections.swap(pizzaList, srcPosition, targetPosition);
                        // 更新UI中的Item的位置，主要是给用户看到交互效果
                        pizzaItemsAdapter.notifyItemMoved(srcPosition, targetPosition);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return true;
                }
                return true;
            }
        });

        callback.setDragEnable(true);
        callback.setSwipeEnable(true);
        //创建helper对象，callback监听recyclerView item 的各种状态
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
        try {
            //关联recyclerView，一个helper对象只能对应一个recyclerView
            itemTouchHelper.attachToRecyclerView(recyclerView);
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    public PizzaViewModel getPizzaViewModel() {
        return pizzaViewModel;
    }

    public void setPizzaViewModel(PizzaViewModel pizzaViewModel) {
        this.pizzaViewModel = pizzaViewModel;
    }

    public List<Pizza> getPizzaList() {
        return pizzaList;
    }

    public void setPizzaList(List<Pizza> pizzaList) {
        this.pizzaList = pizzaList;
    }

    public TimerViewModel getTimerViewModel() {
        return timerViewModel;
    }

    public void setTimerViewModel(TimerViewModel timerViewModel) {
        this.timerViewModel = timerViewModel;
    }

    public PizzasListViewModel getPizzasListViewModel() {
        return pizzasListViewModel;
    }

    public void setPizzasListViewModel(PizzasListViewModel pizzasListViewModel) {
        this.pizzasListViewModel = pizzasListViewModel;
    }
}
