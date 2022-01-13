package com.pacman.systemelements;

/**
 * Classe que representa uma fruta do jogo.
 */
public class Fruit extends GameObject {

    /**
     * Construtor padrão.
     * @param position {@link Position} em que a Fruit será criado.
     */
    public Fruit(Position position) {
        setLayer(2);
        setRigidBody(false);
        setPosition(position);
    }

    @Override
    public void update() {

    }

    @Override
    public void onCollision() {

    }

    @Override
    public void print() {

    }
}
