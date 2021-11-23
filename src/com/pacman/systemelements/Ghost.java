package com.pacman.systemelements;

import java.util.LinkedList;

public final class Ghost extends GameObject {

    public enum Movement {
        FOLLOW_PACMAN,
        RUN_AWAY_PACMAN,
        RANDOM;
    }

    private Movement movement = Movement.RANDOM;

    public Ghost() {
        setLayer(3);
        setRigidBody(true);
    }

    public Ghost(Position position) {
        setLayer(3);
        setRigidBody(true);
        setPosition(position);
    }

    @Override
    public void update() {

        switch (movement) {

            case RANDOM:
                break;

            case FOLLOW_PACMAN:
                break;

            case RUN_AWAY_PACMAN:
                break;
        }
    }

    @Override
    public void onCollision() {

    }

    private void followPath(LinkedList<Floor> path) {

        if (isCenteredOnFloor(path.getFirst())) {

            path.removeFirst();

            float deltaX = path.getFirst().getPosition().getX() - getPosition().getX();
            float deltaY = path.getFirst().getPosition().getY() - getPosition().getY();

            if (deltaX > 0) getVelocity().setDirection(Velocity.Direction.RIGHT);
            else if (deltaX < 0) getVelocity().setDirection(Velocity.Direction.LEFT);
            else if (deltaY > 0) getVelocity().setDirection(Velocity.Direction.UP);
            else if (deltaY < 0) getVelocity().setDirection(Velocity.Direction.DOWN);
        }

        translate();
    }
}
