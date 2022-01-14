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

    private final String defaultPathArena = "/com/pacman/graphicinterface/components/fxml/Arena.fxml";
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
     * Carrega o arquivo padrão da arena.
     */
    public void loadFileArena() {

        FXMLLoader loader = new FXMLLoader(getClass().getResource(defaultPathArena));

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
     * Armazena o placar que informa a atual pontuação máxima do jogador.
     */
    private ScoreBoardGraphic highScoreBoard;

    /**
     * Retorna o placar responsável pela atual máxima pontuação do jogodor.
     * @return o placar responsável pela atual máxima pontuação do jogodor.
     */
    public ScoreBoardGraphic highScoreBoard() {
        return highScoreBoard;
    }

    /**
     * Altera o placar responsável pela máxima pontuação atual do jogador.
     * @param highScoreBoard novo placar.
     */
    public void setHighScoreBoard(ScoreBoardGraphic highScoreBoard) {
        this.highScoreBoard = highScoreBoard;
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
                        highScoreBoard = (ScoreBoardGraphic) node;
                        break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public final Timeline timelineTranslations = new Timeline();
    public final Timeline timelineAnimations = new Timeline();

    /**
     * Realiza as animações e translações dos GameObjects da arena.
     */
    public void runAnimations() {

        timelineTranslations.getKeyFrames().add(new KeyFrame(Duration.millis(1)));
        timelineTranslations.play();

        timelineAnimations.getKeyFrames().add(new KeyFrame(Duration.millis(1)));
        timelineAnimations.play();

        timelineTranslations.setOnFinished(event -> {

            timelineTranslations.getKeyFrames().clear();

            GameSystem.levelManager.check();
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

    /**
     * Converte a abscissa de um SceneElement do jogo para a sua respectiva abscissa na tela.
     * @param sceneElement elemento alvo.
     * @return a abscissa do elemento na tela.
     */
    public static double convertGameToScreenX(SceneElement sceneElement) {
        return sceneElement.getPosition().getX() - sceneElement.getDimension().getWidth() / 2.0;
    }

    /**
     * Converte a ordenada de um SceneElement do jogo para a sua respectiva ordenada na tela.
     * @param sceneElement elemento alvo.
     * @return a ordenada do elemento na tela.
     */
    public static double convertGameToScreenY(SceneElement sceneElement) {
        return sceneElement.getPosition().getY() - sceneElement.getDimension().getHeight() / 2.0;
    }

    /**
     * Converte a abscissa de um Node na tela para a sua respectiva abscissa no jogo.
     * @param node nodo alvo.
     * @return a abscissa do elemento no jogo.
     */
    public static double convertScreenToGameX(Node node) {
        return node.getLayoutX() + node.getLayoutBounds().getWidth() / 2.0;
    }

    /**
     * Converte a ordenada de um Node na tela para a sua respectiva ordenada no jogo.
     * @param node nodo alvo.
     * @return a ordenada do elemento no jogo.
     */
    public static double convertScreenToGameY(Node node) {
        return node.getLayoutY() + node.getLayoutBounds().getHeight() / 2.0;
    }

}
