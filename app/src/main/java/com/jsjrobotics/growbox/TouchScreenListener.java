package com.jsjrobotics.growbox;

import java.util.function.Consumer;

public interface TouchScreenListener {
    Consumer<Void> getOnTouchListener();
}
