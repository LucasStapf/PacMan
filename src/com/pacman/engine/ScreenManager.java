package com.pacman.engine;

import com.pacman.graphicinterface.GameObjectController;
import com.pacman.graphicinterface.InfoBoardController;
import com.pacman.systemelements.DynamicGameObject;
import com.pacman.systemelements.SceneElement;
import javafx.animation.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.Iterator;

public class ScreenManager {

    private Stage mainStage;

    /**
     * Container responsável pela organização visual do jogo durante uma partida.
     */
    private BorderPane gameScreenPane;

    /**
     * Container responsável pela arena(tabuleiro) do jogo.
     */
    private Pane boardPane;
    public InfoBoardController infoBoardController;

    public Stage getMainStage() {
        return mainStage;
    }

    public Pane getBoardPane() {
        return boardPane;
    }

    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
    }

    public void setBoardPane(Pane boardPane) {
        this.boardPane = boardPane;
    }

    public InfoBoardController getInfoBoardController() {
        return infoBoardController;
    }

    public void setInfoBoardController(InfoBoardController infoBoardController) {
        this.infoBoardController = infoBoardController;
    }

    public void startGameScreen() throws IOException {
        
        gameScreenPane = FXMLLoader.load(getClass().getResource("/com/pacman/graphicinterface/components/GameScreen.fxml"));
        boardPane = FXMLLoader.load(getClass().getResource("/com/pacman/graphicinterface/components/Board.fxml"));

        SystemGame.getArenaManager().loadArenaFrom("src/com/pacman/systemelements/Arena.txt");
        SystemGame.getGameObjectManager().updateGameObjectLists();

        gameScreenPane.setCenter(boardPane);

        mainStage.setScene(new Scene(gameScreenPane));
        mainStage.setFullScreen(true);
        mainStage.show();
    }


    public void runGameScreen() {
        updateGameScreen();
    }

    public void updateGameScreen() {

        final Timeline timeline = new Timeline();

        timeline.setCycleCount(1);
        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(1)));
        timeline.play();

        timeline.setOnFinished(e -> {

            if (!SystemGame.getStatus().equals(SystemGame.Status.RUNNING)) return;


//            infoBoardController.updateInfo();

            timeline.getKeyFrames().clear();

            SystemGame.getGameObjectManager().destroyGameObjects();

            SystemGame.getGameObjectManager().updateAllGameObjects();

            SystemGame.getCollisionManager().checkCollisions(SystemGame.getGameObjectManager().getDynamicGameObjects(),
                    SystemGame.getGameObjectManager().getStaticGameObjects());
            SystemGame.getCollisionManager().handleCollisions();


            Iterator<DynamicGameObject> i = SystemGame.getGameObjectManager().getDynamicGameObjects().iterator();

            while (i.hasNext()) {

                DynamicGameObject dynamicGameObject = i.next();
                GameObjectController gameObjectController = SystemGame.getGameObjectManager().getGameObjectController().get(dynamicGameObject);

                timeline.getKeyFrames().add(gameObjectController.getKeyFrame());
            }


            timeline.play();

        });
    }

    public static double convertGameToScreenX(SceneElement sceneElement) {
        return sceneElement.getPosition().getX() - sceneElement.getDimension().getWidth() / 2.0;
    }

    public static double convertGameToScreenY(SceneElement sceneElement) {
        return sceneElement.getPosition().getY() - sceneElement.getDimension().getHeight() / 2.0;
    }
}
