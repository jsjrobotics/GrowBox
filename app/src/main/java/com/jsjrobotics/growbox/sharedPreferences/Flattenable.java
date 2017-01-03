package com.jsjrobotics.growbox.sharedPreferences;

public interface Flattenable<T> {
    String flatten();
    T inflate(String flattened);
}
