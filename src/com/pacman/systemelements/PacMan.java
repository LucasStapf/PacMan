package com.pacman.systemelements;

public class PacMan extends GameObject {

    public PacMan() {
        setLayer(2);
        setRigidBody(true);
    }

    public PacMan(Position position) {
        setLayer(2);
        setRigidBody(true);
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
        System.out.print("P");
    }
}
