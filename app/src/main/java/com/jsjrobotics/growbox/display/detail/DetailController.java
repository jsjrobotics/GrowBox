package com.jsjrobotics.growbox.display.detail;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.GridView;

import com.jsjrobotics.growbox.R;
import com.jsjrobotics.growbox.display.AndroidThingsDisplay;

public class DetailController implements AndroidThingsDisplay {
    private final DetailDisplay mDetailDisplay;
    private Button mButton1;
    private Button mButton2;
    private View mRoot;

    public DetailController(DetailDisplay detailDisplay) {
        mDetailDisplay = detailDisplay;
    }

    @Override
    public View createView(GridView parent){
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        mRoot = inflater.inflate(R.layout.detail_controller, parent, false);
        mButton1 = (Button) mRoot.findViewById(R.id.button1);
        mButton2 = (Button) mRoot.findViewById(R.id.button2);

        String setWaterSchedule = mRoot.getResources().getString(R.string.set_water_schedule);
        String waterNow = mRoot.getResources().getString(R.string.water_now);
        mButton1.setText(setWaterSchedule);
        mButton2.setText(waterNow);
        mButton1.setOnClickListener(ignored -> mDetailDisplay.displayWateringSchedule());
        mButton2.setOnClickListener(ignored -> mDetailDisplay.waterNow());
        return mRoot;
    }

}
