package com.pacman.systemelements;

import com.pacman.engine.GraphicManager;

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
    private int totalPoints;

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
    }

    @Override
    public void update() {
        System.out.println("PacMan: " + getPosition());
        translate(GraphicManager.getDeltaTime());
    }

    @Override
    public void onCollision() {

        System.out.println("Colisão!");

//        if (getCollider() instanceof Ghost) {
//            System.out.println("Collision with Ghost!");
//            SystemManager.end();
//        }
//
//        // temp
//        if (getVelocity().getDirection() == Velocity.Direction.LEFT) {
//            getVelocity().setDirection(Velocity.Direction.RIGHT);
//        } else if (getVelocity().getDirection() == Velocity.Direction.RIGHT) {
//            getVelocity().setDirection(Velocity.Direction.LEFT);
//        }
    }

    @Override
    public void print() {
        System.out.print("P");
    }
}
