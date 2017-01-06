package com.jsjrobotics.growbox.display.graph;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.GridView;


import com.jsjrobotics.growbox.R;
import com.jsjrobotics.growbox.display.AndroidThingsDisplay;
import com.jsjrobotics.growbox.views.graph.GraphView;

import java.util.ArrayList;
import java.util.List;

public class GraphDisplay implements AndroidThingsDisplay {
    private View mView;

    @Override
    public View createView(GridView display) {
        LayoutInflater inflater = LayoutInflater.from(display.getContext());
        mView = inflater.inflate(R.layout.graph_display, display, false);
        GraphView graphView = (GraphView) mView.findViewById(R.id.graph);
        List<GraphNode> nodeList = new ArrayList<>();
        graphView.setData(nodeList);
        return mView;
    }
}
