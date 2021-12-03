package com.pacman.systemelements;

import com.pacman.engine.GraphicManager;
import com.pacman.engine.System;

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


    @Override
    public void update() {
        translate(System.deltaTime);
    }

    @Override
    public void onCollision() {

    }

    @Override
    public void print() {
        java.lang.System.out.print("P");
    }
}
