package com.pacman.systemelements;

import java.util.Objects;

/**
 * Classe que representa uma colisão entre dois {@link GameObject} no jogo.
 */
public final class Collision {

    /**
     * Primeiro colisor.
     */
    private GameObject collider1;

    /**
     * Segundo colisor.
     */
    private GameObject collider2;

    /**
     * Construtor padrão.
     * @param collider1 primeiro colisor.
     * @param collider2 segundo colisor.
     */
    public Collision(GameObject collider1, GameObject collider2) {
        this.collider1 = collider1;
        this.collider2 = collider2;
    }

    /**
     * Retorna o primeiro colisor.
     * @return o primeiro colisor.
     */
    public GameObject getCollider1() {
        return collider1;
    }

    /**
     * Retorna o segundor colisor.
     * @return o segundor colisor.
     */
    public GameObject getCollider2() {
        return collider2;
    }

//    public void handleCollision() {
//
//        this.collider1.setCollider(this.collider2);
//        this.collider2.setCollider(this.collider1);
//
//        this.collider1.onCollision();
//        this.collider2.onCollision();
//
//        this.collider1.setCollider(null);
//        this.collider2.setCollider(null);
//    }

    @Override
    public String toString() {
        return "Collision{" +
                "collider1=" + collider1 +
                ", collider2=" + collider2 +
                '}';
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
