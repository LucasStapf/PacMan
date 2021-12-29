package com.pacman.engine;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    public static void main(String[] args) {
        SystemGame.start();
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        SystemGame.getScreenManager().setMainStage(primaryStage);
        SystemGame.getScreenManager().startGameScreen();
        SystemGame.run();
    }
}
