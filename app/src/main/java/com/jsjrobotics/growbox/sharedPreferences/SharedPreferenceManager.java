package com.jsjrobotics.growbox.sharedPreferences;

import android.content.Context;

import com.jsjrobotics.growbox.sharedPreferences.EncryptedSharedPreferences;
import com.jsjrobotics.growbox.dataStructures.WateringSchedule;

public class SharedPreferenceManager {
    public static final String SHARED_PREF_TOKEN_DIVIDER = ":";
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
