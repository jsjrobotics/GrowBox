package com.jsjrobotics.growbox.display.popupWindow;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import java.util.Optional;

public class AndroidThingsPopupWindow {
    private final WindowManager mWindowManager;
    private final Window mWindow;
    private Optional<View> mDisplayed = Optional.empty();

    public AndroidThingsPopupWindow(Window window) {
        mWindow = window;
        mWindowManager = mWindow.getWindowManager();
    }

    public void show(View popupView) {

        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(WindowManager.LayoutParams.FIRST_SUB_WINDOW);

        layoutParams.width = popupView.getWidth();
        layoutParams.height = popupView.getHeight();
        layoutParams.gravity = Gravity.CENTER;

        layoutParams.format = PixelFormat.RGBA_8888;
        layoutParams.flags = WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN;
        layoutParams.token = mWindow.getDecorView().getRootView().getWindowToken();

        popupView.setOnKeyListener((view, keyCode, event) -> {
            if (keyCode == KeyEvent.KEYCODE_BACK) {
                dismiss();
            }
            return true;
        });
        mDisplayed = Optional.of(popupView);
        mWindowManager.addView(popupView, layoutParams);
    }

    public void dismiss() {
        if (mDisplayed.isPresent()) {
            mWindowManager.removeView(mDisplayed.get());
            mDisplayed = Optional.empty();
        }
    }
}
