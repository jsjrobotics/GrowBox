package com.jsjrobotics.growbox.views.dialogInput;

import android.view.KeyEvent;
import android.view.View;

import java.util.Map;
import java.util.function.Consumer;

public interface AndroidThingsDialogInput {
    View getRootView();

    // getViewMap() result maps android keycodes to the instantiated view on screen
    Map<Integer, View> getViewMap();
    // getIdMap() result maps android keycodes to the view ids specified xml
    Map<Integer,Integer> getIdMap();


    default View findKey(View v, int keycode) {
        View view = v.findViewById(getIdMap().get(keycode));
        if (view == null){
            throw new IllegalArgumentException("Keycode doesn't exist -->" + KeyEvent.keyCodeToString(keycode));
        }
        return view;
    }

    default void setOnPressedListener(Consumer<String> listener) {
        for (final Integer keyCode : getViewMap().keySet()){
            final View view = getViewMap().get(keyCode);
            char label = new KeyEvent(KeyEvent.ACTION_DOWN, keyCode).getDisplayLabel();
            String value = String.valueOf(label);
            view.setOnClickListener(ignored -> listener.accept(value));
        }
    }

    default void buildViewMap(){
        for (Integer keyCode : getIdMap().keySet()){
            View view = findKey(getRootView(), keyCode);
            getViewMap().put(keyCode, view);
        }
    }

}
