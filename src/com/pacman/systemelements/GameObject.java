package com.pacman.systemelements;

public abstract class GameObject extends SceneElement {

    private HitBox hitBox = new HitBox(getDimension(), getPosition());
    private GameObject collider;

    private boolean rigidBody;

    public abstract void update();
    public abstract void onCollision();

    public HitBox getHitBox() {
        return hitBox;
    }

    public GameObject getCollider() {
        return collider;
    }

    public boolean isRigidBody() {
        return rigidBody;
    }

    public void setCollider(GameObject collider) {
        this.collider = collider;
    }

    public void setRigidBody(boolean rigidBody) {
        this.rigidBody = rigidBody;
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
