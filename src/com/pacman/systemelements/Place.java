package com.pacman.systemelements;

public final class Place extends GameObject {

    public Place(Position position) {
        setPosition(position);
        setOldPosition(position);
        setRigidBody(false);
    }

    @Override
    public void update() {

    }

    @Override
    public void onCollision() {

    }
}
