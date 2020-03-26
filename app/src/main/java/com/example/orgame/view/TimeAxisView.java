package com.example.orgame.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.example.orgame.R;

import java.text.DecimalFormat;

/**
 * TODO: document your custom view class.
 */
public class TimeAxisView extends View {
    private int usedTime;
    private Paint linePaint;
    private Paint pointPaint;
    private Paint textPaint;
    private DecimalFormat df = new DecimalFormat("0.0");
    public TimeAxisView(Context context) {
        super(context);
        init(null, 0);
    }

    public TimeAxisView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public TimeAxisView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {
        // Load attributes
        final TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.TimeAxisView, defStyle, 0);
        usedTime = 60;
        // draw line
        linePaint = new Paint();
        linePaint.setColor(Color.BLACK);
        linePaint.setStrokeWidth((float)5.0);
        // draw points
        pointPaint = new Paint();
        pointPaint.setColor(Color.BLACK);
        pointPaint.setStrokeWidth((float)15.0);
        // draw coordinate number
        textPaint = new Paint();
        textPaint.setColor(Color.BLACK);
        textPaint.setTextSize((float)30);


    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        canvas.drawLine(xa,ya,xc,yc,paint)
//        canvas.drawLine(0,0,xc,0,paint); xc = xa + usedTime*20+30
        canvas.drawLine(0,5,usedTime*20+30,5, linePaint);
        if(usedTime > 30) {
            // origin point : 0
            canvas.drawText(Integer.toString(0), 0, 40 ,textPaint);

            int number = usedTime/30;
            for (int i = 1; i < number+1; i++) {
                // draw point
                canvas.drawPoint(i*600, 5, pointPaint);
                if(i%2==0) {
                    canvas.drawText(String.format("%dh", i/2), i*600, 40, textPaint);
                } else {
                    double a = (double) i/2.0;
                    canvas.drawText(String.format("%.1fh", a), i*600, 40, textPaint);
                }

            }


        } else {
            canvas.drawText(Integer.toString(0), 0, 40 ,textPaint);
        }

    }

    protected float[] calculatePointsAxis(int time) {
        int number  = 2*(time / 60 );
        float[] pointsAxis = new float[number];
        for (int i = 0; i < number; i+=2) {
            pointsAxis[i]=(i/2+1)*1200; //xa
            pointsAxis[i+1]=5; //ya
        }
        return pointsAxis;

    }


    public int getUsedTime() {
        return usedTime;
    }

    public void setUsedTime(int usedTime) {
        this.usedTime = usedTime;
    }

    public Paint getLinePaint() {
        return linePaint;
    }

    public void setLinePaint(Paint linePaint) {
        this.linePaint = linePaint;
    }

    public Paint getPointPaint() {
        return pointPaint;
    }

    public void setPointPaint(Paint pointPaint) {
        this.pointPaint = pointPaint;
    }

    public Paint getTextPaint() {
        return textPaint;
    }

    public void setTextPaint(Paint textPaint) {
        this.textPaint = textPaint;
    }
}
