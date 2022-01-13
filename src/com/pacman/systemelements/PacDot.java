package com.pacman.systemelements;

import com.pacman.engine.ScoreManager;
import com.pacman.engine.SystemGame;

/**
 * Classe que representa um pacdot do jogo.
 */
public class PacDot extends GameObject {

    /**
     * Construtor padrão.
     * Por padrão, a camada do PacDot é 2 (layer = 2) e não é um corpo rigido (rigidBody = false).
     * @param position {@link Position} onde será criado o PacDot
     */
    public PacDot (Position position) {
        setLayer(2);
        setRigidBody(false);
        setPosition(position);
        setDimension(new Dimension(5, 5));
    }

    @Override
    public void update() {

    }

    @Override
    public void onCollision() {
        if (getCollider() instanceof PacMan) {
            ScoreManager.addScore((PacMan) getCollider(), this);
            SystemGame.gameObjectManager.blinky().getVelocity().setModulus(SystemGame.gameObjectManager.blinky().getVelocity().getModulus() + 0.1);
            destroy();
        }
    }

    @Override
    public void print() {

    }
}
