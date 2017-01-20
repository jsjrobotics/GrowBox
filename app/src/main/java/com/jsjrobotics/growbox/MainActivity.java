package com.jsjrobotics.growbox;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.GridView;
import android.widget.RelativeLayout;

import com.jsjrobotics.growbox.R;
import com.jsjrobotics.growbox.customViews.FanView;
import com.jsjrobotics.growbox.display.AndroidThingsActionBar;
import com.jsjrobotics.growbox.display.GrowboxFragmentAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class MainActivity extends Activity {

    private FanView mFanView;
    private GridView mGridView;
    private GrowboxFragmentAdapter mAdapter;
    private View mRoot;
    private AndroidThingsActionBar mActionBar;
    private final List<Consumer<Void>> mListeners = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RelativeLayout actionBar = (RelativeLayout) findViewById(R.id.android_things_action_bar);
        mGridView = (GridView) findViewById(R.id.grid_view);

        mActionBar = new AndroidThingsActionBar(() -> this, actionBar);
        mAdapter = new GrowboxFragmentAdapter(this);
        mGridView.setAdapter(mAdapter);

        mListeners.add(mActionBar.getOnTouchListener());
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        for (Consumer<Void> listener : mListeners){
            listener.accept(null);
        }
        return super.onTouchEvent(event);
    }
}
