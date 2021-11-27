package com.pacman.systemelements;

public class Fruit extends GameObject {

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
        System.out.print('F');
    }
}
