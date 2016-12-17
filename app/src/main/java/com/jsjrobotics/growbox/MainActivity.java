package com.jsjrobotics.growbox;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private FanView mFanView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFanView = (FanView) findViewById(R.id.fan_view);
        mFanView.startRotation(this);
        new Handler().postDelayed(() -> runOnUiThread(() -> mFanView.stopRotation()), 10000);
    }


}
