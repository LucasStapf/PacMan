package com.pacman.graphicinterface.components.controllers;

import com.pacman.graphicinterface.components.javafx.PlaceGraphic;
import com.pacman.graphicinterface.components.javafx.SceneElementGraphic;
import com.pacman.systemelements.GameObject;
import com.pacman.systemelements.Place;
import javafx.animation.KeyFrame;

public class PlaceController implements GameObjectController{

    private Place place;

    @Override
    public GameObject getGameObject() {
        return place;
    }

    @Override
    public void setGameObject(GameObject gameObject) throws IllegalArgumentException {
        if (!(gameObject instanceof Place)) {
            throw new IllegalArgumentException("The GameObject must be a Place object.");
        } else place = (Place) gameObject;
    }

    private PlaceGraphic placeGraphic;

    @Override
    public SceneElementGraphic getSceneElementGraphic() {
        return placeGraphic;
    }

    @Override
    public void setSceneElementGraphic(SceneElementGraphic sceneElementGraphic) throws IllegalArgumentException {
        if (!(sceneElementGraphic instanceof PlaceGraphic)) {
            throw new IllegalArgumentException("The SceneElementGraphic must be a PlaceGraphic object.");
        } else placeGraphic = (PlaceGraphic) sceneElementGraphic;
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
        place.update();
    }

    @Override
    public void destroy() {
        place.destroy();
    }
}
