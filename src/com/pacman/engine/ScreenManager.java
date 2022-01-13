package com.pacman.engine;

import com.pacman.graphicinterface.components.controllers.GameObjectController;
import com.pacman.graphicinterface.components.javafx.LifeBoardGraphic;
import com.pacman.graphicinterface.components.javafx.ScoreBoardGraphic;
import com.pacman.systemelements.SceneElement;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class ScreenManager {

    /**
     * Responsável pelo {@link Stage} principal da aplicação.
     */
    private Stage primaryStage;

    /**
     * Retorna o {@link Stage} principal da aplicação.
     * @return o {@link Stage} principal da aplicação.
     */
    public Stage primaryStage() {
        return primaryStage;
    }

    /**
     * Altera o {@link Stage} principal da aplicação.
     * @param primaryStage novo {@link Stage} principal.
     */
    public void setPrimaryStage(Stage primaryStage) throws NullPointerException {
        if (primaryStage == null) throw new NullPointerException("primaryStage must be not null.");
        this.primaryStage = primaryStage;
    }

    /**
     * Armazena a tela do jogo, o que inclui a arena, placares de pontos e outras informações.
     */
    private AnchorPane gameScreen;

    /**
     * Retorna a tela do jogo.
     * @return a tela do jogo.
     */
    public AnchorPane gameScreen() {
        return gameScreen;
    }

    /**
     * Altera a tela do jogo.
     * @param gameScreen nova tela do jogo.
     */
    public void setGameScreen(AnchorPane gameScreen) {
        this.gameScreen = gameScreen;
    }

    /**
     * Armazena a arena com todos os recursos inclusos (representações de Ghosts, PacDots, etc.)
     */
    private AnchorPane arena;

    /**
     * Retorna a arena do jogo.
     * @return a arena do jogo.
     */
    public AnchorPane arena() {
        return arena;
    }

    /**
     * Altera a arena do jogo.
     * @param arena nova arena.
     */
    public void setArena(AnchorPane arena) {
        this.arena = arena;
    }

    /**
     * Carrega o arquivo da arena a partir de um arquivo FXML.
     * @param path caminho do arquivo.
     */
    public void loadFileArena(String path) {

        FXMLLoader loader = new FXMLLoader(getClass().getResource(path));

        try {
            arena = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Armazena o placar que informa a atual pontuação do jogador.
     */
    private ScoreBoardGraphic gameScoreBoard;

    /**
     * Retorna o placar responsável pela atual pontuação do jogador.
     * @return o placar responsável pela atual pontuação do jogador.
     */
    public ScoreBoardGraphic gameScoreBoard() {
        return gameScoreBoard;
    }

    /**
     * Altera o placar responsável pela pontuação atual do jogador.
     * @param gameScoreBoard novo placar.
     */
    public void setGameScoreBoard(ScoreBoardGraphic gameScoreBoard) {
        this.gameScoreBoard = gameScoreBoard;
    }

    /**
     * Armazena o placar de vidas do jogador.
     */
    private LifeBoardGraphic lifeBoard;

    /**
     * Retorna o placar de vidas do jogador.
     * @return o placar de vidas do jogador.
     */
    public LifeBoardGraphic lifeBoard() {
        return lifeBoard;
    }

    /**
     * Altera o placar de vidas do jogador.
     * @param lifeBoard novo placar.
     */
    public void setLifeBoard(LifeBoardGraphic lifeBoard) {
        this.lifeBoard = lifeBoard;
    }

    public ScreenManager() {

        String path = "/com/pacman/graphicinterface/components/fxml/GameScreen.fxml";
        FXMLLoader loader = new FXMLLoader(getClass().getResource(path));

        try {
            gameScreen = loader.load();
            for(Node node: gameScreen.getChildren()) {
                switch (node.getId()) {
                    case "arena":
                        arena = (AnchorPane) node;
                        break;
                    case "gameScoreBoard":
                        gameScoreBoard = (ScoreBoardGraphic) node;
                        break;
                    case "lifeBoard":
                        lifeBoard = (LifeBoardGraphic) node;
                        break;
                    case "highScoreBoard":
                        break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void runAnimations() {

        Timeline timelineTranslations = new Timeline();
        timelineTranslations.getKeyFrames().add(new KeyFrame(Duration.millis(1)));
        timelineTranslations.play();

        Timeline timelineAnimations = new Timeline();
        timelineAnimations.getKeyFrames().add(new KeyFrame(Duration.millis(1)));
        timelineAnimations.play();

        timelineTranslations.setOnFinished(event -> {

            timelineTranslations.getKeyFrames().clear();

            GameSystem.levelManager.checkEffectEnergyPill();
            GameSystem.collisions.checkCollisions(GameSystem.gameobjects.dynamicControllers(),
                    GameSystem.gameobjects.staticControllers());
            GameSystem.collisions.handleCollisions();

            for (GameObjectController gameObjectController : GameSystem.gameobjects.gameObjectControllers()) {
                gameObjectController.update();
                KeyFrame translation = gameObjectController.getTranslationKeyFrame();
                if (translation != null) timelineTranslations.getKeyFrames().add(translation);
            }

            GameSystem.collisions.checkCollisions(GameSystem.gameobjects.dynamicControllers(),
                    GameSystem.gameobjects.staticControllers());
            GameSystem.collisions.handleCollisions();

            GameSystem.gameobjects.destroyGameObjects();

            if (!timelineTranslations.getKeyFrames().isEmpty()) timelineTranslations.play();
        });

        timelineAnimations.setOnFinished(event -> {

            timelineAnimations.getKeyFrames().clear();

            for (GameObjectController gameObjectController : GameSystem.gameobjects.gameObjectControllers()) {
                KeyFrame animation = gameObjectController.getAnimationKeyFrame();
                if (animation != null) timelineAnimations.getKeyFrames().add(animation);
            }

            if (!timelineAnimations.getKeyFrames().isEmpty()) timelineAnimations.play();
        });


    }

    public static double convertGameToScreenX(SceneElement sceneElement) {
        return sceneElement.getPosition().getX() - sceneElement.getDimension().getWidth() / 2.0;
    }

    public static double convertGameToScreenY(SceneElement sceneElement) {
        return sceneElement.getPosition().getY() - sceneElement.getDimension().getHeight() / 2.0;
    }

    public static double convertScreenToGameX(Node node) {
        return node.getLayoutX() + node.getLayoutBounds().getWidth() / 2.0;
    }

    public static double convertScreenToGameY(Node node) {
        return node.getLayoutY() + node.getLayoutBounds().getHeight() / 2.0;
    }

}
