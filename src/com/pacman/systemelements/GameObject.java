package com.pacman.systemelements;

public class GameObject extends SceneElement {

    private HitBox hitBox = new HitBox();
    private GameObject collider;
    private Velocity velocity;

    private boolean rigidBody;

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
}
