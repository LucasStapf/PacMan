package com.pacman.graphicinterface.components.controllers;

import com.pacman.engine.GameSystem;
import com.pacman.graphicinterface.components.javafx.PacDotGraphic;
import com.pacman.graphicinterface.components.javafx.SceneElementGraphic;
import com.pacman.systemelements.GameObject;
import com.pacman.systemelements.Ghost;
import com.pacman.systemelements.PacDot;
import javafx.animation.KeyFrame;

public class PacDotController implements GameObjectController {

    /**
     * Atributo responsável por guardar o objeto {@link PacDot}.
     */
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

    /**
     * Representação gráfico do objeto {@link PacDot}.
     */
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

    }
}
