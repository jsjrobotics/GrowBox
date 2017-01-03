package com.jsjrobotics.growbox.display.detail;

import com.jsjrobotics.growbox.SharedPreferenceObject;

public class WateringSchedule implements SharedPreferenceObject {
    private static final String WATERING_SCHEDULE_KEY = "WATERING_SCHEDULE";
    private static final String CIPHER_KEY = WATERING_SCHEDULE_KEY + "_CIPHER";
    public final int wateringIntervalMinutes;
    public final int wateringLengthMinutes;

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
        return String.valueOf(wateringIntervalMinutes) + ":" + String.valueOf(wateringLengthMinutes);
    }

    @Override
    public String getCipherKey() {
        return CIPHER_KEY;
    }
}
