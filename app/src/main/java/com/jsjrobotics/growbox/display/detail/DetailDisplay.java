package com.jsjrobotics.growbox.display.detail;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;

import com.jsjrobotics.growbox.display.AndroidThingsDisplay;
import com.jsjrobotics.growbox.R;
import com.jsjrobotics.growbox.model.SharedPreferenceManager;
import com.jsjrobotics.growbox.views.dialogInput.AndroidThingsDialogs;

import java.util.Optional;

public class DetailDisplay implements AndroidThingsDisplay {

    private View mView1;
    private View mView2;
    private View mIdle;

    private Button mSave;
    private ScheduleWateringView mScheduleView;
    private NextWateringView mNextWateringView;
    private FrameLayout mRoot;

    @Override
    public void createView(FrameLayout display){
        mRoot = display;
        LayoutInflater inflater = LayoutInflater.from(display.getContext());
        inflater.inflate(R.layout.detail_display, display, true);
        mIdle = display.findViewById(R.id.idle);
        mNextWateringView = new NextWateringView(mIdle);
        mView1 = display.findViewById(R.id.view1);
        mScheduleView = new ScheduleWateringView(mView1);
        mView2 = display.findViewById(R.id.view2);
        displayIdle();

        mSave = (Button) display.findViewById(R.id.save);


        mSave.setOnClickListener(v -> {
            String intervalInput = mScheduleView.getInterval();
            String lengthInput = mScheduleView.getLength();
            int interval = Integer.valueOf(intervalInput);
            int length = Integer.valueOf(lengthInput);
            WateringSchedule schedule = new WateringSchedule(interval,length);
            SharedPreferenceManager.setWateringSchedule(v.getContext(), schedule);
            displayIdle();
        });
    }

    void displayIdle(){
        mIdle.setVisibility(View.VISIBLE);
        mNextWateringView.refreshDisplay(mRoot.getContext());
        mView1.setVisibility(View.GONE);
        mView2.setVisibility(View.GONE);
    }

    void displayWateringSchedule(){
        mView1.setVisibility(View.VISIBLE);
        mIdle.setVisibility(View.GONE);
        mView2.setVisibility(View.GONE);
    }


    void waterNow(){
        mView1.setVisibility(View.GONE);
        mIdle.setVisibility(View.GONE);
        mView2.setVisibility(View.VISIBLE);
    }
}
