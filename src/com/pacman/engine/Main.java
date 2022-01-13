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
        GameSystem.start();
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {

        GameSystem.screenManager.setPrimaryStage(primaryStage);
        primaryStage.setScene(new Scene(GameSystem.screenManager.gameScreen()));
        primaryStage.show();

        GameSystem.gameObjectManager.updateGameObjectControllers();

        GameSystem.screenManager.arena().addEventFilter(KeyEvent.KEY_PRESSED, GameSystem.gameObjectManager.player().keyEventHandler());

        GameSystem.gameObjectManager.blinky().setTarget(GameSystem.gameObjectManager.player().getGameObject());
        GameSystem.gameObjectManager.pinky().setTarget(GameSystem.gameObjectManager.player().getGameObject());
        GameSystem.gameObjectManager.inky().setMovement(Ghost.Movement.RANDOM);
        GameSystem.gameObjectManager.inky().getVelocity().updateVelocity(50, Direction.DOWN);
        GameSystem.gameObjectManager.clyde().setMovement(Ghost.Movement.RANDOM);
        GameSystem.gameObjectManager.clyde().getVelocity().updateVelocity(50, Direction.DOWN);

        GameSystem.gameObjectManager.player().requestFocus();

        GameSystem.screenManager.runAnimations();
    }
}
