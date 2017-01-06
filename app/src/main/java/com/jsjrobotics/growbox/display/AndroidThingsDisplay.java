package com.jsjrobotics.growbox.display;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.TextView;

import com.jsjrobotics.growbox.R;

public interface AndroidThingsDisplay {
    default View getView(Activity activity, View convertView, GridView parent){
        int height = (activity).getWindowManager().getDefaultDisplay().getHeight();
        LayoutInflater inflater = LayoutInflater.from(activity);
        return createView(parent);
    }

    default View createView(GridView display) {
        LayoutInflater inflater = LayoutInflater.from(display.getContext());
        return inflater.inflate(R.layout.no_implementation, display, false);
    }
}
