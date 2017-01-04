package com.jsjrobotics.growbox.display.detail;

import android.content.Context;
import android.view.View;
import android.widget.EditText;

import com.jsjrobotics.growbox.R;
import com.jsjrobotics.growbox.model.SharedPreferenceManager;
import com.jsjrobotics.growbox.views.dialogInput.AndroidThingsDialogs;

import java.util.Optional;

public class ScheduleWateringView {
    private final EditText mWateringInterval;
    private final EditText mWateringLength;

    public ScheduleWateringView(View parent) {
        mWateringInterval = (EditText) parent.findViewById(R.id.watering_interval);
        mWateringLength = (EditText) parent.findViewById(R.id.watering_length);
        mWateringInterval.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus){
                showNumberPad(v.getContext());
            }
        });
        mWateringInterval.setOnClickListener(v -> showNumberPad(v.getContext()));
        mWateringLength.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                showIntervalInput(v.getContext());
            }
        });
        mWateringLength.setOnClickListener(v -> showIntervalInput(v.getContext()));

        refreshDisplay(parent.getContext());

    }

    public void refreshDisplay(Context context) {
        WateringSchedule restored = new WateringSchedule();
        Optional<WateringSchedule> read = SharedPreferenceManager.getSharedPreferences(context).read(restored);
        read.ifPresent(v -> {
            mWateringInterval.setText(String.valueOf(v.wateringIntervalMinutes));
            mWateringLength.setText(String.valueOf(v.wateringLengthMinutes));
        });
    }

    private void showNumberPad(Context context) {
        AndroidThingsDialogs.showNumberPad(
                context,
                mWateringInterval::setText,
                ignored -> {
                }
        );
    }

    private void showIntervalInput(Context context) {
        AndroidThingsDialogs.showTimePicker(
                context,
                date -> mWateringLength.setText("" + date.hour + date.minute),
                ignored -> {}
        );
    }

    public String getInterval() {
        return mWateringInterval.getText().toString();
    }

    public String getLength() {
        return mWateringLength.getText().toString();
    }
}
