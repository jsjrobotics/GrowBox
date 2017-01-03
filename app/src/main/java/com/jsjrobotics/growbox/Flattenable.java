package com.jsjrobotics.growbox;

public interface Flattenable<T> {
    String flatten();
    T inflate(String flattened);
}
