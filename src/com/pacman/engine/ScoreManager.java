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
     * Altera a pontuação atual no jogo e atualiza o valor na tela.
     * @param gameScore nova pontuação.
     */
    public void setGameScore(int gameScore) {
        this.gameScore = gameScore;
        GameSystem.screen.gameScoreBoard().setValue(gameScore);
    }


    /**
     * Bonus aplicado na pontuação padrão originada pelo Ghost.
     */
    public static int bonusScoreGhost = 1;

    /**
     * Quantidade de pontos dada pelo {@link com.pacman.systemelements.Ghost}
     */
    public final static int scoreFromGhost = 200;

    /**
     * Quantidade de pontos dada pela {@link com.pacman.systemelements.EnergyPill}
     */
    public final static int scoreFromEnergyPill = 50;

    /**
     * Quantidade de pontos dado pelo {@link com.pacman.systemelements.PacDot}
     */
    public final static int scoreFromPacDot = 10;

    /**
     * Método utilizado para adicionar pontos na pontuação atual.
     * @param points quantidade a ser adicionada.
     */
    public void addGameScore(int points) {
        int score = gameScore + points;
        setGameScore(score);
    }

}
