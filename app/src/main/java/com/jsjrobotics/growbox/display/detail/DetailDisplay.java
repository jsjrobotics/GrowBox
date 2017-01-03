package com.jsjrobotics.growbox.display.detail;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;

import com.jsjrobotics.growbox.SharedPreferenceObject;
import com.jsjrobotics.growbox.display.AndroidThingsDisplay;
import com.jsjrobotics.growbox.R;
import com.jsjrobotics.growbox.model.SharedPreferenceManager;
import com.jsjrobotics.growbox.views.dialogInput.AndroidThingsDialogs;

import java.util.Optional;
import java.util.function.Consumer;

public class DetailDisplay implements AndroidThingsDisplay {

    private View mView1;
    private View mView2;
    private View mInit;
    private EditText mWateringInterval;
    private EditText mWateringLength;
    private Button mSave;

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
        mSave = (Button) display.findViewById(R.id.save);
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

        WateringSchedule restored = new WateringSchedule();
        Optional<String> read = SharedPreferenceManager.getSharedPreferences(display.getContext()).read(restored);
        read.ifPresent(v -> {
            String[] oldValues = v.split(":");
            mWateringInterval.setText(oldValues[0]);
            mWateringLength.setText(oldValues[1]);
        });

        mSave.setOnClickListener(v -> {
            int interval = Integer.valueOf(mWateringInterval.getText().toString());
            int length = Integer.valueOf(mWateringLength.getText().toString());
            WateringSchedule schedule = new WateringSchedule(interval,length);
            SharedPreferenceManager.setWateringSchedule(v.getContext(), schedule);
        });
    }

    private void showIntervalInput(Context context) {
        AndroidThingsDialogs.showTimePicker(
                context,
                date -> {
                    mWateringLength.setText("" + date.hour + date.minute);
                },
                ignored -> {}
        );
    }

    private void showNumberPad(Context context) {
        AndroidThingsDialogs.showNumberPad(context, mWateringInterval::setText, ignored -> {});
    }

    public void displayWateringSchedule(){
        mView1.setVisibility(View.VISIBLE);
        mInit.setVisibility(View.GONE);
        mView2.setVisibility(View.GONE);
    }


    public void waterNow(){
        mView1.setVisibility(View.GONE);
        mInit.setVisibility(View.GONE);
        mView2.setVisibility(View.VISIBLE);
    }
}
