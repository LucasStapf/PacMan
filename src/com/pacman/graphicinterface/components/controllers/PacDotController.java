package com.pacman.graphicinterface.components.controllers;

import com.pacman.engine.SystemGame;
import com.pacman.graphicinterface.components.controllers.GameObjectController;
import com.pacman.graphicinterface.components.javafx.PacDotGraphic;
import com.pacman.graphicinterface.components.javafx.SceneElementGraphic;
import com.pacman.systemelements.GameObject;
import com.pacman.systemelements.PacDot;
import com.pacman.systemelements.Position;
import javafx.animation.KeyFrame;
import javafx.fxml.FXML;
import javafx.scene.layout.Region;

public class PacDotController implements GameObjectController {

    private PacDot pacDot;

    @Override
    public GameObject getGameObject() {
        return pacDot;
    }

    @Override
    public void setGameObject(GameObject gameObject) throws IllegalArgumentException {
        if (!(gameObject instanceof PacDot)) throw new IllegalArgumentException();
        pacDot = (PacDot) gameObject;
    }

    private PacDotGraphic pacDotGraphic;

    @Override
    public SceneElementGraphic getSceneElementGraphic() {
        return pacDotGraphic;
    }

    @Override
    public void setSceneElementGraphic(SceneElementGraphic sceneElementGraphic) throws IllegalArgumentException {
        if (!(sceneElementGraphic instanceof PacDotGraphic)) {
            throw new IllegalArgumentException("The SceneElementGraphic must be a PacDotGraphic object.");
        } else pacDotGraphic = (PacDotGraphic) sceneElementGraphic;
    }

    @Override
    public KeyFrame getTranslationKeyFrame() {
        return null;
    }

    @Override
    public KeyFrame getAnimationKeyFrame() {
        return null;
    }

    @Override
    public void update() {
        pacDot.update();
    }

    @Override
    public void destroy() {
//        SystemGame.getScreenManager().getBoardPane().getChildren().remove(pacDotID);
        SystemGame.gameObjectManager.gameObjectControllers().remove(this);
        SystemGame.gameObjectManager.staticControllers().remove(this);
        SystemGame.screenManager.arena().getChildren().remove(pacDotGraphic);
    }
}
