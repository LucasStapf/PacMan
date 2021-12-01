package com.pacman.graphicinterface;

import com.pacman.systemelements.*;
import javafx.animation.KeyFrame;
import javafx.fxml.FXML;
import javafx.scene.shape.Rectangle;

public class WallController implements GameObjectController {

    @FXML
    private Rectangle wallID;

    private Wall wall;

    public WallController() {
        wall = new Wall(new Position(), Wall.Orientation.CORNER);
        wall.setDimension(new Dimension(20,20));
    }

    public Rectangle getWallID() {
        return wallID;
    }

    public Wall getWall() {
        return wall;
    }

    public void setWall(Wall wall) {
        this.wall = wall;
    }

    @Override
    public void setGameObject(GameObject gameObject) throws IllegalArgumentException {
        if (!(gameObject instanceof Wall)) throw new IllegalArgumentException("GameObject must be a Wall!");
        else wall = (Wall) gameObject;
    }

    @Override
    public Rectangle getGameObjectRectangle() {
        return wallID;
    }

    @Override
    public KeyFrame getKeyFrame() {
        return null;
    }
}
