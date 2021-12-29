package com.pacman.graphicinterface;

import com.pacman.engine.CollisionManager;
import com.pacman.engine.SystemGame;
import com.pacman.systemelements.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.LinkedList;

public class MainGraphicInterface extends Application {

    public static CollisionManager collisionManager = new CollisionManager();
    public static LinkedList<DynamicGameObject> dynamicGameObjects = new LinkedList<>();

    public static void main(String[] args) {
        SystemGame.start();
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {

        SystemGame.getScreenManager().setMainStage(primaryStage);
        SystemGame.getScreenManager().startGameScreen();

        BorderPane gameScreen = FXMLLoader.load(getClass().getResource("components/GameScreen.fxml"));

        FXMLLoader loader = new FXMLLoader(getClass().getResource("components/InfoBoard.fxml"));
        Pane infoBoard = loader.load();
        InfoBoardController infoBoardController = loader.getController();
        SystemGame.getScreenManager().setInfoBoardController(infoBoardController);

        Pane root = FXMLLoader.load(getClass().getResource("components/Board.fxml"));

        SystemGame.getScreenManager().setBoardPane(root);

        SystemGame.getArenaManager().loadArenaFrom("src/com/pacman/systemelements/Arena.txt");
        SystemGame.getGameObjectManager().updateGameObjectLists();

//        for(GameObject gameObject: SystemGame.getGameObjectManager().getGameObjectController().keySet()) {
//            SystemGame.out.println(SystemGame.getGameObjectManager().getGameObjectController().get(gameObject));
//        }

        PacMan pacMan = null;
        Ghost ghost = null;

        for(DynamicGameObject dynamicGameObject: SystemGame.getGameObjectManager().getDynamicGameObjects()) {
            if (dynamicGameObject instanceof PacMan) pacMan = (PacMan) dynamicGameObject;
            else if (dynamicGameObject instanceof Ghost) {
                ghost = (Ghost) dynamicGameObject;
                ghost.setMovement(Ghost.Movement.FOLLOW_TARGET);
                ghost.setTarget(pacMan);
                ghost.getVelocity().setModulus(0);
            }
        }

        infoBoardController.setPacMan(pacMan);
        gameScreen.setTop(root);
        gameScreen.setBottom(infoBoard);

        Scene scene = new Scene(gameScreen);
        primaryStage.setScene(scene);
        primaryStage.setFullScreen(true);
        primaryStage.show();

        SystemGame.run();
    }
}
