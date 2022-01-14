package com.pacman.engine;

import com.pacman.systemelements.GameObject;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

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
     * Responsável pelo {@link Stage} principal da aplicação.
     */
    public static Stage primaryStage;

    /**
     * Intervalo de tempo entre cada atualização do sistema do jogo.
     * É utilizado, por exemplo, para o cálculo de distâncias percorridas e para o processamento gráfico.
     */
    public static final long deltaTime = 10;

    /**
     * Atributo que armazena o gerenciador da arena.
     */
    public static ArenaManager arenaManager;

    /**
     * Atributo que armazena o gerenciador de colisões.
     */
    public static CollisionManager collisions;

    /**
     * Atributo que armazena o gerenciador dos objetos do jogo.
     */
    public static GameObjectManager gameobjects;

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
     * Gerenciador do jogador.
     */
    public static PlayerManager player;

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

        if (arenaManager == null || gameobjects == null || collisions == null) return;
        gameobjects.getObjectsToDestroy().add(gameObject);
    }

    /**
     * Método responsável por inicializar o jogo.
     */
    public static void start() {

        gameobjects = new GameObjectManager();
        screen = new ScreenManager();
        collisions = new CollisionManager();
        arenaManager = new ArenaManager();
        scoreManager = new ScoreManager();
        levelManager = new LevelManager();
        player = new PlayerManager();

        status = Status.STARTED;
    }

    /**
     * Método responsável por rodar o jogo.
     */
    public static void run() {

        primaryStage.setScene(new Scene(GameSystem.screen.gameScreen()));
        primaryStage.show();

        gameobjects.updateGameObjectControllers();
        screen.arena().addEventFilter(KeyEvent.KEY_PRESSED, gameobjects.player().keyEventHandler());
        screen.runAnimations();

        status = Status.RUNNING;
    }

    /**
     * Método responsável por pausar o jogo.
     */
    public static void pause() {

    }

    public static void restart() {

        screen.timelineTranslations.stop();
        screen.timelineAnimations.stop();

        screen.timelineTranslations.setOnFinished(event -> {

            gameobjects = new GameObjectManager();
            screen = new ScreenManager();
            collisions = new CollisionManager();
            arenaManager = new ArenaManager();
            scoreManager = new ScoreManager();
            levelManager = new LevelManager();
            player.restart();

            status = Status.STARTED;

            run();
        });
    }

    /**
     * Método responsável por encerrar o jogo.
     */
    public static void end() {
        status = Status.ENDED;
    }

}
