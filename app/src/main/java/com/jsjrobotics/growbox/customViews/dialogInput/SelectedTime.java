package com.jsjrobotics.growbox.customViews.dialogInput;

public class SelectedTime {
    public final int hour;
    public final int minute;

    public SelectedTime(int hour, int minute) {
        this.hour = hour;
        this.minute = minute;
    }

    public static SelectedTime invalid() {
        return new SelectedTime(-1, -1);
    }

    public String getDisplayTime() {
        return "" + (hour+1) + ":" + minute;
    }
}
