package com.jsjrobotics.growbox.views;


import android.content.Context;
import android.content.res.TypedArray;
import android.util.TypedValue;

public abstract class ViewUtils {

    public static float dpToPx(Context context, float dp){
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics());
    }
}
