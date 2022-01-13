package com.pacman.systemelements;

import com.pacman.engine.GameSystem;
import com.pacman.engine.ScoreManager;

/**
 * Classe que representa uma pílula de energia do jogo.
 */
public class EnergyPill extends GameObject {

    /**
     * Construtor padrão.
     * @param position {@link Position} em que o EnergyPill será criado.
     */
    public EnergyPill(Position position) {
        setLayer(2);
        setRigidBody(false);
        setPosition(position);
        setDimension(new Dimension(12, 12));
    }

    @Override
    public void update() {

    }

    @Override
    public void onCollision() {
        if (getCollider() instanceof PacMan) {
            GameSystem.player.addGameScore(ScoreManager.scoreFromEnergyPill);
            GameSystem.levelManager.startEffectEnergyPill();
            destroy();
        }
    }

}
