package com.jsjrobotics.growbox.views.dialogInput;

import android.view.View;
import android.widget.Button;

import com.jsjrobotics.growbox.R;

import java.util.HashMap;
import java.util.Map;

import static android.view.KeyEvent.KEYCODE_0;
import static android.view.KeyEvent.KEYCODE_1;
import static android.view.KeyEvent.KEYCODE_2;
import static android.view.KeyEvent.KEYCODE_3;
import static android.view.KeyEvent.KEYCODE_4;
import static android.view.KeyEvent.KEYCODE_5;
import static android.view.KeyEvent.KEYCODE_6;
import static android.view.KeyEvent.KEYCODE_7;
import static android.view.KeyEvent.KEYCODE_8;
import static android.view.KeyEvent.KEYCODE_9;

public class NumberRow implements AndroidThingsDialogInput {
    private final View mRoot;

    private final Map<Integer, Button> mViews = new HashMap<>();

    private static final Map<Integer,Integer> sIdMap;

    static {
        sIdMap = new HashMap<>();
        sIdMap.put(KEYCODE_0, R.id.number_0);
        sIdMap.put(KEYCODE_1, R.id.number_1);
        sIdMap.put(KEYCODE_2, R.id.number_2);
        sIdMap.put(KEYCODE_3, R.id.number_3);
        sIdMap.put(KEYCODE_4, R.id.number_4);
        sIdMap.put(KEYCODE_5, R.id.number_5);
        sIdMap.put(KEYCODE_6, R.id.number_6);
        sIdMap.put(KEYCODE_7, R.id.number_7);
        sIdMap.put(KEYCODE_8, R.id.number_8);
        sIdMap.put(KEYCODE_9, R.id.number_9);

    }
    public NumberRow(View parent) {
        mRoot = parent;
        buildViewMap();
    }

    @Override
    public Map<Integer, Button> getViewMap() {
        return mViews;
    }

    @Override
    public Map<Integer, Integer> getIdMap() {
        return sIdMap;
    }

    @Override
    public View getRootView() {
        return mRoot;
    }
}
