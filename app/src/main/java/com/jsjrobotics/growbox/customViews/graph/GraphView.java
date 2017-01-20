package com.jsjrobotics.growbox.customViews.graph;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import com.jsjrobotics.growbox.R;
import com.jsjrobotics.growbox.display.graph.GraphNode;

import java.util.ArrayList;
import java.util.List;

public class GraphView extends View {
    private static final int DEFAULT_DASH_NUMBER = 3;
    private static final int INVALID = -1;
    private static final float AXIS_THICKNESS_MULTIPLIER = 0.1f;
    private static final int DEFAULT_COLOR = 0xFF880080;
    private static final float DEFAULT_DASH_HEIGHT_MULTIPLIER = 0.5f;
    private static final boolean DRAW_GRID_LINES = true;
    private List<GraphNode> mData;
    private int mAxisThickness = INVALID;
    private int mDashThickness = INVALID;
    private int mNumberPartitionsX = INVALID;
    private int mNumberPartitionsY = INVALID;
    private int DEFAULT_NUMBER_PARTITIONS = 3;
    private List<Rect> mObjectsToDraw;
    private Paint mPaint;
    private int mDashHeight = INVALID;
    private float mDashLengthMultiplier = INVALID;
    private GridBackground mGridBackground;

    public GraphView(Context context) {
        super(context);
    }

    public GraphView(Context context, AttributeSet attrs) {
        super(context, attrs);
        extractXmlAttributes(attrs);
    }

    public GraphView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        extractXmlAttributes(attrs);
    }

    public GraphView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        extractXmlAttributes(attrs);
    }

    private void extractXmlAttributes(AttributeSet attrs) {
        TypedArray typedArray = getContext().getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.GraphView,
                0, 0);
        mAxisThickness = typedArray.getDimensionPixelSize(R.styleable.GraphView_axisThickness, INVALID);
        mNumberPartitionsX = typedArray.getInt(R.styleable.GraphView_xPartitions, INVALID);
        mNumberPartitionsY = typedArray.getInt(R.styleable.GraphView_yPartitions, INVALID);
        mDashThickness = typedArray.getDimensionPixelSize(R.styleable.GraphView_dashThickness, INVALID);
        mDashLengthMultiplier = typedArray.getDimensionPixelSize(R.styleable.GraphView_dashLengthMultiplier, INVALID);
        typedArray.recycle();
    }

    private void init(){
        if (mAxisThickness == INVALID){
            mAxisThickness = (int) (AXIS_THICKNESS_MULTIPLIER * getWidth());
        }

        if (mNumberPartitionsX == INVALID) {
            mNumberPartitionsX = DEFAULT_NUMBER_PARTITIONS;
        }

        if (mNumberPartitionsY == INVALID) {
            mNumberPartitionsY = DEFAULT_NUMBER_PARTITIONS;
        }

        if (mDashThickness == INVALID) {
            mDashThickness = (int) (AXIS_THICKNESS_MULTIPLIER * getHeight());
        }

        if (mDashLengthMultiplier == INVALID) {
            mDashLengthMultiplier = DEFAULT_DASH_HEIGHT_MULTIPLIER;
        }

        if (mDashHeight == INVALID) {
            mDashHeight = (int) (mDashLengthMultiplier * mAxisThickness);
        }

        if (mPaint == null) {
            mPaint = new Paint();
            mPaint.setColor(DEFAULT_COLOR);
        }
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int width;
        if (widthMode == MeasureSpec.EXACTLY || widthMode == MeasureSpec.AT_MOST){
            width = widthSize;
        } else {
            width = getScreenWidth() /2;
        }

        int height;
        if (heightMode == MeasureSpec.EXACTLY || heightMode == MeasureSpec.AT_MOST){
            height = heightSize;
        } else {
            height = getScreenHeight() /2;
        }

        setMeasuredDimension(width, height);

    }

    @Override
    public void onSizeChanged(int width, int height, int oldWidth, int oldHeight){
        init();
        mObjectsToDraw = new ArrayList<>();
        mGridBackground = new GridBackground(
                mNumberPartitionsX,
                DRAW_GRID_LINES,
                mDashHeight,
                mNumberPartitionsY,
                mAxisThickness,
                getPaddingLeft(),
                getPaddingTop(),
                getPaddingRight(),
                getPaddingBottom()
        );
        mObjectsToDraw.addAll(mGridBackground.buildRectangles(width, height));
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (Rect r : mObjectsToDraw){
            canvas.drawRect(r, mPaint);
        }
    }

    public void setData(List<GraphNode> nodeList) {
        mData = nodeList;
        invalidate();
    }

    private int getActionBarSize(){
        final TypedArray styledAttributes = getContext().getTheme().obtainStyledAttributes(new int[] { android.R.attr.actionBarSize });
        int actionBarSize = (int) styledAttributes.getDimension(0, 0);
        styledAttributes.recycle();
        return actionBarSize;
    }

    private int getScreenWidth() {
        return getResources().getDisplayMetrics().widthPixels;
    }

    private int getScreenHeight() {
        return getResources().getDisplayMetrics().heightPixels - getActionBarSize();
    }
}
