package com.example.orgame.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.example.orgame.MainActivity;
import com.example.orgame.R;

import java.util.Objects;

public class MenuActivity extends AppCompatActivity {
    Button flowshowPizzaButton;
    Button questionButton;
    Button aboutUsButton;
    Button languageButton;
    Button introButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        // full screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Objects.requireNonNull(getSupportActionBar()).hide();


        //setup button
        this.flowshowPizzaButton = findViewById(R.id.button_flowshop_pizza);
        this.aboutUsButton = findViewById(R.id.button_aboutus);
        this.introButton = findViewById(R.id.button_introduction);

        /**
         * click the flowshop-pizza button, move to the "flowshop-pizza" game page
         */
        this.flowshowPizzaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        /**
         * click the about-us button, move to about-us page
         */
        this.aboutUsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuActivity.this, AboutUsActivity.class);
                startActivity(intent);
            }
        });

        /**
         * click the about-us button, move to introduction page
         */
        this.introButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuActivity.this, IntroductionActivity.class);
                startActivity(intent);
            }
        });


    }
}
