package com.pacman.graphicinterface;

import com.pacman.systemelements.GameObject;
import javafx.animation.KeyFrame;
import javafx.scene.shape.Rectangle;

public interface GameObjectController {

    public abstract void setGameObject(GameObject gameObject) throws IllegalArgumentException;
    public abstract Rectangle getGameObjectRectangle();
    public abstract void updateGameObjectRectangle();
    public abstract KeyFrame getKeyFrame();
    public abstract void destroy();

}
