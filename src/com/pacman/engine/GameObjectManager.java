package com.pacman.engine;

import com.pacman.systemelements.GameObject;

import java.util.LinkedList;

public class GameObjectManager {

    private LinkedList<GameObject> gameObjects;

    public GameObjectManager(LinkedList<GameObject> gameObjects) {
        this.gameObjects = gameObjects;
    }

    public LinkedList<GameObject> getGameObjects() {
        return gameObjects;
    }

    public void removeGameObject(GameObject gameObject){
        gameObjects.remove(gameObject);
    }

    public void updateAllGameObjects() {
        for(GameObject gameObject: gameObjects) {
            gameObject.update();
        }
    }

    public void updateGameObjectsList() {
       
    }
}
