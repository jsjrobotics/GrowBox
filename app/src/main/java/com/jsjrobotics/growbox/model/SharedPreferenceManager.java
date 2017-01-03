package com.jsjrobotics.growbox.model;

import android.content.Context;

import com.jsjrobotics.growbox.sharedPreferences.EncryptedSharedPreferences;
import com.jsjrobotics.growbox.display.detail.WateringSchedule;

public class SharedPreferenceManager {
    private static final String SHARED_PREF_FILENAME = "GROWBOX_SHARED_PREF";

    public static void setWateringSchedule(Context context, WateringSchedule schedule) {
        EncryptedSharedPreferences editor = getSharedPreferences(context);
        editor.write(schedule);
    }


    public static EncryptedSharedPreferences getSharedPreferences(Context context) {
        return EncryptedSharedPreferences.wrap(
                context.getSharedPreferences(SHARED_PREF_FILENAME, Context.MODE_PRIVATE)
        );
    }
}
