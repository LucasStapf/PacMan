package com.pacman.graphicinterface;

import com.pacman.engine.System;
import com.pacman.systemelements.*;
import javafx.animation.KeyFrame;
import javafx.fxml.FXML;
import javafx.scene.layout.Region;

public class WallController implements GameObjectController {

    @FXML
    private Region wallID;

    private Wall wall;

    public WallController() {
        wall = new Wall(new Position(), Wall.Orientation.CORNER);
        wall.setDimension(new Dimension(20,20));
    }

    @Override
    public void setGameObject(GameObject gameObject) throws IllegalArgumentException {
        if (!(gameObject instanceof Wall)) throw new IllegalArgumentException("GameObject must be a Wall!");
        else wall = (Wall) gameObject;
    }

    @Override
    public Region getGameObjectID() {
        return wallID;
    }

    @Override
    public void updateGameObjectID() {

        switch (wall.orientation) {

            case CORNER_TOP_LEFT:
                wallID.setStyle("-fx-background-radius: 10 0 0 0; -fx-background-color: black");
                break;

            case CORNER_TOP_RIGHT:
                wallID.setStyle("-fx-background-radius: 0 10 0 0; -fx-background-color: black");
                break;

            case CORNER_BUTTON_LEFT:
                wallID.setStyle("-fx-background-radius: 0 0 10 0; -fx-background-color: black");
                break;

            case CORNER_BUTTON_RIGHT:
                wallID.setStyle("-fx-background-radius: 0 0 0 10; -fx-background-color: black");
                break;
        }
    }

    @Override
    public KeyFrame getKeyFrame() {
        return null;
    }

    @Override
    public void destroy() {
        System.getGraphicManager().getBoardPane().getChildren().remove(wallID);
    }
}
