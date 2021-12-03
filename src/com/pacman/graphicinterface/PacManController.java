package com.pacman.graphicinterface;

import com.pacman.engine.GraphicManager;
import com.pacman.engine.System;
import com.pacman.systemelements.*;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.fxml.FXML;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import javafx.util.Duration;


public class PacManController implements GameObjectController {

    @FXML
    private Region pacManID;

    private PacMan pacMan;

    public PacManController() {
        pacMan = new PacMan(new Position());
    }

    public Region getPacManID() {
        return pacManID;
    }

    public PacMan getPacMan() {
        return pacMan;
    }

    public void setPacMan(PacMan pacMan) {
        this.pacMan = pacMan;
    }

    public void keyPressed(KeyEvent keyEvent) {

        switch (keyEvent.getCode()) {
            case UP:
                pacMan.changeDirectionTo(Velocity.Direction.DOWN);
                break;

            case DOWN:
                pacMan.changeDirectionTo(Velocity.Direction.UP);
                break;

            case RIGHT:
                pacMan.changeDirectionTo(Velocity.Direction.RIGHT);
                break;

            case LEFT:
                pacMan.changeDirectionTo(Velocity.Direction.LEFT);
                break;
        }
    }

    public void mouseClicked(MouseEvent mouseEvent) {
        pacManID.requestFocus();
        pacMan.getVelocity().setModulus(50);
    }


    @Override
    public void setGameObject(GameObject gameObject) throws IllegalArgumentException {
        if (!(gameObject instanceof PacMan)) throw new IllegalArgumentException("GameObject must be a PacMan!");
        else pacMan = (PacMan) gameObject;
    }

    @Override
    public Region getGameObjectID() {
        return pacManID;
    }

    @Override
    public void updateGameObjectID() {

    }

    @Override
    public KeyFrame getKeyFrame() {

        KeyValue kvX = new KeyValue(pacManID.translateXProperty(), GraphicManager.convertGameToScreenX(pacMan));
        KeyValue kvY = new KeyValue(pacManID.translateYProperty(), GraphicManager.convertGameToScreenY(pacMan));

        return new KeyFrame(Duration.millis(System.deltaTime), kvX, kvY);
    }

    @Override
    public void destroy() {
        System.getGraphicManager().getBoardPane().getChildren().remove(pacManID);
    }
}
