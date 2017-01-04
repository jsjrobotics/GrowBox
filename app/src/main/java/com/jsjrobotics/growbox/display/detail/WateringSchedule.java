package com.jsjrobotics.growbox.display.detail;

import com.jsjrobotics.growbox.sharedPreferences.SharedPreferenceObject;

public class WateringSchedule implements SharedPreferenceObject<WateringSchedule> {
    private static final String WATERING_SCHEDULE_KEY = "WATERING_SCHEDULE";
    private static final int INVALID = -1;
    private static final java.lang.String TOKEN_DIVIDER = ":";

    public final int wateringIntervalMinutes;
    public final int wateringLengthMinutes;

    public WateringSchedule(){
        wateringIntervalMinutes = INVALID;
        wateringLengthMinutes = INVALID;
    }

    public WateringSchedule(int wateringIntervalMinutes, int wateringLengthMinutes) {
        this.wateringIntervalMinutes = wateringIntervalMinutes;
        this.wateringLengthMinutes = wateringLengthMinutes;
    }

    @Override
    public String getKey() {
        return WATERING_SCHEDULE_KEY;
    }

    @Override
    public String getData() {
        return String.valueOf(wateringIntervalMinutes) + TOKEN_DIVIDER + String.valueOf(wateringLengthMinutes);
    }

    @Override
    public WateringSchedule instantiate(String string) {
        String[] split = string.split(TOKEN_DIVIDER);
        Integer interval = Integer.valueOf(split[0]);
        Integer length = Integer.valueOf(split[1]);
        return new WateringSchedule(interval, length);
    }
}
