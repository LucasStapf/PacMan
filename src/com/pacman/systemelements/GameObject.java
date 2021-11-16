package com.pacman.systemelements;

import com.pacman.engine.Time;

public abstract class GameObject extends SceneElement {

    private HitBox hitBox = new HitBox(getDimension(), getPosition());
    private GameObject collider;
    private Velocity velocity;

    private boolean rigidBody;

    public abstract void update();
    public abstract void onCollision();

    public HitBox getHitBox() {
        return hitBox;
    }

    public GameObject getCollider() {
        return collider;
    }

    public Velocity getVelocity() {
        return velocity;
    }

    public boolean isRigidBody() {
        return rigidBody;
    }

    public void setCollider(GameObject collider) {
        this.collider = collider;
    }

    public void setVelocity(Velocity velocity) {
        this.velocity = velocity;
    }

    public void setRigidBody(boolean rigidBody) {
        this.rigidBody = rigidBody;
    }

    public void translate() {

        float x = getPosition().getX();
        float y = getPosition().getY();

        switch (velocity.getDirection()) {

            case UP:
                y += velocity.getModulus() * Time.getDeltaTimeSEC();
                getPosition().setY(y);
                break;

            case DOWN:
                y -= velocity.getModulus() * Time.getDeltaTimeSEC();
                getPosition().setY(y);
                break;

            case LEFT:
                x -= velocity.getModulus() * Time.getDeltaTimeSEC();
                getPosition().setX(x);
                break;

            case RIGHT:
                x += velocity.getModulus() * Time.getDeltaTimeSEC();
                getPosition().setX(x);
                break;
        }

        getHitBox().setPosition(getPosition());
    }

    public boolean isCenteredOnFloor(Floor floor) {
        float deltaX = Math.abs(getPosition().getX() - floor.getPosition().getX());
        float deltaY = Math.abs(getPosition().getY() - floor.getPosition().getY());
        return (deltaX < 0.10f && deltaY < 0.10f);
    }

    @Override
    public void setPosition(Position position) throws NullPointerException {
        super.setPosition(position);
        getHitBox().setPosition(getPosition());
    }
}
