package com.jsjrobotics.growbox.display.detail;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.GridView;

import com.jsjrobotics.growbox.dataStructures.WateringSchedule;
import com.jsjrobotics.growbox.display.AndroidThingsDisplay;
import com.jsjrobotics.growbox.R;
import com.jsjrobotics.growbox.model.SharedPreferenceManager;
import com.jsjrobotics.growbox.views.dialogInput.SelectedTime;

public class DetailDisplay implements AndroidThingsDisplay {


    private Button mSave;
    private ScheduleWateringView mScheduleView;
    private NextWateringView mNextWateringView;
    private WateringNowView mWateringNowView;
    private View mRoot;

    @Override
    public View createView(GridView parent){
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        mRoot = inflater.inflate(R.layout.detail_display, parent, false);
        View idle = mRoot.findViewById(R.id.idle);
        mNextWateringView = new NextWateringView(idle);
        View view1 = mRoot.findViewById(R.id.view1);
        mScheduleView = new ScheduleWateringView(view1);
        View view2 = mRoot.findViewById(R.id.view2);
        mWateringNowView = new WateringNowView(view2);
        displayIdle();

        mSave = (Button) mRoot.findViewById(R.id.save);

        mSave.setOnClickListener(v -> {
            String intervalInput = mScheduleView.getInterval();
            String lengthInput = mScheduleView.getLength();
            SelectedTime nextStartTime = mScheduleView.getNextWateringTime();
            int interval = Integer.valueOf(intervalInput);
            int length = Integer.valueOf(lengthInput);
            WateringSchedule schedule = new WateringSchedule(nextStartTime, interval,length);
            SharedPreferenceManager.setWateringSchedule(v.getContext(), schedule);
            displayIdle();
        });
        return mRoot;
    }



    private void displayIdle(){
        mNextWateringView.setVisibility(View.VISIBLE);
        mNextWateringView.refreshDisplay(mRoot.getContext());
        mScheduleView.setVisibility(View.GONE);
        mWateringNowView.setVisibility(View.GONE);
    }

    void displayWateringSchedule(){
        mScheduleView.setVisibility(View.VISIBLE);
        mNextWateringView.setVisibility(View.GONE);
        mWateringNowView.setVisibility(View.GONE);
    }


    void waterNow(){
        mScheduleView.setVisibility(View.GONE);
        mNextWateringView.setVisibility(View.GONE);
        mWateringNowView.setVisibility(View.VISIBLE);
    }
}
