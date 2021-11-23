package com.pacman.engine;

import com.pacman.systemelements.Floor;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

public class DijkstraAlgorithm {

    private class VertexInfo {

        public Vertex predecessor;
        public int distance;

        public VertexInfo(Vertex predecessor) {
            this.predecessor = predecessor;
            this.distance = Integer.MAX_VALUE / 2;
        }

        public VertexInfo(Vertex predecessor, int distance) {
            this.predecessor = predecessor;
            this.distance = distance;
        }
    }

    private Graph G;
    private Vertex V;

    private Map<Vertex, VertexInfo> openVertices = new HashMap<>();
    private Map<Vertex, VertexInfo> closedVertices = new HashMap<>();

    public DijkstraAlgorithm(Graph G, Vertex V) {

        this.G = G;
        this.V = V;

        Iterator i = G.getAdjVertices().keySet().iterator();

        while (i.hasNext()) {
            openVertices.put((Vertex) i.next(), new VertexInfo(null));
        }

        openVertices.get(V).distance = 0;
    }

    private void findAllShortestPaths() {

        Vertex vCurrent = V;

        while (!openVertices.isEmpty()) {

            Iterator i = G.getAdjVertices().get(vCurrent).iterator();
            Vertex vNext;

            while (i.hasNext()) { // atualizar distancias aos nós vizinhos.

                vNext = (Vertex) i.next();

                if (!openVertices.containsKey(vNext)) continue;

                if (openVertices.get(vCurrent).distance + 1 < openVertices.get(vNext).distance) {
                    openVertices.get(vNext).predecessor = vCurrent;
                    openVertices.get(vNext).distance = openVertices.get(vCurrent).distance + 1;
                }
            }

            closedVertices.put(vCurrent, openVertices.get(vCurrent));
            openVertices.remove(vCurrent); // fechar vertice


            Iterator j = openVertices.keySet().iterator();
            vCurrent = null;

            while (j.hasNext()) { // encontrar o vertice de menor distancia.

                Vertex vAux = (Vertex) j.next();
                vCurrent = (vCurrent == null) ? vAux : vCurrent;

                if (openVertices.get(vCurrent).distance > openVertices.get(vAux).distance) {
                    vCurrent = vAux;
                }
            }
        }
    }

    public LinkedList<Vertex<Floor>> getShortestPath(Vertex<Floor> vertice) {

        if (closedVertices.isEmpty()) findAllShortestPaths();

        LinkedList<Vertex<Floor>> path = new LinkedList<>();
        Vertex vPath = vertice;

        do {

            path.addFirst(vPath);
            vPath = closedVertices.get(vPath).predecessor;

        } while (vPath != null);

        return path;
    }
}
