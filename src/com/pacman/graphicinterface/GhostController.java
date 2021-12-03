package com.pacman.graphicinterface;

import com.pacman.engine.GraphicManager;
import com.pacman.engine.System;
import com.pacman.systemelements.GameObject;
import com.pacman.systemelements.Ghost;
import com.pacman.systemelements.Position;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import javafx.util.Duration;

public class GhostController implements GameObjectController {

    @FXML
    public Region ghostID;

    private Ghost ghost;

    public GhostController() {
        ghost = new Ghost(new Position(0,0));
    }

    public Region getGhostID() {
        return ghostID;
    }

    public Ghost getGhost() {
        return ghost;
    }

    public void setGhost(Ghost ghost) {
        this.ghost = ghost;
    }


    @Override
    public void setGameObject(GameObject gameObject) throws IllegalArgumentException {
        if (!(gameObject instanceof Ghost)) throw new IllegalArgumentException("GameObject must be a Ghost!");
        else ghost = (Ghost) gameObject;
    }

    @Override
    public Region getGameObjectID() {
        return ghostID;
    }

    @Override
    public void updateGameObjectID() {

    }

    @Override
    public KeyFrame getKeyFrame() {

        KeyValue kvX = new KeyValue(ghostID.translateXProperty(), GraphicManager.convertGameToScreenX(ghost));
        KeyValue kvY = new KeyValue(ghostID.translateYProperty(), GraphicManager.convertGameToScreenY(ghost));

        return new KeyFrame(Duration.millis(System.deltaTime), kvX, kvY);
    }

    @Override
    public void destroy() {
        System.getGraphicManager().getBoardPane().getChildren().remove(ghostID);
    }
}
