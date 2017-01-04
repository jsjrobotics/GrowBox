package com.jsjrobotics.growbox.display.detail;

import android.view.View;
import android.widget.TextView;

import com.jsjrobotics.growbox.R;

public class NextWateringView {
    private final TextView mNextWatering;
    private final TextView mWateringLength;

    public NextWateringView(View parent) {
        mNextWatering = (TextView) parent.findViewById(R.id.next_scheduled_watering);
        mWateringLength = (TextView) parent.findViewById(R.id.watering_length);
    }

    public void setWateringInterval(String wateringInterval) {
        mNextWatering.setText(wateringInterval);
    }

    public void setWateringLength(String wateringLength) {
        mWateringLength.setText(wateringLength);
    }
}
