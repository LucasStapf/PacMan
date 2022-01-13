package com.pacman.systemelements;

import com.pacman.engine.SystemGame;

/**
 * Classe que representa o Pac-Man do jogo.
 */
public class PacMan extends DynamicGameObject {

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
        translate(SystemGame.deltaTime);
    }

    @Override
    public void onCollision() {

    }

}
