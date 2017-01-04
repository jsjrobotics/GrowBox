package com.jsjrobotics.growbox.display.detail;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.jsjrobotics.growbox.R;
import com.jsjrobotics.growbox.dataStructures.WateringSchedule;
import com.jsjrobotics.growbox.model.SharedPreferenceManager;

import java.util.Optional;

class NextWateringView {
    private final TextView mNextWatering;
    private final TextView mWateringLength;
    private final View mParent;

    NextWateringView(View parent) {
        mParent = parent;
        mNextWatering = (TextView) parent.findViewById(R.id.next_scheduled_watering);
        mWateringLength = (TextView) parent.findViewById(R.id.watering_length);
        refreshDisplay(parent.getContext());
    }

    void refreshDisplay(Context context) {
        Optional<WateringSchedule> read = SharedPreferenceManager.getSharedPreferences(context).read(WateringSchedule.invalid());
        read.ifPresent(v -> {
            setWateringInterval(String.valueOf(v.wateringIntervalMinutes));
            setWateringLength(String.valueOf(v.wateringLengthMinutes));
        });
    }

    private void setWateringInterval(String wateringInterval) {
        mNextWatering.setText(wateringInterval);
    }

    private void setWateringLength(String wateringLength) {
        mWateringLength.setText(wateringLength);
    }

    void setVisibility(int visibility) {
        mParent.setVisibility(visibility);
    }
}
