package com.pacman.systemelements;

import com.pacman.engine.Vertex;
import java.util.LinkedList;

public final class Ghost extends DynamicGameObject {

    public enum Movement {
        FOLLOW_PACMAN,
        RUN_AWAY_PACMAN,
        RANDOM;
    }

    private Movement movement = Movement.RANDOM;

    private LinkedList<Vertex<Floor>> path = new LinkedList<>();

    public Ghost() {
        setLayer(3);
        setRigidBody(true);
        getVelocity().setModulus(1);
    }

    public Ghost(Position position) {
        setLayer(3);
        setRigidBody(true);
        setPosition(position);
        getVelocity().setModulus(1);
    }

    public void setPath(LinkedList<Vertex<Floor>> path) {
        this.path = path;
    }

    public void setMovement(Movement movement) {
        this.movement = movement;
    }

    @Override
    public void update() {

        switch (movement) {

            case RANDOM:
                break;

            case FOLLOW_PACMAN:
                followPath();
                break;

            case RUN_AWAY_PACMAN:
                break;
        }
    }

    @Override
    public void onCollision() {

    }

    @Override
    public void print() {
        System.out.print("G");
    }

    private void followPath() {

        if (path.isEmpty()) return;

        if (isCenteredOnFloor(path.getFirst().getT())) {

            path.removeFirst();
            if (path.isEmpty()) return;

            float deltaX = path.getFirst().getT().getPosition().getX() - getPosition().getX();
            float deltaY = path.getFirst().getT().getPosition().getY() - getPosition().getY();

            if (deltaX > 0) getVelocity().setDirection(Velocity.Direction.RIGHT);
            else if (deltaX < 0) getVelocity().setDirection(Velocity.Direction.LEFT);
            else if (deltaY > 0) getVelocity().setDirection(Velocity.Direction.UP);
            else if (deltaY < 0) getVelocity().setDirection(Velocity.Direction.DOWN);

        }

        translate(1);
    }
}
