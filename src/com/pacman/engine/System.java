package com.pacman.engine;

import com.pacman.systemelements.GameObject;

/**
 * Classe que gerencia toda a lógica do jogo.
 */
public class System {

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
     * Intervalo de tempo entre cada atualização do sistema do jogo.
     * É utilizado, por exemplo, para o cálculo de distâncias percorridas e para o processamento gráfico.
     */
    public static final long deltaTime = 100;

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

    private static GraphicManager graphicManager;

    /**
     * Construtor padrão.
     */
    public System() {
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

    public static GraphicManager getGraphicManager() {
        return graphicManager;
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
        gameObjectManager.getObjectsToDestroy().add(gameObject);
    }

    /**
     * Método responsável por inicializar o jogo.
     */
    public static void start() {

        gameObjectManager = new GameObjectManager();
        arenaManager = new ArenaManager();
        collisionManager = new CollisionManager();
        graphicManager = new GraphicManager();
    }

    /**
     * Método responsável por rodar o jogo.
     */
    public static void run() {
//        GraphicManager.updateFrame();
        GraphicManager.updateScreen();
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