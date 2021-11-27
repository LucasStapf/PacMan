package com.pacman.engine;

import com.pacman.graphicinterface.Print;
import com.pacman.systemelements.Arena;
import com.pacman.systemelements.GameObject;

public class GameManager {

    private static ArenaManager arenaManager;
    private static GameObjectManager gameObjectManager;
    private static CollisionManager collisionManager;

    private boolean running = true;

    public GameManager() {
        start();
    }

    public static ArenaManager getArenaManager() {
        return arenaManager;
    }

    public static GameObjectManager getGameObjectManager() {
        return gameObjectManager;
    }

    public static CollisionManager getCollisionManager() {
        return collisionManager;
    }

    public static void createGameObject(GameObject gameObject) {

        if (arenaManager == null || gameObjectManager == null || collisionManager == null) return;

    }

    public static void destroyGameObject(GameObject gameObject) {

        if (arenaManager == null || gameObjectManager == null || collisionManager == null) return;


    }

    public static void start() {

        arenaManager = new ArenaManager(new Arena("src/com/pacman/systemelements/Arena.txt"));
        gameObjectManager = new GameObjectManager(arenaManager.getArena().getGameObjects());
        collisionManager = new CollisionManager(gameObjectManager.getGameObjects());

        Print.printArena(arenaManager.getArena());
    }

    public static void run() {

    }

    public static void pause() {

    }

    public static void stop() {

    }

}
