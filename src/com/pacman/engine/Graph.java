package com.pacman.engine;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Classe que representa um Grafo.
 * É utilizado para encontrar o menor caminho entre dois pontos do tabuleiro.
 */
public class Graph {

    /**
     * Atributo que guarda a lista de vértices adjacentes de um determinado vértice.
     */
    private Map<Vertex, LinkedList<Vertex>> adjVertices;

    /**
     * Construtor padrão.
     */
    public Graph() {
        adjVertices = new HashMap<Vertex, LinkedList<Vertex>>();
    }

    /**
     * Método que retorna a lista de vértices adjacentes.
     * @return adjVertices
     */
    public Map<Vertex, LinkedList<Vertex>> getAdjVertices() {
        return adjVertices;
    }

    /**
     * Método que adiciona um vértice ao grafo.
     * @param vertex vértice a ser adicionado.
     */
    public void addVertex(Vertex vertex) {
        adjVertices.put(vertex, new LinkedList<Vertex>());
    }
    
    /**
     *
     * Método que cria uma aresta entre 2 vértices.
     * @param v1 Vértice 1.
     * @param v2 Vértice 2.
     */
    public void addEdge(Vertex v1, Vertex v2) {
        adjVertices.get(v1).add(v2);
        adjVertices.get(v2).add(v1);
    }
}
