package com.pacman.graphicinterface;

import com.pacman.engine.SystemGame;
import com.pacman.systemelements.EnergyPill;
import com.pacman.systemelements.GameObject;
import com.pacman.systemelements.Position;
import javafx.animation.KeyFrame;
import javafx.fxml.FXML;
import javafx.scene.layout.Region;

public class EnergyPillController implements GameObjectController {

    @FXML
    private Region energyPillID;

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
    public Region getGameObjectID() {
        return energyPillID;
    }

    @Override
    public void updateGameObjectID() {

    }

    @Override
    public KeyFrame getKeyFrame() {
        return null;
    }

    @Override
    public void destroy() {
        SystemGame.getScreenManager().getBoardPane().getChildren().remove(energyPillID);
    }
}
