package com.jsjrobotics.growbox;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.GridView;

import com.jsjrobotics.growbox.R;
import com.jsjrobotics.growbox.customViews.FanView;
import com.jsjrobotics.growbox.display.GrowboxFragmentAdapter;

public class MainActivity extends Activity {

    private FanView mFanView;
    private GridView mGridView;
    private GrowboxFragmentAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mGridView = (GridView) findViewById(R.id.grid_view);
        mAdapter = new GrowboxFragmentAdapter(this);
        mGridView.setAdapter(mAdapter);
    }

}
