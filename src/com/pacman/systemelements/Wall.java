package com.pacman.systemelements;

public class Wall extends GameObject {

    public enum Orientation {
        HORIZONTAL,
        VERTICAL,
        CORNER;
    }

    public Orientation orientation;

    public Wall(Position position, Orientation orientation) {
        setRigidBody(true);
        setPosition(position);
        this.orientation = orientation;
    }

    @Override
    public void update() {

    }

    @Override
    public void onCollision() {

    }
}
