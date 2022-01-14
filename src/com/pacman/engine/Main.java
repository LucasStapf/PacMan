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

        GameSystem.primaryStage = primaryStage;
        primaryStage.setScene(new Scene(GameSystem.screen.gameScreen()));
        primaryStage.show();

        GameSystem.run();
    }
}
