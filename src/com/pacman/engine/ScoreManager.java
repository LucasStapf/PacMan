package com.pacman.engine;

public class ScoreManager {

    /**
     * Pontuação máxima atingida no jogo.
     */
    private int highScore = 0;

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
    private int gameScore = 0;

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
        GameSystem.screenManager.gameScoreBoard().setValue(gameScore);
    }


    public final static int scoreFromGhost = 200;
    public final static int scoreFromEnergyPill = 50;
    public final static int scoreFromPacDot = 10;

    public void addGameScore(int points) {
        int score = gameScore + points;
        setGameScore(score);
    }

}
