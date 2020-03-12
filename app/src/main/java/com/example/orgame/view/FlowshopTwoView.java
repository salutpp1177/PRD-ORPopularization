package com.example.orgame.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

import com.example.orgame.R;
import com.example.orgame.model.Pizza;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO: document your custom view class.
 */
public class FlowshopTwoView extends View {
    private List<Pizza> flowshopPizzas;
    private Paint paint;

    public FlowshopTwoView(Context context) {
        super(context);
        init(null, 0);
    }

    public FlowshopTwoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public FlowshopTwoView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {
        // Load attributes
        final TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.FlowshopTwoView, defStyle, 0);
        flowshopPizzas = new ArrayList<>();
        paint = new Paint();
        paint.setStyle(Paint.Style.FILL);

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (!flowshopPizzas.isEmpty()) {
            // flowshop list 1 which presents the preparing period
            // For each rectangle, Rect(xa, ya, xc, yc)
            // Rect(xa, 0, xc, 400)

            // The first rectangle
            int size = flowshopPizzas.size();
            int xa = flowshopPizzas.get(0).getPreparingTime()*20;
            int xc = xa + flowshopPizzas.get(0).getBakingTime()*20;
            Rect rect0 = new Rect(xa, 0, xc, 400);
            paint.setColor(flowshopPizzas.get(0).getColor());
            canvas.drawRect(rect0, paint);

            // The rest rectangles if chosen
            int sumPrepareTime = xa;
            int sumUsedTime = xc;
            int max = sumUsedTime;
            if (flowshopPizzas.size() > 1) {
                for (int i = 1; i < flowshopPizzas.size(); i++) {
                    sumPrepareTime += flowshopPizzas.get(i).getPreparingTime()*20;
                    max = Math.max(sumPrepareTime, sumUsedTime);
                    xa = max;
                    xc = xa + flowshopPizzas.get(i).getBakingTime()*20;
                    Rect rect = new Rect(xa,0,xc,400);
                    paint.setColor(flowshopPizzas.get(i).getColor());
                    canvas.drawRect(rect, paint);
                    sumUsedTime = xc;
                }

            }

        }
    }


    public List<Pizza> getFlowshopPizzas() {
        return flowshopPizzas;
    }

    public void setFlowshopPizzas(List<Pizza> flowshop) {
        this.flowshopPizzas = flowshop;
        invalidate();
    }

    public Paint getPaint() {
        return paint;
    }

    public void setPaint(Paint paint) {
        this.paint = paint;
    }
}
