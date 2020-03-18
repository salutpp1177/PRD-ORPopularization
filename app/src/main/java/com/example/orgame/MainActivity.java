package com.example.orgame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.example.orgame.adapter.PizzaItemsAdapter;
import com.example.orgame.helper.OnItemTouchCallbackListener;
import com.example.orgame.helper.PizzaItemTouchHelperCallback;
import com.example.orgame.view.BottomTitleView;
import com.example.orgame.view.FlowshopOneView;
import com.example.orgame.view.FlowshopTwoView;
import com.example.orgame.view.TimeAxisView;
import com.example.orgame.viewmodel.FlowshopViewModel;
import com.example.orgame.viewmodel.PizzasListViewModel;
import com.example.orgame.viewmodel.TimerViewModel;
import com.example.orgame.model.Pizza;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // data
    List<Pizza> pizzaList;
    List<Pizza> pizzaListCopy;
    int chosenCounter = 0;
    int johnsonTime = 0;

    // view
    RecyclerView recyclerView;
    TextView hourNumberTextView;
    TextView minuteNumberTextView;
    FlowshopOneView flowshopOneView;
    FlowshopTwoView flowshopTwoView;
    ConstraintLayout flowshopConstraintLayout;
    TimeAxisView timeAxisOneView;
    TimeAxisView timeAxisTwoView;

    // button
    ImageButton replayButton;
    ImageButton keyButton;

    // adapter
    PizzaItemsAdapter pizzaItemsAdapter;

    // viewmodel
    TimerViewModel timerViewModel;
    PizzasListViewModel pizzasListViewModel;
    FlowshopViewModel flowshopViewModel;

    
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

        // set up the chos
        // en pizza window
        Log.d("onCreate: :", "chosen pizza window set-up ");

        // initialize ViewModel
        this.timerViewModel = new ViewModelProvider(this).get(TimerViewModel.class);
        this.pizzasListViewModel = new ViewModelProvider(this).get(PizzasListViewModel.class);
        this.flowshopViewModel = new ViewModelProvider(this).get(FlowshopViewModel.class);


        // initialize pizza's data
        this.pizzaList = pizzasListViewModel.getPizzas().getValue();
        this.pizzaListCopy = Algorithm.deepCopy(pizzaList);
        this.pizzaListCopy = Collections.unmodifiableList(pizzaListCopy);
        this.johnsonTime = Algorithm.calculateUsedTotalTime(Algorithm.solutionJohnson(pizzaList));
        Log.d("PIZZA", "onCreate: johnson time + "+Integer.toString(johnsonTime));

        // set up recyclerView
        this.recyclerView = findViewById(R.id.window_above_layout);
        this.recyclerView.setDescendantFocusability(ViewGroup.FOCUS_AFTER_DESCENDANTS);

        // set up recyclerView's layout
        LinearLayoutManager horizontalLayout = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
        this.recyclerView.setLayoutManager(horizontalLayout);

        //set up textView to show hour and minute
        this.hourNumberTextView = findViewById(R.id.hours_number);
        this.minuteNumberTextView = findViewById(R.id.mins_number);

        //set up all the buttons in this page
        this.replayButton = findViewById(R.id.replayButton);
        this.keyButton = findViewById(R.id.keyButton);

        // set flowshop view
        this.flowshopConstraintLayout = findViewById(R.id.flowshop_scrollview_layout);
        this.flowshopOneView = findViewById(R.id.flowshop_m1_view);
        this.flowshopTwoView = findViewById(R.id.flowshop_m2_view);

        //set axis in flowshop
        this.timeAxisOneView = findViewById(R.id.preparation_timeaxis_view);
        this.timeAxisTwoView = findViewById(R.id.baking_timeaxis_view);


        // set up adapter
        this.pizzaItemsAdapter = new PizzaItemsAdapter();


        // set up PizzasListViewModel
        pizzasListViewModel.getPizzas().observe(this, new Observer<List<Pizza>>() {
            @Override
            public void onChanged(List<Pizza> pizzas) {
                pizzaItemsAdapter.setPizzaList(pizzas);
                pizzaItemsAdapter.notifyDataSetChanged();

            }
        });

        // set up FlowshopViewModel
        flowshopViewModel.getFlowshop().observe(this, new Observer<List<Pizza>>() {
            @Override
            public void onChanged(List<Pizza> pizzas) {
                Log.d("PIZZA ", "onChanged: "+pizzas.toString());
                flowshopOneView.setFlowshopPizzas(pizzas);
                flowshopOneView.postInvalidate();
                flowshopTwoView.setFlowshopPizzas(pizzas);
                flowshopTwoView.postInvalidate();
            }
        });

        // set up TimeViewModel
        timerViewModel.getUsedTotalTime().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                hourNumberTextView.setText(Integer.toString(Algorithm.changeTimeFormat(integer)[0]));
                minuteNumberTextView.setText(Integer.toString(Algorithm.changeTimeFormat(integer)[1]));
                if(chosenCounter == 10) {
                    if(integer > johnsonTime) {
                        hourNumberTextView.setTextColor(Color.RED);
                        minuteNumberTextView.setTextColor(Color.RED);
                    } else {
                        hourNumberTextView.setTextColor(Color.GREEN);
                        minuteNumberTextView.setTextColor(Color.GREEN);
                    }
                }

                // reset the width of flowshop ConstraintLayout
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(integer*20+50, ViewGroup.LayoutParams.MATCH_PARENT);
                flowshopConstraintLayout.setLayoutParams(layoutParams);
                flowshopConstraintLayout.requestLayout();
                //reset axis below flowshop
                if(integer > 0 ) {
                    timeAxisOneView.setVisibility(View.VISIBLE);
                    timeAxisOneView.setUsedTime(integer);
                    timeAxisOneView.invalidate();
                    timeAxisTwoView.setVisibility(View.VISIBLE);
                    timeAxisTwoView.setUsedTime(integer);
                    timeAxisTwoView.invalidate();
                } else {
                    timeAxisOneView.setVisibility(View.INVISIBLE);
                    timeAxisTwoView.setVisibility(View.INVISIBLE);
                }


            }
        });


        // when click on replayButton : re-initialize the game but keep the data
        replayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pizzaList.clear();
                pizzaList = Algorithm.deepCopy(pizzaListCopy);
                pizzasListViewModel.resetPizzas(pizzaList);
                timerViewModel.updateUsedTotalTime(pizzaList);
                flowshopViewModel.updateFlowshopList(pizzaList);
                flowshopOneView.postInvalidate();
                flowshopTwoView.postInvalidate();
                chosenCounter = 0;
                hourNumberTextView.setTextColor(Color.BLACK);
                minuteNumberTextView.setTextColor(Color.BLACK);

            }
        });

        // when click on keyButton : show the solution of Johnson flowshop Algorithm
        keyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pizzaList = Algorithm.solutionJohnson(pizzaList);
                pizzasListViewModel.resetPizzas(pizzaList);
                timerViewModel.updateUsedTotalTime(pizzaList);
                flowshopViewModel.updateFlowshopList(pizzaList);
                flowshopOneView.postInvalidate();
                flowshopTwoView.postInvalidate();
                chosenCounter = 10;
                hourNumberTextView.setTextColor(Color.GREEN);
                minuteNumberTextView.setTextColor(Color.GREEN);
            }
        });


        // when click on pizza : choose or dismiss a pizza
        pizzaItemsAdapter.setOnItemClickListener(new PizzaItemsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                Pizza pizza = pizzaItemsAdapter.getPizzaList().get(position);

                Toast.makeText(MainActivity.this, pizza.getName(), Toast.LENGTH_LONG).show();
                if (pizza.getIsChosen()) {
                    view.findViewById(R.id.checkIcon).setVisibility(View.INVISIBLE);
                    chosenCounter--;
                } else {
                    view.findViewById(R.id.checkIcon).setVisibility(View.VISIBLE);
                    chosenCounter++;
                }
                if (chosenCounter < 10) {
                    hourNumberTextView.setTextColor(Color.BLACK);
                    minuteNumberTextView.setTextColor(Color.BLACK);
                }
                pizzasListViewModel.updatePizzaList(pizza);
                timerViewModel.updateUsedTotalTime(pizzaList);
                flowshopViewModel.updateFlowshopList(pizzaList);

                // log int Terminal
                String str = new String(pizza.getName() + ", " + "onItemClick: " + "Is chosen: "+pizza.getIsChosen() + ", position "+pizza.getPreselectionPosition());
                Toast a = Toast.makeText(getApplicationContext(), str, Toast.LENGTH_LONG);
                Log.d("RecyclerView", str);

            }
        });


        // connect adapter
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
                        // Replace the position of the data item in the data source.
                        // Change the position of the start and end positions in the list
                        Collections.swap(pizzaList, srcPosition, targetPosition);
                        Log.d("PIZZA MOVE", "onMove: from"+Integer.toString(srcPosition) + " TO "+ Integer.toString(targetPosition));
                        // Update the position of the item in the UI, mainly to show the user the interaction effect

                        pizzaItemsAdapter.notifyItemMoved(srcPosition, targetPosition);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return true;
                }
                return true;
            }

            @Override
            public void clearView() {
                if(!pizzaList.isEmpty()) {
                    pizzaItemsAdapter.notifyDataSetChanged();
                    timerViewModel.updateUsedTotalTime(pizzaList);
                    flowshopViewModel.updateFlowshopList(pizzaList);
                    flowshopOneView.postInvalidate();
                    flowshopTwoView.postInvalidate();
                }
            }
        });

        callback.setDragEnable(true);
        callback.setSwipeEnable(true);
        // Create helper objects, callbacks to monitor the various states of the recyclerView item
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
        try {
            // Associate recyclerView, a helper object can only correspond to one recyclerView
            itemTouchHelper.attachToRecyclerView(recyclerView);

        } catch (Exception e) {
            e.printStackTrace();
        }



    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("onStart :", "hello, I'm onStart  ，pizza counter： "+ Integer.toString(chosenCounter));
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("onPause:", "hello, I'm onPause  ，pizza counter：  "+ Integer.toString(chosenCounter));
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("onStop:", "hello, I'm onStop  ，pizza counter：  "+ Integer.toString(chosenCounter));
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
    }

    public List<Pizza> getPizzaList() {
        return pizzaList;
    }

    public void setPizzaList(List<Pizza> pizzaList) {
        this.pizzaList = pizzaList;
    }

    public List<Pizza> getPizzaListCopy() {
        return pizzaListCopy;
    }

    public void setPizzaListCopy(List<Pizza> pizzaListCopy) {
        this.pizzaListCopy = pizzaListCopy;
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

    public FlowshopViewModel getFlowshopViewModel() {
        return flowshopViewModel;
    }

    public void setFlowshopViewModel(FlowshopViewModel flowshopViewModel) {
        this.flowshopViewModel = flowshopViewModel;
    }

}
