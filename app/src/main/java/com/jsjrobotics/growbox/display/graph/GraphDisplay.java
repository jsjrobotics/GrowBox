package com.jsjrobotics.growbox.display.graph;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridView;


import com.jsjrobotics.growbox.R;
import com.jsjrobotics.growbox.display.AndroidThingsDisplay;
import com.jsjrobotics.growbox.customViews.graph.GraphView;

import java.util.ArrayList;
import java.util.List;

public class GraphDisplay implements AndroidThingsDisplay {
    private View mView;
    private GraphView mGraphView;

    @Override
    public View createView(GridView display) {
        LayoutInflater inflater = LayoutInflater.from(display.getContext());
        mView = inflater.inflate(R.layout.graph_display, display, false);
        mGraphView = (GraphView) mView.findViewById(R.id.graph);
        return mView;
    }

    public void addNode(){

    }

    public void addNodes(List<GraphNode> nodeList) {
        mGraphView.setData(nodeList);
    }
}
