package com.jsjrobotics.growbox;


import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;

public class FanView extends View {
    private static final String CALCULATING_MIN_DIAMETER = "FanView must be square. Using minimum x/y size - padding";
    private static final String TAG = FanView.class.getName();
    private static final int DEFAULT_FAN_COLOR = 0xFF880080;
    private static final int RESET_COUNT = 20;
    private static final int RESET_VALUE = 2 * RESET_COUNT;

    private int mFanColor;
    private Paint mFanPaint;
    private float mDrawSize;
    private DisplayMetrics mDisplayMetrics;
    private float mBladeHeight;
    private float mBladeWidth;
    private float mVerticalBladesOffset;
    private float mHorizontalBladesOffset;
    private float mXOffset;
    private int mMidWidth;
    private int mMidHeight;
    private float mHalfBlade;
    private int mDrawCount = 0;
    private volatile boolean mIsRotating;

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
        setOnClickListener(view -> {
            if (mIsRotating) {
                stopRotation();
            } else {
                startRotation((Activity) view.getContext());
            }
        });
    }

    public void startRotation(Activity context){
        mIsRotating = true;
        continueRotation(context.getMainLooper());
    }

    public void stopRotation(){
        mIsRotating = false;
    }

    private void continueRotation(final Looper mainLooper) {
        if (mIsRotating){
            Handler handler = new Handler(mainLooper);
            invalidate();
            handler.postDelayed(() -> continueRotation(mainLooper), 20);
        }
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
        mMidWidth = width /2;
        mMidHeight = height/2;
        mDrawSize = Math.min(drawHeight, drawWidth);
        mXOffset = mDrawSize/2;
        mBladeHeight = mDrawSize/3;
        mBladeWidth = mBladeHeight/2;
        mVerticalBladesOffset = mBladeHeight * 2;
        mHorizontalBladesOffset = mDrawSize/2;
        mHalfBlade = mBladeWidth / 2;


    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // Blades are numbered clockwise, with blade 1 being the blade closest to Y = 0
        boolean rotate = false;
        mDrawCount += 1;

        if (mDrawCount > RESET_VALUE){
            mDrawCount = 0;
        } else if (mDrawCount >= RESET_COUNT) {
            canvas.save();
            canvas.rotate(45, mMidWidth, mMidHeight);
            rotate = true;
        }

        drawFirstBlade(canvas);
        drawThirdBlade(canvas);
        drawSecondBlade(canvas);
        drawFourthBlade(canvas);
        if (rotate) {
            canvas.restore();
        }


    }

    private void drawSecondBlade(Canvas canvas) {
        canvas.drawOval(mBladeHeight*2, mHorizontalBladesOffset - mHalfBlade, mBladeHeight * 3, mHorizontalBladesOffset + mHalfBlade, mFanPaint);
    }

    private void drawThirdBlade(Canvas canvas) {
        canvas.drawOval(mXOffset, mVerticalBladesOffset, mXOffset + mBladeWidth, mVerticalBladesOffset + mBladeHeight, mFanPaint);
    }

    private void drawFourthBlade(Canvas canvas) {
        canvas.drawOval(mBladeHeight/2, mHorizontalBladesOffset - mHalfBlade, mBladeHeight * 3/2, mHorizontalBladesOffset + mHalfBlade, mFanPaint);
    }

    private void drawFirstBlade(Canvas canvas) {
        canvas.drawOval(mXOffset, 0, mXOffset + mBladeWidth, mBladeHeight, mFanPaint);
    }
}
