package com.jsjrobotics.growbox.display.graph;

/**
 * A GraphNode is an object that can be drawn on a graph
 * It holds detail attributes, along with an x and y position from the origin
 *
 */
public class GraphNode {
    public final int xPosition;
    public final int yPosition;

    public GraphNode(int xPosition, int yPosition) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
    }
}
