package com.pacman.systemelements;

public class Position {

    private float x;
    private float y;

    public Position() {
        this.x = 0.0f;
        this.y = 0.0f;
    }

    public Position(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void updatePosition(float x, float y) {
        setX(x);
        setY(y);
    }

    public void copy(Position position) {
        this.x = position.x;
        this.y = position.y;
    }

    @Override
    public String toString() {
        return "Position{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
