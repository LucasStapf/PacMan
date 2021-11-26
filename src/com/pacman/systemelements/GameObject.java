package com.pacman.systemelements;

import com.pacman.engine.Time;

public abstract class GameObject extends SceneElement {

    private HitBox hitBox = new HitBox(getDimension(), getPosition());
    private GameObject collider;
    private Velocity velocity = new Velocity();

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

    public void translate(float time) {

        float x = getPosition().getX();
        float y = getPosition().getY();

        switch (velocity.getDirection()) {

            case UP:
                y += velocity.getModulus() * time;
                getPosition().setY(y);
                break;

            case DOWN:
                y -= velocity.getModulus() * time;
                getPosition().setY(y);
                break;

            case LEFT:
                x -= velocity.getModulus() * time;
                getPosition().setX(x);
                break;

            case RIGHT:
                x += velocity.getModulus() * time;
                getPosition().setX(x);
                break;
        }

        getHitBox().setPosition(getPosition());
    }

    public boolean isOnFloor(Floor floor) {

        float x = floor.getPosition().getX();
        float y = floor.getPosition().getY();
        float[] projX = {x - (floor.getDimension().getWidth() / 2), x + (floor.getDimension().getWidth() / 2)};
        float[] projY = {y - (floor.getDimension().getHeight() / 2), y + (floor.getDimension().getHeight() / 2)};

        if (getPosition().getX() < projX[0]) return false;
        else if (getPosition().getX() > projX[1]) return false;
        else if (getPosition().getY() < projY[0]) return false;
        else if (getPosition().getY() > projY[1]) return false;

        return true;
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
