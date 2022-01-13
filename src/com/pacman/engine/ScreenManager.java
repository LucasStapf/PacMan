package com.pacman.engine;

import com.pacman.graphicinterface.components.controllers.GameObjectController;
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

    public void loadFileArena(String path) {

        FXMLLoader loader = new FXMLLoader(getClass().getResource(path));

        try {
            arena = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ScreenManager() {
        String path = "/com/pacman/graphicinterface/components/fxml/Arena.fxml";
        loadFileArena(path);
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

            SystemGame.collisionManager.checkCollisions(SystemGame.gameObjectManager.dynamicControllers(),
                    SystemGame.gameObjectManager.staticControllers());
            SystemGame.collisionManager.handleCollisions();

            for (GameObjectController gameObjectController : SystemGame.gameObjectManager.gameObjectControllers()) {
                gameObjectController.update();
                KeyFrame translation = gameObjectController.getTranslationKeyFrame();
                if (translation != null) timelineTranslations.getKeyFrames().add(translation);
            }

            SystemGame.collisionManager.checkCollisions(SystemGame.gameObjectManager.dynamicControllers(),
                    SystemGame.gameObjectManager.staticControllers());
            SystemGame.collisionManager.handleCollisions();

            SystemGame.gameObjectManager.destroyGameObjects();

            if (!timelineTranslations.getKeyFrames().isEmpty()) timelineTranslations.play();
        });

        timelineAnimations.setOnFinished(event -> {

            timelineAnimations.getKeyFrames().clear();

            for (GameObjectController gameObjectController : SystemGame.gameObjectManager.gameObjectControllers()) {
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
