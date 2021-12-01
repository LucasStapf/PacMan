package com.pacman.graphicinterface;

import com.pacman.engine.GraphicManager;
import com.pacman.systemelements.*;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.fxml.FXML;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;


public class PacManController implements GameObjectController {

    @FXML
    private Rectangle pacmanID;

    private PacMan pacMan;

    public PacManController() {
        pacMan = new PacMan(new Position());
    }

    public Rectangle getPacmanID() {
        return pacmanID;
    }

    public PacMan getPacMan() {
        return pacMan;
    }

    public void setPacMan(PacMan pacMan) {
        this.pacMan = pacMan;
    }

    public void keyPressed(KeyEvent keyEvent) {

        pacMan.getVelocity().setModulus(50);

        switch (keyEvent.getCode()) {
            case UP:
                pacMan.getVelocity().setDirection(Velocity.Direction.DOWN);
                break;

            case DOWN:
                pacMan.getVelocity().setDirection(Velocity.Direction.UP);
                break;

            case RIGHT:
                pacMan.getVelocity().setDirection(Velocity.Direction.RIGHT);
                break;

            case LEFT:
                pacMan.getVelocity().setDirection(Velocity.Direction.LEFT);
                break;
        }
    }

    public void mouseClicked(MouseEvent mouseEvent) {
        pacmanID.requestFocus();
    }


    @Override
    public void setGameObject(GameObject gameObject) throws IllegalArgumentException {
        if (!(gameObject instanceof PacMan)) throw new IllegalArgumentException("GameObject must be a PacMan!");
        else pacMan = (PacMan) gameObject;
    }

    @Override
    public Rectangle getGameObjectRectangle() {
        return pacmanID;
    }

    @Override
    public KeyFrame getKeyFrame() {

        KeyValue kvX = new KeyValue(pacmanID.translateXProperty(), GraphicManager.convertGameToScreenX(pacMan));
        KeyValue kvY = new KeyValue(pacmanID.translateYProperty(), GraphicManager.convertGameToScreenY(pacMan));

        return new KeyFrame(Duration.millis(GraphicManager.getDeltaTime()), kvX, kvY);
    }
}
