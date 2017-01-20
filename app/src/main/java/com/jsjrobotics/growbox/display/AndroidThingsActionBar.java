package com.jsjrobotics.growbox.display;


import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jsjrobotics.growbox.R;
import com.jsjrobotics.growbox.TouchScreenListener;
import com.jsjrobotics.growbox.display.popupWindow.AndroidThingsPopupWindow;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class AndroidThingsActionBar implements TouchScreenListener{

    private final Supplier<Activity> mContext;
    private final View mRoot;
    private final Consumer<Void> mTouchListener;
    private final ImageView mSettingsButton;
    private Optional<AndroidThingsPopupWindow> mPopupWindow = Optional.empty();


    public AndroidThingsActionBar(Supplier<Activity> context, ViewGroup parent) {
        mContext = context;
        mRoot = parent;
        mSettingsButton = (ImageView) parent.findViewById(R.id.settings_button);
        mSettingsButton.setOnClickListener(view -> {
            showPopupWindow();
        });
        
        mTouchListener = ignored -> {
            if (mPopupWindow.isPresent()) {
                mPopupWindow.get().dismiss();
                mPopupWindow = Optional.empty();
            }
        };
    }

    private void showPopupWindow() {
        AndroidThingsPopupWindow window = new AndroidThingsPopupWindow(mContext.get().getWindow());
        window.show(buildPopupContent());

    }

    private View buildPopupContent() {
        LayoutInflater inflater = LayoutInflater.from(mContext.get());
        return inflater.inflate(R.layout.settings_view, null);
    }

    public View getView() {
        return mRoot;
    }

    @Override
    public Consumer<Void> getOnTouchListener() {
        return mTouchListener;
    }

}
