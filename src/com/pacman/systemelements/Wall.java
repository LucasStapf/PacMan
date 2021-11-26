package com.pacman.systemelements;

public class Wall extends GameObject {

    public enum Orientation {
        HORIZONTAL,
        VERTICAL,
        CORNER;
    }

    public Orientation orientation;

    public Wall(Position position, Orientation orientation) {
        setLayer(1);
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

    @Override
    public void print() {
        switch (orientation) {
            case HORIZONTAL:
                System.out.print("-");
                break;
            case VERTICAL:
                System.out.print("|");
                break;
            case CORNER:
                System.out.print("+");
                break;
        }
    }

}
