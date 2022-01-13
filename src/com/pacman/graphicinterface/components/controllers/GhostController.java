package com.pacman.graphicinterface.components.controllers;

import com.pacman.engine.ScreenManager;
import com.pacman.engine.GameSystem;
import com.pacman.graphicinterface.components.javafx.GhostGraphic;
import com.pacman.graphicinterface.components.javafx.SceneElementGraphic;
import com.pacman.systemelements.GameObject;
import com.pacman.systemelements.Ghost;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.util.Duration;

public class GhostController implements GameObjectController {

    /**
     * Atributo responsável por guardar o objeto {@link Ghost}.
     */
    private Ghost ghost;

    @Override
    public GameObject getGameObject() {
        return ghost;
    }

    @Override
    public void setGameObject(GameObject gameObject) throws IllegalArgumentException {
        if (!(gameObject instanceof Ghost)) throw new IllegalArgumentException("The GameObject must be a Ghost object.");
        else ghost = (Ghost) gameObject;
    }

    /**
     * Atributo responsável por guardar a representação gráfica do {@link Ghost}.
     */
    private GhostGraphic ghostGraphic;

    @Override
    public SceneElementGraphic getSceneElementGraphic() {
        return ghostGraphic;
    }

    @Override
    public void setSceneElementGraphic(SceneElementGraphic sceneElementGraphic) throws IllegalArgumentException {
        if (!(sceneElementGraphic instanceof GhostGraphic)) {
            throw new IllegalArgumentException("The SceneElementGraphic must be a GhostGraphic object.");
        } else ghostGraphic = (GhostGraphic) sceneElementGraphic;
    }

    @Override
    public KeyFrame getTranslationKeyFrame() {

        KeyValue keyValueX = new KeyValue(ghostGraphic.layoutXProperty(),
                ScreenManager.convertGameToScreenX(ghost));
        KeyValue keyValueY = new KeyValue(ghostGraphic.layoutYProperty(),
                ScreenManager.convertGameToScreenY(ghost));

        return new KeyFrame(Duration.millis(GameSystem.deltaTime), keyValueX, keyValueY);
    }

    @Override
    public KeyFrame getAnimationKeyFrame() {
        return null;
    }

    @Override
    public void update() {
        ghost.update();
        ghostGraphic.setDirection(ghost.getVelocity().getDirection());
    }

    @Override
    public void destroy() {

    }
}
