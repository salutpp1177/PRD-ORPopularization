package com.example.orgame.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import androidx.fragment.app.Fragment;

import com.example.orgame.R;
import com.example.orgame.adapter.TabFragmentPageAdapter;
import com.google.android.material.tabs.TabLayout;

import java.util.Objects;

public class AboutUsActivity extends AppCompatActivity {

    Context context; // context

    Button backToMenuBtn; // button to

    TabLayout tabLayout; // tabLayout

    ViewPager viewPager; // viewpager contains tab items

    TabFragmentPageAdapter tabFragmentPageAdapter; // adapter

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        context = this;
        // full screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Objects.requireNonNull(getSupportActionBar()).hide();

        // set up button
        this.backToMenuBtn = this.findViewById(R.id.aboutus_back2menu_button);
        // set up tabLayout
        this.tabLayout = this.findViewById(R.id.tabLayout_aboutus);
        // set up fragments
        this.viewPager = this.findViewById(R.id.aboutus_viewpager);
        Fragment producedby = new Fragment(R.layout.tabitem_producedby);
        Fragment credit = new Fragment(R.layout.tabitem_credit);
        this.tabFragmentPageAdapter = new TabFragmentPageAdapter(getSupportFragmentManager(),0);
        this.tabFragmentPageAdapter.addFragment(producedby, context.getString(R.string.aboutus_produce));
        this.tabFragmentPageAdapter.addFragment(credit,context.getString(R.string.aboutus_credits));
        viewPager.setAdapter(tabFragmentPageAdapter);
        tabLayout.setupWithViewPager(viewPager);

        // when click, back to the menu page
        backToMenuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AboutUsActivity.this, MenuActivity.class);
                startActivity(intent);
            }
        });


    }
}
