package com.pacman.engine;

import com.pacman.graphicinterface.Print;
import com.pacman.systemelements.GameObject;
import com.pacman.systemelements.Ghost;
import com.pacman.systemelements.PacMan;
import com.pacman.systemelements.Velocity;

import java.util.Iterator;

/**
 * Classe que gerencia toda a lógica do jogo.
 */
public class GameManager {

    /**
     * Constantes que armazenam o atual estado do jogo.
     */
    public enum Status {
        STARTED,
        RUNNING,
        PAUSED,
        ENDED;
    }

    /**
     * Atributo que guarda o atual estado do jogo.
     */
    private static Status status;

    /**
     * Atributo que armazena o gerenciador da arena.
     */
    private static ArenaManager arenaManager;

    /**
     * Atributo que armazena o gerenciador dos objectos da arena.
     */
    private static GameObjectManager gameObjectManager;

    /**
     * Atributo que armazena o gerenciador de colisões.
     */
    private static CollisionManager collisionManager;

    /**
     * Construtor padrão.
     */
    public GameManager() {
        start();
    }

    /**
     * Método que retorna o gerenciador da arena do jogo.
     * @return arenaManager
     */
    public static ArenaManager getArenaManager() {
        return arenaManager;
    }

    /**
     * Método que retorna o gerenciador dos objetos do jogo.
     * @return gameObjectManager
     */
    public static GameObjectManager getGameObjectManager() {
        return gameObjectManager;
    }

    /**
     * Método que retorna o gerenciador de colisões do jogo.
     * @return collisionManager
     */
    public static CollisionManager getCollisionManager() {
        return collisionManager;
    }

    /**
     * Método utilizado para adicionar um GameObject dinâmicamente (runtime).
     * @param gameObject objeto a ser adicionado.
     */
    public static void createGameObject(GameObject gameObject) {

        if (arenaManager == null || gameObjectManager == null || collisionManager == null) return;

    }

    /**
     * Método utilizado para remover um GameObject do jogo dinâmicamente (runtime).
     * @param gameObject objeto a ser removido.
     */
    public static void destroyGameObject(GameObject gameObject) {

        if (arenaManager == null || gameObjectManager == null || collisionManager == null) return;

    }

    /**
     * Método responsável por inicializar o jogo.
     */
    public static void start() {

        arenaManager = new ArenaManager();
        gameObjectManager = new GameObjectManager();
        collisionManager = new CollisionManager();

        arenaManager.loadArena("src/com/pacman/systemelements/Arena.txt");
        gameObjectManager.updateGameObjectLists();

        PacMan pacMan = null;
        Ghost ghost = null;

        Iterator<GameObject> i = gameObjectManager.getGameObjects().iterator();
        while (i.hasNext() && (pacMan == null || ghost == null)) {
            GameObject gameObject = i.next();
            if (gameObject instanceof PacMan) pacMan = (PacMan) gameObject;
            else if (gameObject instanceof Ghost) ghost = (Ghost) gameObject;
        }

        pacMan.getVelocity().setDirection(Velocity.Direction.RIGHT);
        pacMan.getVelocity().setModulus(1);

        ghost.getVelocity().setModulus(1);
        ghost.setTarget(pacMan);
        ghost.setMovement(Ghost.Movement.FOLLOW_TARGET);

        Print.printArena(arenaManager.getArena());
    }

    /**
     * Método responsável por rodar o jogo.
     */
    public static void run() {

        status = Status.RUNNING;

        while (status == Status.RUNNING) {

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            gameObjectManager.updateAllGameObjects();
            arenaManager.getArena().updateArena();
            Print.printArena(arenaManager.getArena());
            collisionManager.handleCollisions();
        }
    }

    /**
     * Método responsável por pausar o jogo.
     */
    public static void pause() {

    }

    /**
     * Método responsável por encerrar o jogo.
     */
    public static void end() {
        status = Status.ENDED;
    }

}
