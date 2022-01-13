package com.pacman.graphicinterface.components.controllers;

import com.pacman.engine.GameSystem;
import com.pacman.graphicinterface.components.javafx.EnergyPillGraphic;
import com.pacman.graphicinterface.components.javafx.SceneElementGraphic;
import com.pacman.systemelements.EnergyPill;
import com.pacman.systemelements.GameObject;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class EnergyPillController implements GameObjectController {

    private EnergyPill energyPill;

    @Override
    public GameObject getGameObject() {
        return energyPill;
    }

    @Override
    public void setGameObject(GameObject gameObject) throws IllegalArgumentException {
        if (!(gameObject instanceof EnergyPill)) throw new IllegalArgumentException();
        else energyPill = (EnergyPill) gameObject;
    }

    private EnergyPillGraphic energyPillGraphic;

    @Override
    public SceneElementGraphic getSceneElementGraphic() {
        return energyPillGraphic;
    }

    @Override
    public void setSceneElementGraphic(SceneElementGraphic sceneElementGraphic) throws IllegalArgumentException {
        if (!(sceneElementGraphic instanceof EnergyPillGraphic)) {
            throw new IllegalArgumentException("The SceneElementGraphic must be a GhostGraphic object.");
        } else energyPillGraphic = (EnergyPillGraphic) sceneElementGraphic;
    }

    @Override
    public KeyFrame getTranslationKeyFrame() {
        return null;
    }

    @Override
    public KeyFrame getAnimationKeyFrame() {

        Color finalColor;
        if (energyPillGraphic.getColor() == Color.GREEN) finalColor = Color.RED;
        else finalColor = Color.GREEN;

        KeyValue keyValueColor = new KeyValue(energyPillGraphic.colorProperty(), finalColor);

        return new KeyFrame(Duration.millis(GameSystem.deltaTime * 10), keyValueColor);
    }

    @Override
    public void update() {

    }

    @Override
    public void destroy() {
//        GameSystem.getScreenManager().getBoardPane().getChildren().remove(energyPillID);
    }
}
