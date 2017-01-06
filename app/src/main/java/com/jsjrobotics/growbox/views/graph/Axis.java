package com.jsjrobotics.growbox.views.graph;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import com.jsjrobotics.growbox.R;

import java.util.ArrayList;
import java.util.List;

import static com.jsjrobotics.growbox.views.ViewUtils.dpToPx;


public class Axis extends View {
    private static final int DEFAULT_COLOR = 0xFF880080;
    private static final double DEFAULT_DASH_HEIGHT_PERCENTAGE = 0.9;
    private static final double DEFAULT_BASELINE_HEIGHT_PERCENTAGE = 0.1;
    private static final int INVALID = -1;
    private static final int DEFAULT_DASH_WIDTH_DP = 2;
    private int mNumberOfSections;
    private int mPixelsBetweenDash;
    private int mDashHeight;
    private int mBaseLineHeight;
    private Paint mPaint;
    private Rect mBaseLineRectangle;
    private int mDashWidth;
    private ArrayList<Rect> mObjectsToDraw;

    public Axis(Context context, int numberOfDashes, int pixelsBetweenDash) {
        super(context);
        mNumberOfSections = numberOfDashes;
        mPixelsBetweenDash = pixelsBetweenDash;
        init();
    }

    public Axis(Context context, AttributeSet attrs) {
        super(context, attrs);
        extractXmlAttributes(attrs);
    }

    public Axis(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        extractXmlAttributes(attrs);
    }

    public Axis(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        extractXmlAttributes(attrs);
    }

    private void extractXmlAttributes(AttributeSet attrs) {
        TypedArray typedArray = getContext().getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.Axis,
                0, 0);
        int numberOfDashes = typedArray.getInt(R.styleable.Axis_numberOfDashes, INVALID);
        if (numberOfDashes != INVALID){
            mNumberOfSections =  numberOfDashes +1;
        }

        mPixelsBetweenDash = typedArray.getInt(R.styleable.Axis_spaceBetweenDashes, INVALID);
        typedArray.recycle();
        init();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure();
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int paddingWidth = getPaddingLeft() + getPaddingRight();
        int paddingHeight = getPaddingBottom() + getPaddingTop();

        // Try for a width based on our minimum
        int suggestedMinWidth = paddingWidth + getSuggestedMinimumWidth();
        int w = resolveSizeAndState(suggestedMinWidth, widthMeasureSpec, 1);

        // Whatever the width ends up being, ask for a height that would let the pie
        // get as big as it can
        int suggestedMinHeight = paddingHeight + getSuggestedMinimumHeight();
        int h = resolveSizeAndState(suggestedMinHeight, heightMeasureSpec, 1);

        setMeasuredDimension(widthSize, heightSize);
    }

    @Override
    public void onSizeChanged(int width, int height, int oldWidth, int oldHeight){
        mDashHeight = (int) (DEFAULT_DASH_HEIGHT_PERCENTAGE * height);
        mDashWidth = (int) dpToPx(getContext(), DEFAULT_DASH_WIDTH_DP);
        mPixelsBetweenDash = width / mNumberOfSections ;
        mObjectsToDraw = new ArrayList<>();
        mObjectsToDraw.add(buildBaseLineRectangle(width, height));
        mObjectsToDraw.addAll(buildDashes(width, height));
        if (mPixelsBetweenDash == INVALID) {
            mPixelsBetweenDash = width / mNumberOfSections;
        }
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (Rect r : mObjectsToDraw){
            canvas.drawRect(r, mPaint);
        }
    }

    private Rect buildBaseLineRectangle(int width, int height){
        int padding = (int) ((DEFAULT_BASELINE_HEIGHT_PERCENTAGE * height) / 2);
        return new Rect(0,padding,width,height-padding);
    }

    private List<Rect> buildDashes(int width, int height){
        int padding = (int) ((DEFAULT_DASH_HEIGHT_PERCENTAGE * height) / 2);
        ArrayList<Rect> result = new ArrayList<>();
        int spacesBetweenDashes = width / mNumberOfSections;
        int startX = 0;
        for (int index = 0; index < mNumberOfSections; index++){
            result.add(new Rect(startX, padding, startX + mDashWidth, height - padding));
            startX += spacesBetweenDashes;
        }
        return result;
    }

    private void init() {
        if (mNumberOfSections == INVALID){
            throw new IllegalArgumentException("Must provide number of dashes");
        }
        mPaint = new Paint();
        mPaint.setColor(DEFAULT_COLOR);
    }

}
