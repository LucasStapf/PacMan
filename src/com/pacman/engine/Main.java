package com.pacman.engine;

import com.pacman.systemelements.Direction;
import com.pacman.systemelements.Ghost;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    public static void main(String[] args) {
        SystemGame.start();
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {

        SystemGame.screenManager.setPrimaryStage(primaryStage);
        primaryStage.setScene(new Scene(SystemGame.screenManager.gameScreen()));
        primaryStage.show();

        SystemGame.gameObjectManager.updateGameObjectControllers();

        SystemGame.screenManager.arena().addEventFilter(KeyEvent.KEY_PRESSED, SystemGame.gameObjectManager.player().keyEventHandler());

        SystemGame.gameObjectManager.blinky().setTarget(SystemGame.gameObjectManager.player().getGameObject());
        SystemGame.gameObjectManager.pinky().setTarget(SystemGame.gameObjectManager.player().getGameObject());
        SystemGame.gameObjectManager.inky().setMovement(Ghost.Movement.RANDOM);
        SystemGame.gameObjectManager.inky().getVelocity().updateVelocity(50, Direction.DOWN);
        SystemGame.gameObjectManager.clyde().setMovement(Ghost.Movement.RANDOM);
        SystemGame.gameObjectManager.clyde().getVelocity().updateVelocity(50, Direction.DOWN);

        SystemGame.gameObjectManager.player().requestFocus();

        SystemGame.screenManager.runAnimations();
    }
}
