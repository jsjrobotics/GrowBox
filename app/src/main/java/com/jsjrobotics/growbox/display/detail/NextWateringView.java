package com.jsjrobotics.growbox.display.detail;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.jsjrobotics.growbox.R;
import com.jsjrobotics.growbox.model.SharedPreferenceManager;

import java.util.Optional;

public class NextWateringView {
    private final TextView mNextWatering;
    private final TextView mWateringLength;

    public NextWateringView(View parent) {
        mNextWatering = (TextView) parent.findViewById(R.id.next_scheduled_watering);
        mWateringLength = (TextView) parent.findViewById(R.id.watering_length);
        refreshDisplay(parent.getContext());
    }

    public void refreshDisplay(Context context) {
        WateringSchedule restored = new WateringSchedule();
        Optional<WateringSchedule> read = SharedPreferenceManager.getSharedPreferences(context).read(restored);
        read.ifPresent(v -> {
            setWateringInterval(String.valueOf(v.wateringIntervalMinutes));
            setWateringLength(String.valueOf(v.wateringLengthMinutes));
        });
    }

    public void setWateringInterval(String wateringInterval) {
        mNextWatering.setText(wateringInterval);
    }

    public void setWateringLength(String wateringLength) {
        mWateringLength.setText(wateringLength);
    }
}
