package com.pacman.graphicinterface;

import com.pacman.graphicinterface.components.controllers.GhostController;
import com.pacman.graphicinterface.components.controllers.PacManController;
import com.pacman.graphicinterface.components.javafx.GhostGraphic;
import com.pacman.graphicinterface.components.javafx.PacManGraphic;
import com.pacman.systemelements.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class MainGraphicInterface extends Application {

    public static void main(String[] args) {
//        GameSystem.start();
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {

        Ghost ghost = new Ghost(new Position(0,0));
        GhostGraphic ghostGraphic = new GhostGraphic();

        ghost.getVelocity().updateVelocity(20, Direction.RIGHT);
        ghostGraphic.setHeightValue(18);
        ghostGraphic.setWidthValue(16);
        ghostGraphic.setLayoutX(300);
        ghostGraphic.setLayoutY(300);
        GhostController ghostController = new GhostController();
        ghostController.setGameObject(ghost);
        ghostController.setSceneElementGraphic(ghostGraphic);


        Pane pane = new Pane();
        pane.setPrefSize(600, 600);
        pane.getChildren().add(ghostGraphic);


        primaryStage.setScene(new Scene(pane));
        primaryStage.show();

        KeyFrame keyFrame = new KeyFrame(Duration.millis(1));
        Timeline timeline = new Timeline(keyFrame);
        timeline.play();

        timeline.setOnFinished(e -> {
            timeline.getKeyFrames().clear();
            ghostController.update();
            timeline.getKeyFrames().add(ghostController.getTranslationKeyFrame());
//            timeline.getKeyFrames().add(ghostController.getAnimationKeyFrame());
            timeline.play();
        });

//        GameSystem.getScreenManager().setMainStage(primaryStage);
//        GameSystem.getScreenManager().startGameScreen();
//
//        BorderPane gameScreen = FXMLLoader.load(getClass().getResource("components/GameScreen.fxml"));
//
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("components/InfoBoard.fxml"));
//        Pane infoBoard = loader.load();
//        InfoBoardController infoBoardController = loader.getController();
//        GameSystem.getScreenManager().setInfoBoardController(infoBoardController);
//
//        Pane root = FXMLLoader.load(getClass().getResource("components/Board.fxml"));
//
//        GameSystem.getScreenManager().setBoardPane(root);
//
//        GameSystem.getArenaManager().loadArenaFrom("src/com/pacman/systemelements/Arena.txt");
//        GameSystem.getGameObjectManager().updateGameObjectLists();
//
////        for(GameObject gameObject: GameSystem.getGameObjectManager().getGameObjectController().keySet()) {
////            GameSystem.out.println(GameSystem.getGameObjectManager().getGameObjectController().get(gameObject));
////        }
//
//        PacMan pacMan = null;
//        Ghost ghost = null;
//
//        for(DynamicGameObject dynamicGameObject: GameSystem.getGameObjectManager().getDynamicGameObjects()) {
//            if (dynamicGameObject instanceof PacMan) pacMan = (PacMan) dynamicGameObject;
//            else if (dynamicGameObject instanceof Ghost) {
//                ghost = (Ghost) dynamicGameObject;
//                ghost.setMovement(Ghost.Movement.FOLLOW_TARGET);
//                ghost.setTarget(pacMan);
//                ghost.getVelocity().setModulus(0);
//            }
//        }
//
//        infoBoardController.setPacMan(pacMan);
//        gameScreen.setTop(root);
//        gameScreen.setBottom(infoBoard);
//
//        Scene scene = new Scene(gameScreen);
//        primaryStage.setScene(scene);
//        primaryStage.setFullScreen(true);
//        primaryStage.show();
//
//        GameSystem.run();
    }
}
