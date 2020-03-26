package com.example.orgame.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.orgame.MainActivity;
import com.example.orgame.R;
import com.example.orgame.adapter.SpinnerItemsAdapter;
import com.example.orgame.model.Pizza;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FirstPageActivity extends AppCompatActivity {

    Spinner spinner;
    Button playButton;
    ArrayList<Pizza> pizzas;
    ImageView univLogo;
    ImageView polytechLogo;
    ImageView lifatLogo;
    SpinnerItemsAdapter spinnerItemsAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_page);
        // full screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Objects.requireNonNull(getSupportActionBar()).hide();

        // set up logos
        this.univLogo = this.findViewById(R.id.first_page_univlogo);
        this.polytechLogo = this.findViewById(R.id.polytech_logo);
        this.lifatLogo = this.findViewById(R.id.first_page_lifatlogo);

        // click univ logo
        this.univLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.univ-tours.fr"));
                startActivity(intent);
            }
        });

        // click polytech logo
        this.polytechLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://polytech.univ-tours.fr"));
                startActivity(intent);
            }
        });

        // click lifat logo
        this.lifatLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://lifat.univ-tours.fr"));
                startActivity(intent);
            }
        });

        pizzas = new ArrayList<>();
//        pizzas = (ArrayList<Pizza>)getIntent().getSerializableExtra("pizzaslist2");

        // set play button
        this.playButton = this.findViewById(R.id.play_button);
        this.playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FirstPageActivity.this, MainActivity.class);
                startActivity(intent);
//                intent.putExtra("data_return2",(Serializable)pizzas);
//                setResult(RESULT_OK, intent);
//                finish();
            }
        });

        // set up spinner
        String[] array = getResources().getStringArray(R.array.spinner_strings);
        this.spinner = findViewById(R.id.language_spinner);
        this.spinnerItemsAdapter = new SpinnerItemsAdapter(FirstPageActivity.this, R.layout.spinner_items, array);
        String prompt = getResources().getString(R.string.language_button);
        this.spinner.setPrompt(prompt);
        this.spinner.setAdapter(spinnerItemsAdapter);

    }

}
