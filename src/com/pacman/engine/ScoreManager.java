package com.pacman.engine;

public class ScoreManager {

    /**
     * Pontuação máxima atingida no jogo.
     */
    private int highScore;

    /**
     * Retorna a pontuação máxima atingida no jogo.
     * @return a pontuação máxima atingida no jogo.
     */
    public int highScore() {
        return highScore;
    }

    /**
     * Altera a pontuação máxima atingida no jogo.
     * @param highScore nova pontuação.
     */
    public void setHighScore(int highScore) {
        this.highScore = highScore;
    }

    /**
     * Pontuação atual.
     */
    private int gameScore;

    /**
     * Retorna a pontuação atual no jogo.
     * @return a pontuação atual no jogo.
     */
    public int gameScore() {
        return gameScore;
    }

    /**
     * Altera a pontuação atual no jogo.
     * @param gameScore nova pontuação.
     */
    public void setGameScore(int gameScore) {
        this.gameScore = gameScore;
    }

    private static final int bonus = 1;
    private static int bonusPacDot = 1;
    private static int bonusGhost = 1;

    private static int scoreFromGhost = 200;
    private static int scoreFromPacDot = 1;

}
