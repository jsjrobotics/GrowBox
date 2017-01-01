package com.jsjrobotics.growbox.views;

import android.util.SparseIntArray;
import android.view.KeyEvent;
import android.view.View;

import com.jsjrobotics.growbox.R;

import java.util.HashMap;
import java.util.Map;

import static android.view.KeyEvent.KEYCODE_A;
import static android.view.KeyEvent.KEYCODE_B;
import static android.view.KeyEvent.KEYCODE_C;
import static android.view.KeyEvent.KEYCODE_D;
import static android.view.KeyEvent.KEYCODE_E;
import static android.view.KeyEvent.KEYCODE_F;
import static android.view.KeyEvent.KEYCODE_G;
import static android.view.KeyEvent.KEYCODE_H;
import static android.view.KeyEvent.KEYCODE_I;
import static android.view.KeyEvent.KEYCODE_J;
import static android.view.KeyEvent.KEYCODE_K;
import static android.view.KeyEvent.KEYCODE_L;
import static android.view.KeyEvent.KEYCODE_M;
import static android.view.KeyEvent.KEYCODE_N;
import static android.view.KeyEvent.KEYCODE_O;
import static android.view.KeyEvent.KEYCODE_P;
import static android.view.KeyEvent.KEYCODE_Q;
import static android.view.KeyEvent.KEYCODE_R;
import static android.view.KeyEvent.KEYCODE_S;
import static android.view.KeyEvent.KEYCODE_T;
import static android.view.KeyEvent.KEYCODE_U;
import static android.view.KeyEvent.KEYCODE_V;
import static android.view.KeyEvent.KEYCODE_W;
import static android.view.KeyEvent.KEYCODE_X;
import static android.view.KeyEvent.KEYCODE_Y;
import static android.view.KeyEvent.KEYCODE_Z;


public class QwertyKeyboard {
    private final View mRoot;

    private final Map<Integer, View> mViews = new HashMap<>();
    // Keycode --> Id for view
    private static final Map<Integer,Integer> sIdMap;

    static {
        sIdMap = new HashMap<>();
        sIdMap.put(KEYCODE_A, R.id.qwerty_a);
        sIdMap.put(KEYCODE_B, R.id.qwerty_b);
        sIdMap.put(KEYCODE_C, R.id.qwerty_c);
        sIdMap.put(KEYCODE_D, R.id.qwerty_d);
        sIdMap.put(KEYCODE_E, R.id.qwerty_e);
        sIdMap.put(KEYCODE_F, R.id.qwerty_f);
        sIdMap.put(KEYCODE_G, R.id.qwerty_g);
        sIdMap.put(KEYCODE_H, R.id.qwerty_h);
        sIdMap.put(KEYCODE_I, R.id.qwerty_i);
        sIdMap.put(KEYCODE_J, R.id.qwerty_j);
        sIdMap.put(KEYCODE_K, R.id.qwerty_k);
        sIdMap.put(KEYCODE_L, R.id.qwerty_l);
        sIdMap.put(KEYCODE_M, R.id.qwerty_m);
        sIdMap.put(KEYCODE_N, R.id.qwerty_n);
        sIdMap.put(KEYCODE_O, R.id.qwerty_o);
        sIdMap.put(KEYCODE_P, R.id.qwerty_p);
        sIdMap.put(KEYCODE_Q, R.id.qwerty_q);
        sIdMap.put(KEYCODE_R, R.id.qwerty_r);
        sIdMap.put(KEYCODE_S, R.id.qwerty_s);
        sIdMap.put(KEYCODE_T, R.id.qwerty_t);
        sIdMap.put(KEYCODE_U, R.id.qwerty_u);
        sIdMap.put(KEYCODE_V, R.id.qwerty_v);
        sIdMap.put(KEYCODE_W, R.id.qwerty_w);
        sIdMap.put(KEYCODE_X, R.id.qwerty_x);
        sIdMap.put(KEYCODE_Y, R.id.qwerty_y);
        sIdMap.put(KEYCODE_Z, R.id.qwerty_z);
    }


    public QwertyKeyboard(View root) {
        mRoot = root;
        for (Integer keyCode : sIdMap.keySet()){
            View view = findKey(root, keyCode);
            mViews.put(keyCode, view);
        }
    }

    private View findKey(View v, int keycode) {
        View view = v.findViewById(sIdMap.get(keycode));
        if (view == null){
            throw new IllegalArgumentException("Keycode doesn't exist -->" + KeyEvent.keyCodeToString(keycode));
        }
        return view;
    }
}
