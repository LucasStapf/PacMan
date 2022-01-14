package com.pacman.engine;

import com.pacman.systemelements.Arena;
import com.pacman.systemelements.Floor;

import java.util.ArrayList;

/**
 * Classe responsável por gerenciar a arena do jogo.
 *
 */
public class ArenaManager {

    /**
     * Atributo que armazena a arena.
     */
    private Arena arena;

    /**
     * Atributo que armazena o grafo relaciona à arena.
     */
    private Graph graph;

    /**
     * Construtor padrão.
     */
    public ArenaManager() {
        arena = new Arena();
        graph = new Graph();
    }

    /**
     * Método que retorna a arena gerenciada.
     * @return arena
     */
    public Arena getArena() {
        return arena;
    }

    /**
     * Método que retorna o grafo da arena.
     * @return graph
     */
    public Graph getGraph() {
        return graph;
    }

    /**
     * Método que atualiza as arestas de um determinado vértice.
     * @param board tabuleiro onde se encontra o vértice.
     * @param i linha do vértice.
     * @param j coluna do vértice.
     */
    public void updateEdgesArena(ArrayList<ArrayList<Floor>> board, int i, int j) {

        Floor floor = board.get(i).get(j);

        if (floor != null) {

            if (i >= 1) {
                Floor auxFloor = board.get(i - 1).get(j);
                if (auxFloor != null) graph.addEdge(floor.getVertex(), auxFloor.getVertex());
            }

            if (j >= 1) {
                Floor auxFloor = board.get(i).get(j - 1);
                if (auxFloor != null) graph.addEdge(floor.getVertex(), auxFloor.getVertex());
            }
        }
    }
}
