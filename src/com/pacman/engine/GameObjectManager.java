package com.pacman.engine;

import com.pacman.systemelements.DynamicGameObject;
import com.pacman.systemelements.GameObject;

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
     * Atributo que guarda todos os objetos que são estáticos (não se movem) do jogo.
     */
    private LinkedList<GameObject> staticGameObjects;

    /**
     * Atributo que guarda todos os objetos que são dinâmicos (podem se mover) do jogo.
     */
    private LinkedList<DynamicGameObject> dynamicGameObjects;

    /**
     * Construtor padrão.
     */
    public GameObjectManager() {
        gameObjects = new LinkedList<>();
        staticGameObjects = new LinkedList<>();
        dynamicGameObjects = new LinkedList<>();
    }

    /**
     * Método que retorna a lista de todos os GameObject do jogo.
     * @return gameObjects
     */
    public LinkedList<GameObject> getGameObjects() {
        return gameObjects;
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
}
