package com.pacman.engine;

import com.pacman.systemelements.*;

import java.util.Iterator;
import java.util.LinkedList;

public class CollisionManager {

    private Arena arena;
    private LinkedList<GameObject> gameObjects;
    private LinkedList<GameObject> staticGameObjects;
    private LinkedList<DynamicGameObject> dynamicGameObjects;
    private LinkedList<Collision> collisions;

    public CollisionManager(LinkedList<GameObject> gameObjects) {
        this.gameObjects = gameObjects;
    }

    public CollisionManager(Arena arena) throws NullPointerException {

        if (arena == null) throw new NullPointerException("Arena cant be null!");
        else this.arena = arena;

        collisions = new LinkedList<>();
        dynamicGameObjects = new LinkedList<>();
        staticGameObjects = new LinkedList<>();

        updateGameObjectLists();
    }

    private void updateGameObjectLists() {

        Iterator<GameObject> iterator = arena.getHashGameObjects().keySet().iterator();
        while (iterator.hasNext()) {
            GameObject gameObject = iterator.next();
            if (gameObject instanceof DynamicGameObject) dynamicGameObjects.add((DynamicGameObject) gameObject);
            else staticGameObjects.add(gameObject);
        }
    }

    private void checkCollisions() {

        Iterator<DynamicGameObject> dynamicIterator = dynamicGameObjects.iterator();
        Iterator<GameObject> staticIterator = staticGameObjects.iterator();

        while (dynamicIterator.hasNext()) {
            DynamicGameObject dynamicGameObject = dynamicIterator.next();
            while (staticIterator.hasNext()) {
                GameObject gameObject = staticIterator.next();
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

    public void handleCollisions() {

        checkCollisions();

        Iterator<Collision> iterator = collisions.iterator();

        while (iterator.hasNext()) {

            Collision collision = iterator.next();

            GameObject collider1 = collision.getCollider1();
            GameObject collider2 = collision.getCollider2();

            if (collider1.isRigidBody() && collider2.isRigidBody()) {
                if (collider1 instanceof DynamicGameObject) {
                    breakOverlap((DynamicGameObject) collider1, collider2);
                } else breakOverlap((DynamicGameObject) collider2, collider1);
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


    private void moveAwayInX(DynamicGameObject dynamicGameObject, GameObject gameObject, float distance) {

        float x1, y1, x2;
        x1 = dynamicGameObject.getPosition().getX();
        y1 = dynamicGameObject.getPosition().getY();
        x2 = gameObject.getPosition().getX();

        if (x1 > x2) dynamicGameObject.setPosition(new Position(x1 + distance, y1));
        else dynamicGameObject.setPosition(new Position(x1 - distance, y1));
    }

    private void moveAwayInY(DynamicGameObject dynamicGameObject, GameObject gameObject, float distance) {

        float x1, y1, y2;
        x1 = dynamicGameObject.getPosition().getX();
        y1 = dynamicGameObject.getPosition().getY();
        y2 = gameObject.getPosition().getY();

        if (y1 > y2) dynamicGameObject.setPosition(new Position(x1, y1 + distance));
        else dynamicGameObject.setPosition(new Position(x1, y1 - distance));
    }

    public void breakOverlap(DynamicGameObject dGameObject, GameObject gameObject) {

        float w1, w2, h1, h2, x1, x2, y1, y2;

        w1 = dGameObject.getHitBox().getWidth() / 2;
        h1 = dGameObject.getHitBox().getHeight() / 2;
        x1 = dGameObject.getPosition().getX();
        y1 = dGameObject.getPosition().getY();

        w2 = gameObject.getHitBox().getWidth() / 2;
        h2 = gameObject.getHitBox().getHeight() / 2;
        x2 = gameObject.getPosition().getX();
        y2 = gameObject.getPosition().getY();

        float dX = ((w1 + w2) / 2) - Math.abs(x1 - x2);
        float dY = ((h1 + h2) / 2) - Math.abs(y1 - y2);

        if (gameObject instanceof DynamicGameObject) {

            DynamicGameObject dGameObjectAux = (DynamicGameObject) gameObject;

            if (Velocity.isSameDirection(dGameObject.getVelocity(), dGameObjectAux.getVelocity())) { //

                DynamicGameObject dynamicGameObject;

                if (dGameObject.getVelocity().getModulus() > dGameObjectAux.getVelocity().getModulus()) {
                    dynamicGameObject = dGameObject;
                } else {
                    dynamicGameObject = dGameObjectAux;
                    dGameObjectAux = dGameObject;
                }

                if (Velocity.isVertical(dynamicGameObject.getVelocity())) {
                    moveAwayInY(dynamicGameObject, dGameObjectAux, dY);
                } else {
                    moveAwayInX(dynamicGameObject, dGameObjectAux, dX);
                }

            } else if (Velocity.isOppositeDirection(dGameObject.getVelocity(), dGameObjectAux.getVelocity())) {

                float dX1, dX2, dY1, dY2, r12;
                r12 = dGameObject.getVelocity().getModulus() / dGameObjectAux.getVelocity().getModulus();

                if (Velocity.isVertical(dGameObject.getVelocity())) {

                    dY1 = dY / (1 + (1 / r12));
                    dY2 = dY / (1 + r12);

                    moveAwayInY(dGameObject, dGameObjectAux, dY1);
                    moveAwayInY(dGameObjectAux, dGameObject, dY2);

                } else {

                    dX1 = dX / (1 + (1 / r12));
                    dX2 = dX / (1 + r12);

                    moveAwayInX(dGameObject, dGameObjectAux, dX1);
                    moveAwayInX(dGameObjectAux, dGameObject, dX2);
                }

            } else { // Perpendiculares


            }

        } else {

            if (Velocity.isVertical(dGameObject.getVelocity())) {
                moveAwayInY(dGameObject, gameObject, dY);
            } else {
                moveAwayInX(dGameObject, gameObject, dX);
            }
        }
    }
}
