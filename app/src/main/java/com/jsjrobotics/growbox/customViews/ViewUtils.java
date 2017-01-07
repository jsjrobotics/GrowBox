package com.jsjrobotics.growbox.customViews;


import android.content.Context;
import android.util.TypedValue;

public abstract class ViewUtils {

    public static float dpToPx(Context context, float dp){
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics());
    }
}
