package com.pacman.engine;

import com.pacman.systemelements.Arena;
import com.pacman.systemelements.Collision;
import com.pacman.systemelements.DynamicGameObject;
import com.pacman.systemelements.GameObject;

import java.util.Iterator;
import java.util.LinkedList;

public class CollisionManager {

    private Arena arena;
    private LinkedList<GameObject> staticGameObjects;
    private LinkedList<DynamicGameObject> dynamicGameObjects;
    private LinkedList<Collision> collisions;

    public CollisionManager(Arena arena) throws NullPointerException {

        if (arena == null) throw new NullPointerException("Arena cant be null!");
        else this.arena = arena;

        collisions = new LinkedList<>();
        dynamicGameObjects = new LinkedList<>();
        staticGameObjects = new LinkedList<>();

        updateGameObjectLists();
    }

    private void updateGameObjectLists() {

        Iterator iterator = arena.getGameObjects().keySet().iterator();
        while (iterator.hasNext()) {
            GameObject gameObject = (GameObject) iterator.next();
            if (gameObject instanceof DynamicGameObject) dynamicGameObjects.add((DynamicGameObject) gameObject);
            else staticGameObjects.add(gameObject);
        }
    }

    private void checkCollisions() {

        Iterator dynamicIterator = dynamicGameObjects.iterator();
        Iterator staticIterator = staticGameObjects.iterator();

        while (dynamicIterator.hasNext()) {
            DynamicGameObject dynamicGameObject = (DynamicGameObject) dynamicIterator.next();
            while (staticIterator.hasNext()) {
                GameObject gameObject = (GameObject) staticIterator.next();
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

        Iterator iterator = collisions.iterator();

        while (iterator.hasNext()) {

            Collision collision = (Collision) iterator.next();

            GameObject collider1 = collision.getCollider1();
            GameObject collider2 = collision.getCollider2();

            collider1.setCollider(collider2);
            collider2.setCollider(collider1);
            collider1.onCollision();
            collider2.onCollision();
            collider1.setCollider(null);
            collider2.setCollider(null);

            iterator.remove();
        }
    }
}
