package com.pacman.graphicinterface.components.controllers;

import com.pacman.graphicinterface.components.javafx.SceneElementGraphic;
import com.pacman.graphicinterface.components.javafx.WallGraphic;
import com.pacman.systemelements.GameObject;
import com.pacman.systemelements.Wall;
import javafx.animation.KeyFrame;

public class WallController implements GameObjectController {

    /**
     * Atributo responsável por guardar o objeto {@link Wall}.
     */
    private Wall wall;

    @Override
    public GameObject getGameObject() {
        return wall;
    }

    @Override
    public void setGameObject(GameObject gameObject) throws IllegalArgumentException {
        if (!(gameObject instanceof Wall)) throw new IllegalArgumentException("GameObject must be a Wall!");
        else wall = (Wall) gameObject;
    }

    /**
     * Atributo responsável por guardar a representação gráfica do {@link Wall}.
     */
    private WallGraphic wallGraphic;

    @Override
    public SceneElementGraphic getSceneElementGraphic() {
        return wallGraphic;
    }

    @Override
    public void setSceneElementGraphic(SceneElementGraphic sceneElementGraphic) throws IllegalArgumentException {
        if (!(sceneElementGraphic instanceof WallGraphic)) {
            throw new IllegalArgumentException("The SceneElementGraphic must be a WallGraphic object.");
        } else wallGraphic = (WallGraphic) sceneElementGraphic;
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
        wall.update();
    }

    @Override
    public void destroy() {

    }
}
