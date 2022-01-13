package com.pacman.engine;

import com.pacman.systemelements.GameObject;

/**
 * Classe que gerencia toda a lógica do jogo.
 */
public class GameSystem {

    /**
     * Constantes que armazenam o atual estado do jogo.
     */
    public enum Status {
        STARTED,
        RUNNING,
        PAUSED,
        ENDED
    }

    /**
     * Atributo que guarda o atual estado do jogo.
     */
    private static Status status;

    /**
     * Intervalo de tempo entre cada atualização do sistema do jogo.
     * É utilizado, por exemplo, para o cálculo de distâncias percorridas e para o processamento gráfico.
     */
    public static final long deltaTime = 20;

    /**
     * Atributo que armazena o gerenciador da arena.
     */
    public static ArenaManager arenaManager;

    /**
     * Atributo que armazena o gerenciador de colisões.
     */
    public static CollisionManager collisionManager;

    /**
     * Atributo que armazena o gerenciador dos objetos do jogo.
     */
    public static GameObjectManager gameObjectManager;

    /**
     * Gerenciador da interface gráfica do jogo.
     */
    public static ScreenManager screen;

    /**
     * Gerenciador do sistema de pontuação do jogo.
     */
    public static ScoreManager scoreManager;

    /**
     * Gerenciador do sistema de nível do jogo.
     */
    public static LevelManager levelManager;

    /**
     * Construtor padrão.
     */
    public GameSystem() {
        start();
    }

    public static Status getStatus() {
        return status;
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
        screen = new ScreenManager();
        collisionManager = new CollisionManager();
        arenaManager = new ArenaManager();
        scoreManager = new ScoreManager();
        levelManager = new LevelManager();

        status = Status.STARTED;
    }

    /**
     * Método responsável por rodar o jogo.
     */
    public static void run() {
        status = Status.RUNNING;
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
