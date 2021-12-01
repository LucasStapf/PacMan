package com.pacman.engine;

import com.pacman.systemelements.*;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * Classe responsável por gerenciar todas as colisões da arena do jogo.
 */
public class CollisionManager {

    /**
     * Atributo que armazena todas as colisões ocorridas e não tratadas do jogo.
     */
    private LinkedList<Collision> collisions;

    /**
     * Construtor padrão.
     */
    public CollisionManager() {
        collisions = new LinkedList<>();
    }

    /**
     * Método que retorna a lista de colisões não tratadas.
     * @return collisions
     */
    public LinkedList<Collision> getCollisions() {
        return collisions;
    }

    /**
     * Método que verifica se há colisões entre os GameObjects da arena do jogo.
     */
    public void checkCollisions(LinkedList<DynamicGameObject> dynamicGameObjects, LinkedList<GameObject> staticGameObjects) {

        collisions.clear();

        Iterator<DynamicGameObject> dynamicGameObjectIterator = dynamicGameObjects.iterator();

        while (dynamicGameObjectIterator.hasNext()) {
            DynamicGameObject dynamicGameObject = dynamicGameObjectIterator.next();
            Iterator<GameObject> staticGameObjectIterator = staticGameObjects.iterator();
            while (staticGameObjectIterator.hasNext()) {
                GameObject gameObject = staticGameObjectIterator.next();
                if (dynamicGameObject.getHitBox().hasIntersection(gameObject.getHitBox())) {
                    collisions.add(new Collision(dynamicGameObject, gameObject));
                }
            }
        }

        for (int i = 0; i < dynamicGameObjects.size(); i++) {
            DynamicGameObject dynamicGameObject_I = dynamicGameObjects.get(i);
            for (int j = i + 1; j < dynamicGameObjects.size(); j++) {
                DynamicGameObject dynamicGameObject_J = dynamicGameObjects.get(j);
                if (dynamicGameObject_I.getHitBox().hasIntersection(dynamicGameObject_J.getHitBox())) {
                    collisions.add(new Collision(dynamicGameObject_I, dynamicGameObject_J));
                }
            }
        }
    }

    public void checkCollisions(LinkedList<DynamicGameObject> dynamicGameObjects) {

        collisions.clear();

        for (int i = 0; i < dynamicGameObjects.size(); i++) {
            DynamicGameObject dynamicGameObject_I = dynamicGameObjects.get(i);
            for (int j = i + 1; j < dynamicGameObjects.size(); j++) {
                DynamicGameObject dynamicGameObject_J = dynamicGameObjects.get(j);
                if (dynamicGameObject_I.getHitBox().hasIntersection(dynamicGameObject_J.getHitBox())) {
                    collisions.add(new Collision(dynamicGameObject_I, dynamicGameObject_J));
                }
            }
        }
    }

    /**
     * Método que realiza as colisões e as trata.
     */
    public void handleCollisions() {

        Iterator<Collision> iterator = collisions.iterator();

        while (iterator.hasNext()) {

            Collision collision = iterator.next();

            GameObject collider1 = collision.getCollider1();
            GameObject collider2 = collision.getCollider2();

            if (collider1.isRigidBody() && collider2.isRigidBody()) {
                collider1.returnToOldPosition();
                collider2.returnToOldPosition();
            }

            collider1.setCollider(collider2);
            collider2.setCollider(collider1);
            collider1.onCollision();
            collider2.onCollision();
            collider1.setCollider(null);
            collider2.setCollider(null);

            iterator.remove();
        }
    }


    /**
     * Método em construção
     * @param dynamicGameObject
     * @param gameObject
     * @param distance
     */
    private void moveAwayInX(DynamicGameObject dynamicGameObject, GameObject gameObject, double distance) {

        double x1;
        double y1;
        double x2;
        x1 = dynamicGameObject.getPosition().getX();
        y1 = dynamicGameObject.getPosition().getY();
        x2 = gameObject.getPosition().getX();

        if (x1 > x2) dynamicGameObject.setPosition(new Position(x1 + distance, y1));
        else dynamicGameObject.setPosition(new Position(x1 - distance, y1));
    }

    /**
     * Método em construção
     * @param dynamicGameObject
     * @param gameObject
     * @param distance
     */
    private void moveAwayInY(DynamicGameObject dynamicGameObject, GameObject gameObject, double distance) {

        double x1;
        double y1;
        double y2;
        x1 = dynamicGameObject.getPosition().getX();
        y1 = dynamicGameObject.getPosition().getY();
        y2 = gameObject.getPosition().getY();

        if (y1 > y2) dynamicGameObject.setPosition(new Position(x1, y1 + distance));
        else dynamicGameObject.setPosition(new Position(x1, y1 - distance));
    }

    /**
     * Método em construção
     * @param dGameObject
     * @param gameObject
     */
    public void breakOverlap(DynamicGameObject dGameObject, GameObject gameObject) {

        double w1, w2;
        double h1, h2;
        double x1, x2;
        double y1, y2;

        w1 = dGameObject.getHitBox().getWidth() / 2;
        h1 = dGameObject.getHitBox().getHeight() / 2;
        x1 = dGameObject.getPosition().getX();
        y1 = dGameObject.getPosition().getY();

        w2 = gameObject.getHitBox().getWidth() / 2;
        h2 = gameObject.getHitBox().getHeight() / 2;
        x2 = gameObject.getPosition().getX();
        y2 = gameObject.getPosition().getY();

        double dX = ((w1 + w2) / 2) - Math.abs(x1 - x2);
        double dY = ((h1 + h2) / 2) - Math.abs(y1 - y2);

        moveAwayInX(dGameObject, gameObject, dX);
        moveAwayInY(dGameObject, gameObject, dY);
    }
}
