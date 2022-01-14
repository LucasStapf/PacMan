package com.pacman.engine;

public class PlayerManager {

    /**
     * Pontuação máxima atingida no jogo.
     */
    private int highScore = 0; // Valor padrão.

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
        GameSystem.screen.highScoreBoard().setValue(highScore);
    }

    /**
     * Pontuação atual.
     */
    private int gameScore = 0; // Valor padrão.

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
     * Método utilizado para adicionar pontos na pontuação atual.
     * @param points quantidade a ser adicionada.
     */
    public void addGameScore(int points) {
        int score = gameScore + points;
        setGameScore(score);
    }

    /**
     * Número atual de vidas do jogador.
     */
    private int lifes = 3; // Valor padrão.

    /**
     * Retorna a quantidade de vidas do jogador.
     * @return a quantidade de vidas do jogador.
     */
    public int lifes() {
        return lifes;
    }

    /**
     * Altera a quantidade de vidas do jogador.
     * @param lifes nova quantidade.
     */
    public void setLifes(int lifes) {
        this.lifes = lifes;
        GameSystem.screen.lifeBoard().setLifes(lifes);
    }

    /**
     * Adiciona o valor passado no número atual de vidas do jogador.
     * @param lifes valor a ser adicionado.
     */
    public void addLifes(int lifes) {
        this.lifes += lifes;
        setLifes(this.lifes);
    }

    /**
     * Remove o valor passado no número atual de vidas do jogador.
     * @param lifes valor a ser removido.
     */
    public void removeLifes(int lifes) {
        this.lifes -= lifes;
        setLifes(this.lifes);
    }

    /**
     * Reinicia os valores para o padrão (menos o highscore).
     */
    public void restart() {
        setLifes(3);
        setHighScore(Math.max(gameScore, highScore));
        setGameScore(0);
    }
}
