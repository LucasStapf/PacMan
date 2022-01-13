package com.pacman.engine;

import com.pacman.graphicinterface.components.controllers.GameObjectController;
import com.pacman.graphicinterface.components.controllers.ScoreBoardController;
import com.pacman.systemelements.SceneElement;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
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
    private ScoreBoardController gameScoreBoard;

    /**
     * Retorna o placar responsável pela atual pontuação do jogador.
     * @return o placar responsável pela atual pontuação do jogador.
     */
    public ScoreBoardController gameScoreBoard() {
        return gameScoreBoard;
    }

    /**
     * Altera o placar responsável pela pontuação atual do jogador.
     * @param gameScoreBoard novo placar.
     */
    public void setGameScoreBoard(ScoreBoardController gameScoreBoard) {
        this.gameScoreBoard = gameScoreBoard;
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
                        gameScoreBoard = new ScoreBoardController();
                        Pane pane = (Pane) node;
                        for (Node nodeAux: pane.getChildren()) {
                            if (nodeAux.getId().equals("scoreBoardText"))
                                gameScoreBoard.setScoreBoardText((Label) nodeAux);
                            else if (nodeAux.getId().equals("scoreBoardValue"))
                                gameScoreBoard.setScoreBoardValue((Label) nodeAux);
                        }
                        gameScoreBoard.scoreBoardText().setText("GAME SCORE");
                        gameScoreBoard.scoreBoardValue().setText("0");
                    case "highScoreBoard":
                        break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

//        String path = "/com/pacman/graphicinterface/components/fxml/Arena.fxml";
//        loadFileArena(path);
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
