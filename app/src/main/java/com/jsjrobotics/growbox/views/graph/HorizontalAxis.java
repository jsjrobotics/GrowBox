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


public class HorizontalAxis extends View {
    private static final int DEFAULT_COLOR = 0xFF880080;
    private static final double DEFAULT_DASH_HEIGHT_PERCENTAGE = 0.9;
    private static final double DEFAULT_BASELINE_HEIGHT_PERCENTAGE = 0.02;
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

    public HorizontalAxis(Context context, int numberOfDashes, int pixelsBetweenDash) {
        super(context);
        mNumberOfSections = numberOfDashes;
        mPixelsBetweenDash = pixelsBetweenDash;
        init();
    }

    public HorizontalAxis(Context context, AttributeSet attrs) {
        super(context, attrs);
        extractXmlAttributes(attrs);
    }

    public HorizontalAxis(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        extractXmlAttributes(attrs);
    }

    public HorizontalAxis(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        extractXmlAttributes(attrs);
    }

    private void extractXmlAttributes(AttributeSet attrs) {
        TypedArray typedArray = getContext().getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.HorizontalAxis,
                0, 0);
        int numberOfDashes = typedArray.getInt(R.styleable.HorizontalAxis_numberOfDashes, INVALID);
        if (numberOfDashes != INVALID){
            mNumberOfSections =  numberOfDashes +1;
        }

        mPixelsBetweenDash = typedArray.getInt(R.styleable.HorizontalAxis_spaceBetweenDashes, INVALID);
        typedArray.recycle();
        init();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int paddingWidth = getPaddingLeft() + getPaddingRight();
        int paddingHeight = getPaddingBottom() + getPaddingTop();

        int width = widthSize;
        if (widthMode == MeasureSpec.UNSPECIFIED && widthSize == 0){
            width = (int) dpToPx(getContext(), 100) + paddingWidth;
        }

        int height = heightSize;
        if (heightMode == MeasureSpec.UNSPECIFIED && heightSize == 0){
            height = (int) dpToPx(getContext(), 100) + paddingHeight;
        }

        int w = resolveSizeAndState(width, widthMeasureSpec, 0);
        int h = resolveSizeAndState(height, heightMeasureSpec, 0);
        setMeasuredDimension(w, h);
    }

    @Override
    public void onSizeChanged(int width, int height, int oldWidth, int oldHeight){
        mDashHeight = (int) ((DEFAULT_DASH_HEIGHT_PERCENTAGE * height) / 2);
        mDashWidth = (int) (DEFAULT_BASELINE_HEIGHT_PERCENTAGE * height);
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
        int midHeight = height/2;
        int lineWidth = width - getPaddingLeft() - getPaddingRight();
        return new Rect(getPaddingLeft(),midHeight-mDashWidth, lineWidth ,midHeight+mDashWidth);
    }

    private List<Rect> buildDashes(int width, int height){
        ArrayList<Rect> result = new ArrayList<>();
        int baseLineWidth = width - getPaddingLeft() - getPaddingRight();
        int spacesBetweenDashes = baseLineWidth / mNumberOfSections;
        int startX = 0;
        int startY = mDashHeight;
        for (int index = 0; index < mNumberOfSections; index++){
            startX += spacesBetweenDashes;
            result.add(new Rect(startX, startY, startX + mDashWidth, height - startY));
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
