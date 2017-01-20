package com.jsjrobotics.growbox.customViews.graph;

import android.graphics.Rect;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

class GridBackground {
    private final int mNumberPartitionsX;
    private final boolean mDrawGridLines;
    private final int mDashHeight;
    private final int mNumberPartitionsY;
    private final int mAxisThickness;
    private final int mPaddingLeft;
    private final int mPaddingTop;
    private final int mPaddingBottom;
    private final int mPaddingRight;
    private List<Integer> mCoordinatesX = new ArrayList<>();
    private List<Integer> mCoordinatesY = new ArrayList<>();

    GridBackground(
            int numberPartitionsX,
            boolean drawGridLines,
            int dashHeight,
            int numberPartitionsY,
            int axisThickness,
            int paddingLeft,
            int paddingTop,
            int paddingBottom,
            int paddingRight
    ) {
        this.mNumberPartitionsX = numberPartitionsX;
        this.mDrawGridLines = drawGridLines;
        this.mDashHeight = dashHeight;
        this.mNumberPartitionsY = numberPartitionsY;
        this.mAxisThickness = axisThickness;
        this.mPaddingLeft = paddingLeft;
        this.mPaddingTop = paddingTop;
        this.mPaddingBottom = paddingBottom;
        this.mPaddingRight = paddingRight;
    }

    Collection<Rect> buildRectangles(int width, int height) {
        ArrayList<Rect> toDraw = new ArrayList<>();
        toDraw.add(buildHorizontalAxis(width, height));
        toDraw.add(buildVerticalAxis(width, height));
        toDraw.addAll(buildHorizontalDashLines(width,height));
        toDraw.addAll(buildVerticalDashLines(width, height));
        return toDraw;
    }

    private List<Rect> buildHorizontalDashLines(int width, int height){
        ArrayList<Rect> result = new ArrayList<>();
        Rect horizontalAxis = buildHorizontalAxis(width, height);
        int spacesBetweenDashes = horizontalAxis.width() / mNumberPartitionsX;
        int startX = 0;
        for (int index = 0; index < mNumberPartitionsX; index++){
            startX += spacesBetweenDashes;
            if (mDrawGridLines){
                result.add(new Rect(startX, 0, startX + 1, height));
                mCoordinatesX.add(startX);
            } else {
                result.add(new Rect(startX, horizontalAxis.top - mDashHeight, startX + mDashHeight, horizontalAxis.bottom + mDashHeight));
                mCoordinatesX.add(startX + mDashHeight/2);
            }
        }
        return result;
    }

    private List<Rect> buildVerticalDashLines(int width, int height){
        ArrayList<Rect> result = new ArrayList<>();
        Rect verticalAxis = buildVerticalAxis(width, height);
        int spacesBetweenDashes = verticalAxis.height() / mNumberPartitionsY;
        int startY = 0;
        for (int index = 0; index < mNumberPartitionsY; index++){
            startY += spacesBetweenDashes;
            if (mDrawGridLines){
                result.add(new Rect(0, startY, width, startY + 1));
                mCoordinatesY.add(startY);
            } else {
                result.add(new Rect(0, startY, verticalAxis.right + mDashHeight, startY + mDashHeight));
                mCoordinatesY.add(startY + mDashHeight/2);
            }
        }
        return result;
    }
    private Rect buildVerticalAxis(int width, int height) {
        int left = mPaddingLeft;
        int top = mPaddingTop;
        int right = mPaddingLeft + mAxisThickness;
        int bottom = height - mPaddingBottom;
        return new Rect(left, top, right, bottom );
    }

    private Rect buildHorizontalAxis(int width, int height) {
        int left = mPaddingLeft;
        int top = height - mAxisThickness - mPaddingBottom;
        int right = width - mPaddingRight;
        int bottom = height - mPaddingBottom;
        return new Rect(left, top, right, bottom );
    }

}
