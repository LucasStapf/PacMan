package com.pacman.systemelements;

import com.pacman.engine.SystemGame;

/**
 * Classe que representa o Pac-Man do jogo.
 */
public class PacMan extends DynamicGameObject {

    /**
     * Atributo que registra a quantidade de vida que o PacMan possui.
     */
    private int life;

    /**
     * Atributo que guarda a quantidade de pontos obtidos pelo PacMan.
     */
    private int score;

    /**
     * Construtor padrão.
     * Por padrão, a camada do PacMan é 3 (layer = 3) e é um corpo rígido (rigidBody = true).
     * @param position {@link Position} onde será criado o PacMan.
     */
    public PacMan(Position position) {
        setLayer(3);
        setRigidBody(true);
        setPosition(position);
        setOldPosition(position);
        setDimension(new Dimension(18, 18));
        getVelocity().setModulus(70);
    }

    public int getLife() {
        return life;
    }

    public int getScore() {
        return score;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public void update() {
        java.lang.System.out.println("Score: " + score);
        translate(SystemGame.deltaTime);
    }

    @Override
    public void onCollision() {

    }

    @Override
    public void print() {
        java.lang.System.out.print("P");
    }
}
