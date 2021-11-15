package com.pacman.systemelements;

public class Velocity {

    public enum Direction {
        UP,
        DOWN,
        LEFT,
        RIGHT,
        NONE;
    }

    private float modulus;
    private Direction direction;

    public Velocity() {
        this.modulus = 0.0f;
        this.direction = Direction.NONE;
    }

    public Velocity(float modulus, Direction direction) throws IllegalArgumentException {
        if (modulus < 0) throw new IllegalArgumentException("Modulos must be a positive number");
        this.modulus = modulus;
        this.direction = direction;
    }

    public float getModulus() {
        return modulus;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setModulus(float modulus) throws IllegalArgumentException {
        if (modulus < 0) throw new IllegalArgumentException(); // msg
        this.modulus = modulus;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public void updateVelocity(float modulus, Direction direction) throws IllegalArgumentException {
        setModulus(modulus);
        setDirection(direction);
    }

    @Override
    public String toString() {
        return "Velocity{" +
                "module=" + modulus +
                ", direction=" + direction +
                '}';
    }
}
