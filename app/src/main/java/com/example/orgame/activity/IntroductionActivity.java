package com.example.orgame.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.example.orgame.R;
import com.example.orgame.adapter.TabFragmentPageAdapter;
import com.google.android.material.tabs.TabLayout;

import java.util.Objects;

public class IntroductionActivity extends AppCompatActivity {

    Context context; // context

    Button backToMenuBtn; // button to

    TabLayout tabLayout; // tabLayout

    ViewPager viewPager; // viewpager contains tab items

    TabFragmentPageAdapter tabFragmentPageAdapter; // adapter

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduction);
        context = this;
        // full screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Objects.requireNonNull(getSupportActionBar()).hide();

        // set up button
        this.backToMenuBtn = this.findViewById(R.id.intro_back2menu);
        // set up tabLayout
        this.tabLayout = this.findViewById(R.id.tabLayout_intro);
        // set up fragments
        this.viewPager = this.findViewById(R.id.intro_viewpager);
        Fragment or = new Fragment(R.layout.tabitem_or);
        Fragment polytech = new Fragment(R.layout.tabitem_polytech);
        Fragment lifat = new Fragment(R.layout.tabitem_lifat);
        this.tabFragmentPageAdapter = new TabFragmentPageAdapter(getSupportFragmentManager(),0);
        this.tabFragmentPageAdapter.addFragment(or, context.getString(R.string.intro_tabitem1));
        this.tabFragmentPageAdapter.addFragment(polytech,context.getString(R.string.intro_tabitem2));
        this.tabFragmentPageAdapter.addFragment(lifat,context.getString(R.string.intro_tabitem3));
        viewPager.setAdapter(tabFragmentPageAdapter);
        tabLayout.setupWithViewPager(viewPager);

        // when click, back to the menu page
        backToMenuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(IntroductionActivity.this, MenuActivity.class);
                startActivity(intent);
            }
        });
    }
}
