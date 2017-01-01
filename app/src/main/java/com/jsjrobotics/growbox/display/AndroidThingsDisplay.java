package com.jsjrobotics.growbox.display;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.jsjrobotics.growbox.R;

public interface AndroidThingsDisplay {
    default View getView(Activity activity, View convertView, ViewGroup parent){
        int height = (activity).getWindowManager().getDefaultDisplay().getHeight();
        LayoutInflater inflater = LayoutInflater.from(activity);
        FrameLayout view = (FrameLayout) inflater.inflate(R.layout.default_display_window, parent, false);
        view.setMinimumHeight(height/2);
        int backgroundColor = activity.getResources().getColor(R.color.colorBackground, activity.getTheme());
        view.setBackgroundColor(backgroundColor);
        createView(view);
        return view;
    }

    default void createView(FrameLayout display) {
        LayoutInflater inflater = LayoutInflater.from(display.getContext());
        inflater.inflate(R.layout.no_implementation, display, true);
    }
}
