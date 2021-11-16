package com.pacman.systemelements;

import java.util.Objects;

public final class Collision {

    private GameObject collider1;
    private GameObject collider2;

    public Collision(GameObject collider1, GameObject collider2) {
        this.collider1 = collider1;
        this.collider2 = collider2;
    }

    public GameObject getCollider1() {
        return collider1;
    }

    public GameObject getCollider2() {
        return collider2;
    }

    public void handleCollision() {

        this.collider1.setCollider(this.collider2);
        this.collider2.setCollider(this.collider1);

        this.collider1.onCollision();
        this.collider2.onCollision();

        this.collider1.setCollider(null);
        this.collider2.setCollider(null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Collision collision = (Collision) o;
        return (collider1.equals(collision.collider1) && collider2.equals(collision.collider2)) ||
                (collider1.equals(collision.collider2) && collider2.equals(collision.collider1));
    }

    @Override
    public int hashCode() {
        return Objects.hash(collider1, collider2);
    }
}
