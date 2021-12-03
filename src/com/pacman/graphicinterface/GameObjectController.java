package com.pacman.graphicinterface;

import com.pacman.systemelements.GameObject;
import javafx.animation.KeyFrame;
import javafx.scene.layout.Region;

public interface GameObjectController {

    public abstract void setGameObject(GameObject gameObject) throws IllegalArgumentException;
    public abstract Region getGameObjectID();
    public abstract void updateGameObjectID();
    public abstract KeyFrame getKeyFrame();
    public abstract void destroy();

}
