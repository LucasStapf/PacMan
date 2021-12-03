package com.pacman.systemelements;

import java.util.*;

/**
 * Classe que representa a arena do jogo.
 */
public class Arena {

    /**
     * Atributo que guarda as posições de cada {@link SceneElement} no tabuleiro.
     */
    private ArrayList<ArrayList<Floor>> board;

    /**
     * Construtor padrão.
     */
    public Arena() {
        board = new ArrayList<ArrayList<Floor>>();
    }

    /**
     * Método que retorna o tabuleiro do jogo.
     * @return o tabuleiro do jogo.
     */
    public ArrayList<ArrayList<Floor>> getBoard() {
        return board;
    }

    /**
     * Método que altera o atual tabuleiro da arena.
     * @param board novo tabuleiro.
     */
    public void setBoard(ArrayList<ArrayList<Floor>> board) {
        this.board = board;
    }

    public boolean hasFloorOn(double x, double y) {

        int i = (int) Math.round(y / Floor.height - 0.5);
        int j = (int) Math.round(x / Floor.width - 0.5);

        return board.get(i).get(j) != null;
    }

    public Floor getFloorOn(double x, double y) {

        int i = (int) Math.round(y / Floor.height - 0.5);
        int j = (int) Math.round(x / Floor.width - 0.5);

        return board.get(i).get(j);
    }
}
