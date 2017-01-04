package com.jsjrobotics.growbox.sharedPreferences;

import java.util.Optional;

public interface SharedPreferenceObject<T> {
    String getKey();
    String getData();

    T instantiate(String string);
}
