package com.pacman.engine;

import com.pacman.systemelements.Floor;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

/**
 * Classe responsável por calcular o menor caminha entre dois vértices de um grafo.
 */
public class DijkstraAlgorithm {

    /**
     * Classe privada que armazena as informações necessárias para a operação do algoritmo de Dijkstra.
     */
    private class VertexInfo {

        /**
         * Atributo que armazena o vértice predecessor.
         */
        public Vertex predecessor;

        /**
         * Atributo que armazena a distância até aquele vértice.
         */
        public int distance;

        /**
         * Construtor padrão.
         * @param predecessor vértice predecessor.
         */
        public VertexInfo(Vertex predecessor) {
            this.predecessor = predecessor;
            this.distance = Integer.MAX_VALUE / 2;
        }
    }

    /**
     * Atributo que guarda o grafo no qual o algoritmo será aplicado.
     */
    private Graph G;

    /**
     * Atributo que armazena o vértice de partida para o cálculo de todos os menores caminhos.
     */
    private Vertex V;

    private Map<Vertex, VertexInfo> openVertices = new HashMap<>();
    private Map<Vertex, VertexInfo> closedVertices = new HashMap<>();

    /**
     * Construtor padrão.
     * @param G grafo no qual o algoritmo será aplicado.
     * @param V vértice de partida.
     */
    public DijkstraAlgorithm(Graph G, Vertex V) {

        this.G = G;
        this.V = V;

        Iterator i = G.getAdjVertices().keySet().iterator();

        while (i.hasNext()) {
            openVertices.put((Vertex) i.next(), new VertexInfo(null));
        }

        openVertices.get(V).distance = 0;
    }

    /**
     * Método que encontra todos os menores caminhos.
     */
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

    /**
     * Método que retorna o menor caminho até um determinado vértice.
     * @param vertex vértice de destino.
     * @return LinkedList com todos os vértices que compõem o menor caminho.
     */
    public LinkedList<Vertex<Floor>> getShortestPath(Vertex<Floor> vertex) {

        if (closedVertices.isEmpty()) findAllShortestPaths();

        LinkedList<Vertex<Floor>> path = new LinkedList<>();
        Vertex vPath = vertex;

        do {

            path.addFirst(vPath);
            vPath = closedVertices.get(vPath).predecessor;

        } while (vPath != null);

        return path;
    }
}
