package com.pacman.graphicinterface.components.controllers;

import com.pacman.engine.ScreenManager;
import com.pacman.engine.SystemGame;
import com.pacman.graphicinterface.GameObjectController;
import com.pacman.graphicinterface.components.javafx.GhostGraphic;
import com.pacman.graphicinterface.components.javafx.SceneElementGraphic;
import com.pacman.systemelements.GameObject;
import com.pacman.systemelements.Ghost;
import com.pacman.systemelements.PacMan;
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

        KeyValue keyValueX = new KeyValue(ghostGraphic.translateXProperty(),
                ScreenManager.convertGameToScreenX(ghost));
        KeyValue keyValueY = new KeyValue(ghostGraphic.translateYProperty(),
                ScreenManager.convertGameToScreenY(ghost));

        return new KeyFrame(Duration.millis(SystemGame.deltaTime), keyValueX, keyValueY);
    }

    @Override
    public void destroy() {

    }
}
