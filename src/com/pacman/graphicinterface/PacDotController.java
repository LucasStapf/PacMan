package com.pacman.graphicinterface;

import com.pacman.engine.GraphicManager;
import com.pacman.engine.System;
import com.pacman.systemelements.GameObject;
import com.pacman.systemelements.PacDot;
import com.pacman.systemelements.Position;
import javafx.animation.KeyFrame;
import javafx.fxml.FXML;
import javafx.scene.shape.Rectangle;

public class PacDotController implements GameObjectController {

    @FXML
    private Rectangle pacDotID;

    private PacDot pacDot;

    public PacDotController() {
        pacDot = new PacDot(new Position());
    }

    @Override
    public void setGameObject(GameObject gameObject) throws IllegalArgumentException {
        if (!(gameObject instanceof PacDot)) throw new IllegalArgumentException();
        pacDot = (PacDot) gameObject;
    }

    @Override
    public Rectangle getGameObjectRectangle() {
        return pacDotID;
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
        System.getGraphicManager().getBoardPane().getChildren().remove(pacDotID);
    }
}
