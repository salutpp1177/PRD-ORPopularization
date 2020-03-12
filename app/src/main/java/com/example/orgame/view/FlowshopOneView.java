package com.example.orgame.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import com.example.orgame.R;
import com.example.orgame.model.Pizza;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO: document your custom view class.
 */
public class FlowshopOneView extends View {
    private List<Pizza> flowshopPizzas;
    private Paint paint;

    public FlowshopOneView(Context context) {
        super(context);
        init(null, 0);
    }

    public FlowshopOneView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public FlowshopOneView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {
        // Load attributes
        final TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.FlowshopOneView, defStyle, 0);
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
            int size = flowshopPizzas.size();
            int xa = 0;
            int xc = flowshopPizzas.get(0).getPreparingTime() * 20;
            Rect rect0 = new Rect(xa, 0, xc, 400);
            paint.setColor(flowshopPizzas.get(0).getColor());
            canvas.drawRect(rect0, paint);
            if (flowshopPizzas.size() > 1) {
                for (int i = 1; i < flowshopPizzas.size(); i++) {
                    xa = xc;
                    xc += flowshopPizzas.get(i).getPreparingTime() * 20;
                    Rect rect = new Rect(xa, 0, xc, 400);
                    paint.setColor(flowshopPizzas.get(i).getColor());
                    canvas.drawRect(rect, paint);
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
