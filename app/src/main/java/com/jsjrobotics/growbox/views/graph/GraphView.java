package com.jsjrobotics.growbox.views.graph;


import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.jsjrobotics.growbox.display.graph.GraphNode;

import java.util.List;

public class GraphView extends View {
    private List<GraphNode> mData;

    public GraphView(Context context) {
        super(context);
    }

    public GraphView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GraphView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public GraphView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void setData(List<GraphNode> nodeList) {
        mData = nodeList;
        requestLayout();
        invalidate();
    }
}
