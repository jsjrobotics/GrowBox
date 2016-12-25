package com.jsjrobotics.growbox.display.detail;

import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.FrameLayout;

import com.jsjrobotics.growbox.R;
import com.jsjrobotics.growbox.display.AndroidThingsDisplay;

public class DetailController implements AndroidThingsDisplay {
    private final DetailDisplay mDetailDisplay;
    private Button mButton1;
    private Button mButton2;

    public DetailController(DetailDisplay detailDisplay) {
        mDetailDisplay = detailDisplay;
    }

    @Override
    public void createView(FrameLayout display){
        LayoutInflater inflater = LayoutInflater.from(display.getContext());
        inflater.inflate(R.layout.detail_controller, display, true);
        mButton1 = (Button) display.findViewById(R.id.button1);
        mButton2 = (Button) display.findViewById(R.id.button2);

        String setWaterSchedule = display.getResources().getString(R.string.set_water_schedule);
        String waterNow = display.getResources().getString(R.string.water_now);
        mButton1.setText(setWaterSchedule);
        mButton2.setText(waterNow);
        mButton1.setOnClickListener(ignored -> mDetailDisplay.displayWateringSchedule());
        mButton2.setOnClickListener(ignored -> mDetailDisplay.waterNow());
    }

}
