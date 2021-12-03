package com.pacman.engine;

import com.pacman.graphicinterface.GameObjectController;
import com.pacman.systemelements.DynamicGameObject;
import com.pacman.systemelements.GameObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Classe responsável por gerenciar todos os GameObject do jogo.
 */
public class GameObjectManager {

    /**
     * Atributo que guarda uma lista encadeada com todos os GameObject do jogo.
     */
    private LinkedList<GameObject> gameObjects;

    /**
     * Atributo que guarda a classe GameObjectController de cada GameObject do jogo.
     */
    private HashMap<GameObject, GameObjectController> gameObjectController;

    /**
     * Atributo que guarda todos os objetos que são estáticos (não se movem) do jogo.
     */
    private LinkedList<GameObject> staticGameObjects;

    /**
     * Atributo que guarda todos os objetos que são dinâmicos (podem se mover) do jogo.
     */
    private LinkedList<DynamicGameObject> dynamicGameObjects;

    private LinkedList<GameObject> objectsToDestroy;

    /**
     * Construtor padrão.
     */
    public GameObjectManager() {
        gameObjects = new LinkedList<>();
        gameObjectController = new HashMap<>();
        staticGameObjects = new LinkedList<>();
        dynamicGameObjects = new LinkedList<>();
        objectsToDestroy = new LinkedList<>();
    }

    /**
     * Método que retorna a lista de todos os GameObject do jogo.
     * @return gameObjects
     */
    public LinkedList<GameObject> getGameObjects() {
        return gameObjects;
    }


    public HashMap<GameObject, GameObjectController> getGameObjectController() {
        return gameObjectController;
    }

    /**
     * Método que retorna todos os objetos estáticos do jogo.
     * @return staticGameObjects
     */
    public LinkedList<GameObject> getStaticGameObjects() {
        return staticGameObjects;
    }

    /**
     * Método que retorna todos os objetos dinâmicos do jogo.
     * @return dynamicGameObjects
     */
    public LinkedList<DynamicGameObject> getDynamicGameObjects() {
        return dynamicGameObjects;
    }

    public LinkedList<GameObject> getObjectsToDestroy() {
        return objectsToDestroy;
    }

    //    public void setGameObjects(LinkedList<GameObject> gameObjects) {
//        this.gameObjects = gameObjects;
//    }
//
//    public void removeGameObject(GameObject gameObject){
//        gameObjects.remove(gameObject);
//    }

    /**
     * Método que atualiza as listas de objetos estáticos e dinâmicos com base na lista geral do GameObject.
     */
    public void updateGameObjectLists() {

        staticGameObjects.clear();
        dynamicGameObjects.clear();

        Iterator<GameObject> iterator = gameObjects.iterator();
        while (iterator.hasNext()) {
            GameObject gameObject = iterator.next();
            if (gameObject instanceof DynamicGameObject) dynamicGameObjects.add((DynamicGameObject) gameObject);
            else staticGameObjects.add(gameObject);
        }
    }

    /**
     * Método que chama o método update() de todos os GameObject do jogo.
     */
    public void updateAllGameObjects() {
        for(GameObject gameObject: gameObjects) {
            gameObject.update();
        }
    }

    public void destroyGameObjects() {

        if (objectsToDestroy.isEmpty()) return;

        for(GameObject gameObject: objectsToDestroy){
//            System.out.println(objectsToDestroy.size());
//            System.out.println(gameObject);
//            System.out.println(gameObjectController.get(gameObject));
            gameObjectController.get(gameObject).destroy();
            gameObjects.remove(gameObject);
            gameObjectController.remove(gameObject);
        }

        objectsToDestroy.clear();
        updateGameObjectLists();
    }
}
