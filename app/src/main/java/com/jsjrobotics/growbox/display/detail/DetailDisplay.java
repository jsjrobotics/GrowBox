package com.jsjrobotics.growbox.display.detail;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.jsjrobotics.growbox.display.AndroidThingsDisplay;
import com.jsjrobotics.growbox.R;

public class DetailDisplay implements AndroidThingsDisplay {

    private View mView1;
    private View mView2;
    private View mInit;

    @Override
    public void createView(FrameLayout display){
        LayoutInflater inflater = LayoutInflater.from(display.getContext());
        inflater.inflate(R.layout.detail_display, display, true);
        mInit = display.findViewById(R.id.init);
        mView1 = display.findViewById(R.id.view1);
        mView2 = display.findViewById(R.id.view2);
        mView1.setVisibility(View.GONE);
        mView2.setVisibility(View.GONE);
    }

    public void displayWateringSchedule(){
        mView1.setVisibility(View.VISIBLE);
        mInit.setVisibility(View.GONE);
        mView2.setVisibility(View.GONE);
    }

    public void waterNow(){
        mView1.setVisibility(View.GONE);
        mInit.setVisibility(View.GONE);
        mView2.setVisibility(View.VISIBLE);
    }
}
