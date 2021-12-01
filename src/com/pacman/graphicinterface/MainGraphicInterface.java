package com.pacman.graphicinterface;

import com.pacman.engine.CollisionManager;
import com.pacman.engine.GraphicManager;
import com.pacman.engine.SystemManager;
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
        SystemManager.start();
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {

        Pane root = FXMLLoader.load(getClass().getResource("fxml/Board.fxml"));

        GraphicManager.root = root;

        SystemManager.getArenaManager().loadArena("src/com/pacman/systemelements/Arena.txt");
        SystemManager.getGameObjectManager().updateGameObjectLists();

        PacMan pacMan = null;
        Ghost ghost = null;

        for(DynamicGameObject dynamicGameObject: SystemManager.getGameObjectManager().getDynamicGameObjects()) {
            if (dynamicGameObject instanceof PacMan) pacMan = (PacMan) dynamicGameObject;
            else if (dynamicGameObject instanceof Ghost) ghost = (Ghost) dynamicGameObject;
        }

        ghost.setTarget(pacMan);

//        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/Ghost.fxml"));
//        root.getChildren().add(loader.load());
//        GhostController ghostController = loader.getController();
//
//        SystemManager.getGameObjectManager().getGameObjects().add(ghostController.getGhost());
//        SystemManager.getGameObjectManager().getGameObjectController().put(ghostController.getGhost(), ghostController);
//
//        ghostController.ghostID.setTranslateX(100);
//        ghostController.ghostID.setTranslateY(100);
//        ghostController.getGhost().setPosition(new Position(400, 200));
//        ghostController.getGhost().setPosition(new Position(400, 200));
//        ghostController.getGhost().getHitBox().setDimension(new Dimension(100,100));
//        ghostController.getGhost().setRigidBody(false);
//
//        ghostController.getGhost().getVelocity().updateVelocity(50, Velocity.Direction.LEFT);
//
//        loader = new FXMLLoader(getClass().getResource("fxml/PacMan.fxml"));
//        root.getChildren().add(loader.load());
//        PacManController pacManController = loader.getController();
//
//        SystemManager.getGameObjectManager().getGameObjects().add(pacManController.getPacMan());
//        SystemManager.getGameObjectManager().getGameObjectController().put(pacManController.getPacMan(), pacManController);
//
//        pacManController.getPacMan().getVelocity().setModulus(100);
//        pacManController.getPacMan().getHitBox().setDimension(new Dimension(100,100));
//
//        SystemManager.getGameObjectManager().updateGameObjectLists();


        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setFullScreen(true);
        primaryStage.show();

        SystemManager.run();
    }
}
