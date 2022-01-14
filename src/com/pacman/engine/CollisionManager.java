package com.pacman.engine;

import com.pacman.graphicinterface.components.controllers.GameObjectController;
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
    private final LinkedList<Collision> collisions;

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
     * @param dynamicControllers lista de objetos dinâmicos.
     * @param staticControllers lista de objetos estáticos.
     */
    public void checkCollisions(LinkedList<GameObjectController> dynamicControllers, LinkedList<GameObjectController> staticControllers) {

        collisions.clear();

        for (GameObjectController dGameObjectController : dynamicControllers) {
            DynamicGameObject dynamicGameObject = (DynamicGameObject) dGameObjectController.getGameObject();
            for (GameObjectController sGameObjectControlller : staticControllers) {
                GameObject gameObject = sGameObjectControlller.getGameObject();
                if (dynamicGameObject.getHitBox().hasIntersection(gameObject.getHitBox())) {
                    collisions.add(new Collision(dynamicGameObject, gameObject));
                }
            }
        }

        for (int i = 0; i < dynamicControllers.size(); i++) {
            GameObjectController dGameObjectControllerI =  dynamicControllers.get(i);
            DynamicGameObject dynamicGameObject_I = (DynamicGameObject) dGameObjectControllerI.getGameObject();
            for (int j = i + 1; j < dynamicControllers.size(); j++) {
                GameObjectController dGameObjectControllerJ =  dynamicControllers.get(j);
                DynamicGameObject dynamicGameObject_J = (DynamicGameObject) dGameObjectControllerJ.getGameObject();
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
                if (!((collider1 instanceof Ghost) && (collider2 instanceof Ghost))){
                    collider1.returnToOldPosition();
                    collider2.returnToOldPosition();
                }
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
}
