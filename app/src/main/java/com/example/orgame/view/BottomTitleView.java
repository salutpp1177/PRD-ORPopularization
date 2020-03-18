package com.example.orgame.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.orgame.R;

import java.util.ArrayList;

/**
 * TODO: document your custom view class.
 */
public class BottomTitleView extends ConstraintLayout {
    TextView textView;
    ImageView imageView;

    public BottomTitleView(Context context) {
        this(context,null);
        init(null, 0);
        initView(context);
    }

    public BottomTitleView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
        init(attrs, 0);
        initView(context);
    }

    public BottomTitleView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
        initView(context);

    }

    private void init(AttributeSet attrs, int defStyle) {
        // Load attributes
        final TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.BottomTitleView, defStyle, 0);

    }


    private void initView(final Context context) {
        LayoutInflater.from(context).inflate(R.layout.sample_bottom_title_view, this, true);
        textView = findViewById(R.id.all_rights_reserved);
        imageView = findViewById(R.id.polytech_logo);

    }


}
