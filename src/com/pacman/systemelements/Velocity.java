package com.pacman.systemelements;

public final class Velocity {

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

    public static boolean isHorizontal(Velocity velocity) {
        return (velocity.direction.equals(Direction.RIGHT) || velocity.direction.equals(Direction.LEFT));
    }

    public static boolean isVertical(Velocity velocity) {
        return (velocity.direction.equals(Direction.UP) || velocity.direction.equals(Direction.DOWN));
    }

    public static boolean isSameDirection(Velocity v1, Velocity v2) {
        return v1.direction.equals(v2.direction);
    }

    public static boolean isOppositeDirection(Velocity v1, Velocity v2) {

        if (v1.direction.equals(Direction.UP)) {
            if (v2.direction.equals(Direction.DOWN)) return true;
            else return false;
        }

        if (v1.direction.equals(Direction.DOWN)) {
            if (v2.direction.equals(Direction.UP)) return true;
            else return false;
        }

        if (v1.direction.equals(Direction.LEFT)) {
            if (v2.direction.equals((Direction.RIGHT))) return true;
            else return false;
        }

        if (v1.direction.equals(Direction.RIGHT)) {
            if (v2.direction.equals(Direction.LEFT)) return true;
            else return false;
        }

        return true;
    }

    public static boolean isPerpendicularDirection(Velocity v1, Velocity v2) {
        if (v1.direction.equals(Direction.NONE) || v2.direction.equals(Direction.NONE)) return false;
        else if (isSameDirection(v1, v2)) return false;
        else if (isOppositeDirection(v1, v2)) return false;
        else return true;
    }

    public static int sign(Velocity v) {
        if (v.direction.equals(Direction.UP) || v.direction.equals(Direction.RIGHT)) return 1;
        else if (v.direction.equals(Direction.DOWN) || v.direction.equals(Direction.LEFT)) return -1;
        else return 0;
    }

    @Override
    public String toString() {
        return "Velocity{" +
                "module=" + modulus +
                ", direction=" + direction +
                '}';
    }
}
