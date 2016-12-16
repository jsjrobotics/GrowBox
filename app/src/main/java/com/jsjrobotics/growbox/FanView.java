package com.jsjrobotics.growbox;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;

public class FanView extends View {
    private static final String CALCULATING_MIN_DIAMETER = "FanView must be square. Using minimum x/y size - padding";
    private static final String TAG = FanView.class.getName();
    private static final int DEFAULT_FAN_COLOR = 0xFF880080;
    private int mFanColor;
    private Paint mFanPaint;
    private float mDrawSize;
    private DisplayMetrics mDisplayMetrics;
    private float mBladeHeight;
    private float mBladeWidth;
    private float mVerticalBladesOffset;
    private float mHorizontalBladesOffset;

    public FanView(Context context) {
        super(context);
        init(context);
    }

    public FanView(Context context, AttributeSet attributes) {
        super(context, attributes);
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(
                attributes,
                R.styleable.FanView,
                0, 0);
        mFanColor = typedArray.getInt(R.styleable.FanView_fanColor, DEFAULT_FAN_COLOR);
        typedArray.recycle();
        init(context);
    }

    private void init(Context context) {
        mFanPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mFanPaint.setColor(mFanColor);
        mFanPaint.setStyle(Paint.Style.FILL);
        mDisplayMetrics = context.getResources().getDisplayMetrics();
    }

    @Override
    public void onSizeChanged(int width, int height, int oldWidth, int oldHeight){
        float xPadding = (float)(getPaddingLeft() + getPaddingRight());
        float yPadding = (float)(getPaddingTop() + getPaddingBottom());
        float drawWidth = width - xPadding;
        float drawHeight = height - yPadding;
        if (drawHeight != drawWidth) {
            Log.i(TAG, CALCULATING_MIN_DIAMETER);
        }
        mDrawSize = Math.min(drawHeight, drawWidth);
        mBladeHeight = mDrawSize/6;
        mBladeWidth = mBladeHeight/2;
        mVerticalBladesOffset = mBladeHeight * 2;
        mHorizontalBladesOffset = mDrawSize/2;


    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // Blades are numbered clockwise, with blade 1 being the blade closest to Y = 0
        canvas.save();
        canvas.rotate(45);

        drawFirstBlade(canvas);
        drawSecondBlade(canvas);
        drawThirdBlade(canvas);
        drawFourthBlade(canvas);

        canvas.restore();


    }

    private void drawFourthBlade(Canvas canvas) {

    }

    private void drawThirdBlade(Canvas canvas) {
        canvas.drawOval(0, mVerticalBladesOffset, mBladeWidth, mVerticalBladesOffset + mBladeHeight, mFanPaint);
    }

    private void drawSecondBlade(Canvas canvas) {
        canvas.drawOval(0, mHorizontalBladesOffset, mBladeHeight, mHorizontalBladesOffset + mBladeWidth, mFanPaint);
    }

    private void drawFirstBlade(Canvas canvas) {
        canvas.drawOval(0, 0, mBladeWidth, mBladeHeight, mFanPaint);
    }
}
