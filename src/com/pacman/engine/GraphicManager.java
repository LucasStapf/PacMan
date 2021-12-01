package com.pacman.engine;

import com.pacman.graphicinterface.GameObjectController;
import com.pacman.systemelements.DynamicGameObject;
import com.pacman.systemelements.SceneElement;
import javafx.animation.*;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.util.Iterator;

public class GraphicManager {

    public static Pane root;
    private static double deltaTime = 50;

    public static double getDeltaTime() {
        return deltaTime;
    }

    public static void setDeltaTime(double deltaTime) {
        GraphicManager.deltaTime = deltaTime;
    }


    public static void updateScreen() {

        final Timeline timeline = new Timeline();

        timeline.setCycleCount(1);
        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(1)));
        timeline.play();

        timeline.setOnFinished(e -> {

            timeline.getKeyFrames().clear();

            SystemManager.getGameObjectManager().updateAllGameObjects();

            Iterator<DynamicGameObject> i = SystemManager.getGameObjectManager().getDynamicGameObjects().iterator();
            SystemManager.getCollisionManager().checkCollisions(SystemManager.getGameObjectManager().getDynamicGameObjects(),
                    SystemManager.getGameObjectManager().getStaticGameObjects());
            SystemManager.getCollisionManager().handleCollisions();

            while (i.hasNext()) {

                DynamicGameObject dynamicGameObject = i.next();
                GameObjectController gameObjectController = SystemManager.getGameObjectManager().getGameObjectController().get(dynamicGameObject);

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
