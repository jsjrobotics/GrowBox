package com.jsjrobotics.growbox.display.detail;

import android.view.View;

class WateringNowView {
    private final View mParent;

    WateringNowView(View parent) {
        mParent = parent;
    }

    void setVisibility(int visibility) {
        mParent.setVisibility(visibility);
    }
}
