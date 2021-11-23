package com.pacman.engine;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Graph {

    private Map<Vertex, LinkedList<Vertex>> adjVertices;

    public Graph() {
        adjVertices = new HashMap<Vertex, LinkedList<Vertex>>();
    }

    public void addVertex(Vertex vertex) {
        adjVertices.put(vertex, new LinkedList<Vertex>());
    }

    public Map<Vertex, LinkedList<Vertex>> getAdjVertices() {
        return adjVertices;
    }

    public void removeVertice(Vertex vertex) {
        adjVertices.remove(vertex);
    }

    public void addEdge(Vertex v1, Vertex v2) {
        adjVertices.get(v1).add(v2);
        adjVertices.get(v2).add(v1);
    }

    public void removeEdge(Vertex v1, Vertex v2) {
        adjVertices.get(v1).remove(v2);
        adjVertices.get(v2).remove(v1);
    }
}
