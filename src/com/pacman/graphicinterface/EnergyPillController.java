package com.pacman.graphicinterface;

import com.pacman.engine.System;
import com.pacman.systemelements.EnergyPill;
import com.pacman.systemelements.GameObject;
import com.pacman.systemelements.Position;
import javafx.animation.KeyFrame;
import javafx.fxml.FXML;
import javafx.scene.shape.Rectangle;

public class EnergyPillController implements GameObjectController {

    @FXML
    private Rectangle energyPillID;

    private EnergyPill energyPill;

    public EnergyPillController() {
        energyPill = new EnergyPill(new Position());
    }

    @Override
    public void setGameObject(GameObject gameObject) throws IllegalArgumentException {
        if (!(gameObject instanceof EnergyPill)) throw new IllegalArgumentException();
        else energyPill = (EnergyPill) gameObject;
    }

    @Override
    public Rectangle getGameObjectRectangle() {
        return energyPillID;
    }

    @Override
    public void updateGameObjectRectangle() {

    }

    @Override
    public KeyFrame getKeyFrame() {
        return null;
    }

    @Override
    public void destroy() {
        System.getGraphicManager().getBoardPane().getChildren().remove(energyPillID);
    }
}
