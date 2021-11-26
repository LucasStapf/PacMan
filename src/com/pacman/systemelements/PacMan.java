package com.pacman.systemelements;

public class PacMan extends DynamicGameObject {

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
        System.out.println("Collision! Collider is: " + getCollider());
    }

    @Override
    public void print() {
        System.out.print("P");
    }
}
