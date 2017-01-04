package com.jsjrobotics.growbox.display.detail;

import android.content.Context;
import android.view.View;
import android.widget.EditText;

import com.jsjrobotics.growbox.R;
import com.jsjrobotics.growbox.dataStructures.WateringSchedule;
import com.jsjrobotics.growbox.model.SharedPreferenceManager;
import com.jsjrobotics.growbox.views.dialogInput.AndroidThingsDialogs;
import com.jsjrobotics.growbox.views.dialogInput.SelectedTime;

import java.util.Optional;

class ScheduleWateringView {
    private final EditText mWateringInterval;
    private final EditText mWateringLength;
    private final EditText mNextWateringTime;
    private final View mParent;
    private Optional<SelectedTime> mSelectedTime = Optional.empty();

    ScheduleWateringView(View parent) {
        mParent = parent;
        mNextWateringTime = (EditText) parent.findViewById(R.id.next_watering_time);
        mWateringInterval = (EditText) parent.findViewById(R.id.watering_interval);
        mWateringLength = (EditText) parent.findViewById(R.id.watering_length);
        mWateringInterval.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus){
                showNumberPad(mWateringInterval);
            }
        });
        mWateringInterval.setOnClickListener(v -> showNumberPad(mWateringInterval));
        mWateringLength.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                showNumberPad(mWateringLength);
            }
        });
        mWateringLength.setOnClickListener(v -> showNumberPad(mWateringLength));

        mNextWateringTime.setOnClickListener(v -> showEnterTimeInput(mNextWateringTime));
        mNextWateringTime.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                showEnterTimeInput(mNextWateringTime);
            }
        });
        refreshDisplay(parent.getContext());

    }

    public void refreshDisplay(final Context context) {
        Optional<WateringSchedule> read = SharedPreferenceManager.getSharedPreferences(context).read(WateringSchedule.invalid());
        read.ifPresent(v -> {
            mSelectedTime = Optional.of(v.startTime);
            mWateringInterval.setText(String.valueOf(v.wateringIntervalMinutes));
            mWateringLength.setText(String.valueOf(v.wateringLengthMinutes));
            mNextWateringTime.setText(v.startTime.getDisplayTime());
        });
    }

    private void showNumberPad(final EditText resultDisplay) {
        AndroidThingsDialogs.showNumberPad(
                resultDisplay.getContext(),
                resultDisplay::setText,
                ignored -> {}
        );
    }

    private void showEnterTimeInput(final EditText resultDisplay) {
        AndroidThingsDialogs.showTimePicker(
                resultDisplay.getContext(),
                date -> {
                    mSelectedTime = Optional.of(date);
                    resultDisplay.setText(date.hour + ":" + date.minute);
                },
                ignored -> {}
        );
    }

    String getInterval() {
        return mWateringInterval.getText().toString();
    }

    String getLength() {
        return mWateringLength.getText().toString();
    }

    void setVisibility(int visibility) {
        mParent.setVisibility(visibility);
    }

    SelectedTime getNextWateringTime() {
        return mSelectedTime.orElseGet(() -> SelectedTime.invalid());
    }
}
