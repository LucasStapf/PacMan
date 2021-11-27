package com.pacman.systemelements;

public class EnergyPill extends GameObject {

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
