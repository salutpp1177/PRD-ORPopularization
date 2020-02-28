package com.example.orgame.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.orgame.R;

public class TimeRulerProgressBar extends ProgressBar {
    private static final int DEFAULT_REACH_COLOR = 0xff000000;
    private static final int DEFAULT_UNREACH_COLOR = 0xffffffff;
    private static final int DEFAULT_HEIGHT = 10;
    private static final int DEFAULT_TEXT_COLOR = DEFAULT_REACH_COLOR;
    private static final int DEFAULT_TEXT_SIZE = 12;
    private static final int DEFAULT_TEXT_OFFSET = 500;
    private final Drawable mProgress;//当前进度提示文本框
    private final Drawable scaleBackground;//背景刻度
    private int scaleWidth;
    private int scaleHeight;
    private int mTextOffsetLine;
    private int mProgressWidght;
    private int mProgressHeight;

    protected int mReachColor = DEFAULT_REACH_COLOR;
    protected int mUnReachColor = DEFAULT_UNREACH_COLOR;
    protected int mReachHeight = dp2px(DEFAULT_HEIGHT);
    protected int mUnReachHeight = dp2px(DEFAULT_HEIGHT);
    protected int mTextColor = DEFAULT_TEXT_COLOR;
    protected int mTextSize = sp2px(DEFAULT_TEXT_SIZE);
    protected int mTextOffset = dp2px(DEFAULT_TEXT_OFFSET);

    protected Paint mPaint = new Paint();

    public TimeRulerProgressBar(Context context) {
        this(context, null);
    }

    public TimeRulerProgressBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TimeRulerProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.TimeRulerProgressBar);
        mReachColor = ta.getColor(R.styleable.TimeRulerProgressBar_reachColor, mReachColor);
        mUnReachColor = ta.getColor(R.styleable.TimeRulerProgressBar_unreachColor, mUnReachColor);
        mReachHeight = (int) ta.getDimension(R.styleable.TimeRulerProgressBar_reachHeight, mReachHeight);
        mUnReachHeight = (int) ta.getDimension(R.styleable.TimeRulerProgressBar_unreachHeight, mUnReachHeight);
        mTextColor = ta.getColor(R.styleable.TimeRulerProgressBar_textColor, mTextColor);
        mTextSize = (int) ta.getDimension(R.styleable.TimeRulerProgressBar_textSize, mTextSize);
        mTextOffset = (int) ta.getDimension(R.styleable.TimeRulerProgressBar_textOffset, mTextOffset);
        mTextOffsetLine = (int) ta.getDimension(R.styleable.TimeRulerProgressBar_textOffsetLine, mTextOffset);
        scaleBackground = ta.getDrawable(R.styleable.TimeRulerProgressBar_scaleBackground);
        mProgress = ta.getDrawable(R.styleable.TimeRulerProgressBar_progressBackground);
        ta.recycle();
        if(scaleBackground != null) {
            scaleHeight = scaleBackground.getIntrinsicHeight();
        }
        scaleWidth = scaleBackground.getIntrinsicWidth();
        mPaint.setTextSize(mTextSize);
    }

    public int getTextWidth(String content) {
        int width = 0;
        if (content != null && content.length() > 0) {
            int length = content.length();
            float[] widths = new float[length];
            mPaint.getTextWidths(content, widths);
            for (int i = 0; i < length; i++) {
                width += (int) Math.ceil(widths[i]);
            }
        }
        return width;
    }

    @Override
    protected synchronized void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
        String text = getProgress() + "%";
        boolean noNeedUnrechBar = false;
        float radio = getProgress() * 1.0f / getMax();
        float progressX = scaleWidth * radio;
        if (progressX > scaleWidth) {
            progressX = scaleWidth;
            noNeedUnrechBar = true;
        }
        //画文本框
        if (mProgress != null) {
            //计算文本框应该在的X坐标
            float bgStartX = (int) progressX - (mProgressWidght / 2);
            float bgEndX = progressX + (mProgressWidght / 2);
            if (bgStartX >= scaleWidth - mProgressWidght) {
                bgStartX = scaleWidth - mProgressWidght;
            }
            //文本框的坐标校验
            if (bgEndX >= scaleWidth) {
                bgEndX = scaleWidth;
            }
            if (bgEndX < mProgressWidght) {
                bgEndX = mProgressWidght;
            }
            if (bgStartX <= 0) {
                bgStartX = 0;
            }
            mProgress.setBounds((int) bgStartX, 0, (int) bgEndX, mProgressHeight);
            mProgress.draw(canvas);
            //计算文本的X坐标和Y坐标
            int textWH = getTextWidth(text) / 2;//文本的一半长度
            int progressBgWH = mProgressWidght / 2; //进度文本框的一半
            float progressTxtStartX = bgStartX + (progressBgWH - textWH);
            float progressTxtStartY = mProgressHeight / 2 + 5;
            if (progressTxtStartX >= scaleWidth - progressBgWH) {
                progressTxtStartX = scaleWidth - progressBgWH - textWH;
            }
            if (progressTxtStartX <= (progressBgWH - textWH)) {
                progressTxtStartX = progressBgWH - textWH;
            }

            mPaint.setColor(mTextColor);
            canvas.drawText(text, progressTxtStartX, progressTxtStartY, mPaint);
        }

        //画布移动到进度条
        canvas.save();
        canvas.translate(getPaddingLeft(), mProgressHeight + mTextOffsetLine);

        //draw 进度条
        mPaint.setColor(mReachColor);
        mPaint.setStrokeWidth(mReachHeight);
        canvas.drawLine(0, 0 + mReachHeight, progressX, 0 + mReachHeight, mPaint);
        //draw text
        //画刻度
        if (scaleBackground != null) {
            scaleBackground.setBounds(0, 0, scaleWidth, scaleHeight);
            scaleBackground.draw(canvas);
        }
        if (!noNeedUnrechBar) {
            mPaint.setColor(mUnReachColor);
            mPaint.setStrokeWidth(mUnReachHeight);
            float startX = progressX;
            canvas.drawLine(startX, mProgressHeight, scaleWidth, mProgressHeight, mPaint);
        }
        canvas.restore();
        
    }

    @Override
    protected synchronized void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
            int heightVal = mProgressHeight + mTextOffsetLine + mReachHeight + scaleHeight;
            setMeasuredDimension(scaleWidth, heightVal);
    }

    protected int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, getResources().getDisplayMetrics());
    }

    protected int sp2px(int sp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, getResources().getDisplayMetrics());
    }

}
