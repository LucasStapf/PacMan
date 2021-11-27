package com.pacman.systemelements;

public class PacDot extends GameObject {

    public PacDot (Position position) {
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
        System.out.print('.');
    }
}
