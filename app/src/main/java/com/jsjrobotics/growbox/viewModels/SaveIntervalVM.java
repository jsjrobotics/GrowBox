package com.jsjrobotics.growbox.viewModels;

import android.content.Context;

import com.jsjrobotics.growbox.dataStructures.WateringSchedule;
import com.jsjrobotics.growbox.model.SharedPreferenceManager;

public abstract class SaveIntervalVM {
    public static void saveWateringInterval(Context context, WateringSchedule schedule){
        SharedPreferenceManager.setWateringSchedule(context, schedule);
    }
}
