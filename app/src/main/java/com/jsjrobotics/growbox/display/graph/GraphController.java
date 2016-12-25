package com.jsjrobotics.growbox.display.graph;

import android.view.View;

import com.jsjrobotics.growbox.display.AndroidThingsDisplay;

public class GraphController implements AndroidThingsDisplay {
    private final GraphDisplay mGraphDisplay;
    private View mView;

    public GraphController(GraphDisplay graphDisplay) {
        mGraphDisplay = graphDisplay;
    }
}
