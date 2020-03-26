package com.example.orgame.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;


import com.example.orgame.MainActivity;
import com.example.orgame.R;
import com.example.orgame.adapter.TutorialFragmentPageAdapter;
import com.example.orgame.model.Pizza;
import com.example.orgame.view.TutorialFragment;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;


public class JohnsonTutorialActivity extends AppCompatActivity {
    // button
    ImageButton JBackToGameButton;
    ImageButton JMenuButton;

    // data
    ArrayList<Pizza> pizzas;
    ArrayList<Fragment> fragments;

    // viewpager
    ViewPager viewPager;

    // adapter
    TutorialFragmentPageAdapter tutorialFragmentPageAdapter;

    // number of pages
    private static final int NUM_PAGES = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_johnson_tutorial);
        // full screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Objects.requireNonNull(getSupportActionBar()).hide();

        // set up buttons
        JBackToGameButton = this.findViewById(R.id.JBackToGameButton);
        JMenuButton = this.findViewById(R.id.JMenuButton);
        pizzas = new ArrayList<>();
        pizzas = (ArrayList<Pizza>)getIntent().getSerializableExtra("pizzaslist");

        // set up fragments
        viewPager = this.findViewById(R.id.jtutorial_viewPager);
        this.fragments = new ArrayList<>();
        TutorialFragment johnson_page1 = new TutorialFragment(R.layout.fragment_explain_page_one);
        TutorialFragment johnson_page2 = new TutorialFragment(R.layout.fragment_explain_page_two);
        TutorialFragment johnson_page3 = new TutorialFragment(R.layout.fragment_explain_page_three);
        TutorialFragment johnson_page4 = new TutorialFragment(R.layout.fragment_explain_page_four);
        this.fragments.add(johnson_page1);
        this.fragments.add(johnson_page2);
        this.fragments.add(johnson_page3);
        this.fragments.add(johnson_page4);
        tutorialFragmentPageAdapter = new TutorialFragmentPageAdapter(getSupportFragmentManager(), 0,fragments);
        viewPager.setAdapter(tutorialFragmentPageAdapter);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });




        // click backToGameButton
        JBackToGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(JohnsonTutorialActivity.this, MainActivity.class);
                intent.putExtra("data_return",(Serializable)pizzas);
                setResult(RESULT_OK, intent);
                finish();

            }
        });

    }

    public ArrayList<Pizza> getPizzas() {
        return pizzas;
    }

    public void setPizzas(ArrayList<Pizza> pizzas) {
        this.pizzas = pizzas;
    }
}
