package com.jsjrobotics.growbox.dataStructures;

import com.jsjrobotics.growbox.sharedPreferences.SharedPreferenceObject;
import com.jsjrobotics.growbox.customViews.dialogInput.SelectedTime;

import static com.jsjrobotics.growbox.sharedPreferences.SharedPreferenceManager.SHARED_PREF_TOKEN_DIVIDER;

public class WateringSchedule implements SharedPreferenceObject<WateringSchedule> {
    private static final String WATERING_SCHEDULE_KEY = "WATERING_SCHEDULE";
    private static final int INVALID = -1;

    public final int wateringIntervalMinutes;
    public final int wateringLengthMinutes;
    public final SelectedTime startTime;


    public WateringSchedule(SelectedTime startTime, int wateringIntervalMinutes, int wateringLengthMinutes) {
        this.wateringIntervalMinutes = wateringIntervalMinutes;
        this.wateringLengthMinutes = wateringLengthMinutes;
        this.startTime = startTime;
    }

    @Override
    public String getKey() {
        return WATERING_SCHEDULE_KEY;
    }

    @Override
    public String getData() {
        return startTime.hour + SHARED_PREF_TOKEN_DIVIDER + startTime.minute + SHARED_PREF_TOKEN_DIVIDER + String.valueOf(wateringIntervalMinutes) + SHARED_PREF_TOKEN_DIVIDER + String.valueOf(wateringLengthMinutes);
    }

    @Override
    public WateringSchedule instantiate(String string) {
        String[] split = string.split(SHARED_PREF_TOKEN_DIVIDER);
        if (split.length < 4) {
            return WateringSchedule.invalid();
        }
        Integer hour = Integer.valueOf(split[0]);
        Integer min = Integer.valueOf(split[1]);
        SelectedTime startTime = new SelectedTime(hour, min);
        Integer interval = Integer.valueOf(split[2]);
        Integer length = Integer.valueOf(split[3]);
        return new WateringSchedule(startTime, interval, length);
    }

    public static WateringSchedule invalid() {
        return new WateringSchedule(SelectedTime.invalid(), INVALID, INVALID);
    }
}
