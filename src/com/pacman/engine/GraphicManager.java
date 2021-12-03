package com.pacman.engine;

import com.pacman.graphicinterface.GameObjectController;
import com.pacman.systemelements.DynamicGameObject;
import com.pacman.systemelements.SceneElement;
import javafx.animation.*;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.util.Iterator;

public class GraphicManager {

    public Pane boardPane;

    public Pane getBoardPane() {
        return boardPane;
    }

    public void setBoardPane(Pane boardPane) {
        this.boardPane = boardPane;
    }

    public static void updateScreen() {

        final Timeline timeline = new Timeline();

        timeline.setCycleCount(1);
        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(1)));
        timeline.play();

        timeline.setOnFinished(e -> {

            timeline.getKeyFrames().clear();

            System.getGameObjectManager().destroyGameObjects();

            System.getGameObjectManager().updateAllGameObjects();

            System.getCollisionManager().checkCollisions(System.getGameObjectManager().getDynamicGameObjects(),
                    System.getGameObjectManager().getStaticGameObjects());
            System.getCollisionManager().handleCollisions();


            Iterator<DynamicGameObject> i = System.getGameObjectManager().getDynamicGameObjects().iterator();

            while (i.hasNext()) {

                DynamicGameObject dynamicGameObject = i.next();
                GameObjectController gameObjectController = System.getGameObjectManager().getGameObjectController().get(dynamicGameObject);

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
