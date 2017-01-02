package com.jsjrobotics.growbox.display.detail;

import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;

import com.jsjrobotics.growbox.display.AndroidThingsDisplay;
import com.jsjrobotics.growbox.R;
import com.jsjrobotics.growbox.views.dialogInput.AndroidThingsDialogs;

public class DetailDisplay implements AndroidThingsDisplay {

    private View mView1;
    private View mView2;
    private View mInit;
    private EditText mWateringInterval;
    private EditText mWateringLength;

    @Override
    public void createView(FrameLayout display){
        LayoutInflater inflater = LayoutInflater.from(display.getContext());
        inflater.inflate(R.layout.detail_display, display, true);
        mInit = display.findViewById(R.id.init);
        mView1 = display.findViewById(R.id.view1);
        mView2 = display.findViewById(R.id.view2);
        mView1.setVisibility(View.GONE);
        mView2.setVisibility(View.GONE);
        mWateringInterval = (EditText) display.findViewById(R.id.watering_interval);
        mWateringLength = (EditText) display.findViewById(R.id.watering_length);
        mWateringInterval.setOnClickListener(v -> {
            AndroidThingsDialogs.showNumberPad(v.getContext(), mWateringInterval::setText, ignored -> {});
        });
        mWateringLength.setOnClickListener(v -> {
            AndroidThingsDialogs.showTimePicker(v.getContext(), ignored -> {}, ignored -> {});
        });
    }

    public void displayWateringSchedule(){
        mView1.setVisibility(View.VISIBLE);
        mInit.setVisibility(View.GONE);
        mView2.setVisibility(View.GONE);
    }

    private DialogInterface.OnClickListener buildSaveListener() {
        return new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        };
    }

    public void waterNow(){
        mView1.setVisibility(View.GONE);
        mInit.setVisibility(View.GONE);
        mView2.setVisibility(View.VISIBLE);
    }
}
