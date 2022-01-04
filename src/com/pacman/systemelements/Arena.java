package com.pacman.systemelements;

import java.util.*;

/**
 * Classe que representa a arena do jogo.
 */
public class Arena {

    /**
     * Atributo que guarda as posições de cada {@link Floor} no tabuleiro.
     */
    private ArrayList<ArrayList<Floor>> board;

    /**
     * Construtor padrão.
     */
    public Arena() {
        board = new ArrayList<>();
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

    /**
     * Verifica se há um {@link Floor} num determinado ponto do plano.
     * @param x abscissa do ponto.
     * @param y ordenada do ponto.
     * @return true caso houver um {@link Floor} no ponto passado, false caso contrário.
     */
    public boolean hasFloorOn(double x, double y) {

        int i = (int) Math.round(y / Floor.height - 0.5);
        int j = (int) Math.round(x / Floor.width - 0.5);

        return board.get(i).get(j) != null;
    }

    /**
     * Retorna o {@link Floor} presente nas coordenadas passadas.
     * @param x abscissa do ponto.
     * @param y ordenada do ponto.
     * @return o {@link Floor} presente no ponto.
     */
    public Floor getFloorOn(double x, double y) {

        int i = (int) Math.round(y / Floor.height - 0.5);
        int j = (int) Math.round(x / Floor.width - 0.5);

        return board.get(i).get(j);
    }
}
