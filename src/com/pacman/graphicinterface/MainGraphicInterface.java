package com.pacman.graphicinterface;

import com.pacman.engine.CollisionManager;
import com.pacman.engine.GraphicManager;
import com.pacman.engine.System;
import com.pacman.systemelements.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.LinkedList;

public class MainGraphicInterface extends Application {

    public static CollisionManager collisionManager = new CollisionManager();
    public static LinkedList<DynamicGameObject> dynamicGameObjects = new LinkedList<>();

    public static void main(String[] args) {
        System.start();
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {

        Pane root = FXMLLoader.load(getClass().getResource("fxml/Board.fxml"));

        System.getGraphicManager().setBoardPane(root);

        System.getArenaManager().loadArenaFrom("src/com/pacman/systemelements/Arena.txt");
        System.getGameObjectManager().updateGameObjectLists();

//        for(GameObject gameObject: System.getGameObjectManager().getGameObjectController().keySet()) {
//            System.out.println(System.getGameObjectManager().getGameObjectController().get(gameObject));
//        }

        PacMan pacMan = null;
        Ghost ghost = null;

        for(DynamicGameObject dynamicGameObject: System.getGameObjectManager().getDynamicGameObjects()) {
            if (dynamicGameObject instanceof PacMan) pacMan = (PacMan) dynamicGameObject;
            else if (dynamicGameObject instanceof Ghost) ghost = (Ghost) dynamicGameObject;
        }

        ghost.setMovement(Ghost.Movement.FOLLOW_TARGET);
        ghost.setTarget(pacMan);


        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setFullScreen(true);
        primaryStage.show();

        System.run();
    }
}
