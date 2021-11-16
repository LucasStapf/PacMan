package com.pacman.systemelements;

public class GameObject extends SceneElement {

    private HitBox hitBox = new HitBox();
    private Collision collision;
    private Velocity velocity;

    private boolean rigidBody;

    public HitBox getHitBox() {
        return hitBox;
    }

    public Collision getCollision() {
        return collision;
    }

    public Velocity getVelocity() {
        return velocity;
    }

    public boolean isRigidBody() {
        return rigidBody;
    }

    public void setCollision(Collision collision) {
        this.collision = collision;
    }

    public void setVelocity(Velocity velocity) {
        this.velocity = velocity;
    }

    public void setRigidBody(boolean rigidBody) {
        this.rigidBody = rigidBody;
    }
}
