package com.pacman.engine;

import javafx.application.Application;
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
        GameSystem.run();
    }
}
