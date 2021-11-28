package com.pacman.systemelements;

/**
 * Classe que representa uma pílula de energia do jogo.
 */
public class EnergyPill extends GameObject {

    /**
     * Construtor padrão.
     * @param position posição em que o EnergyPill será criado.
     */
    public EnergyPill(Position position) {
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
        System.out.print('E');
    }
}
