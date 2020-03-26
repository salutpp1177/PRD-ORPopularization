package com.example.orgame.view;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.orgame.MainActivity;
import com.example.orgame.R;

public class MainActivityMenu extends PopupWindow {
    ImageButton newgameBtn;
    ImageButton tutorialBtn;

    public MainActivityMenu(@NonNull Context context) {
        super(context);
        View view = LayoutInflater.from(context).inflate(R.layout.sample_attach_menu, null, false);
        newgameBtn = view.findViewById(R.id.newgame_button);
        tutorialBtn = view.findViewById(R.id.tutorial_button);

        setOutsideTouchable(true);
        setFocusable(true);
        setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return false;
            }
        });
        setBackgroundDrawable(new ColorDrawable(0xffffff3f));

        // click button Tutorial
        tutorialBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Going to tutorial", Toast.LENGTH_SHORT).show();
                dismiss();
            }
        });

        // click button NewGame
        newgameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "New Game", Toast.LENGTH_SHORT).show();
                dismiss();
            }
        });
        setContentView(view);

    }

    public MainActivityMenu() {
    }

}
