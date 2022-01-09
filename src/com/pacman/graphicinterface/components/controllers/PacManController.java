package com.pacman.graphicinterface.components.controllers;

import com.pacman.engine.ScreenManager;
import com.pacman.engine.SystemGame;
import com.pacman.graphicinterface.GameObjectController;
import com.pacman.graphicinterface.components.javafx.PacManGraphic;
import com.pacman.graphicinterface.components.javafx.SceneElementGraphic;
import com.pacman.systemelements.GameObject;
import com.pacman.systemelements.PacMan;
import com.pacman.systemelements.Velocity;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;

public class PacManController implements GameObjectController {

    /**
     * Atributo responsável por guardar o objeto {@link PacMan}.
     */
    private PacMan pacMan;

    @Override
    public GameObject getGameObject() {
        return pacMan;
    }

    @Override
    public void setGameObject(GameObject gameObject) throws IllegalArgumentException {
        if (!(gameObject instanceof PacMan)) {
            throw new IllegalArgumentException("The GameObject must be a PacMan object.");
        } else pacMan = (PacMan) gameObject;
    }

    /**
     * Atributo responsável por guardar a representação gráfica do {@link PacMan}.
     */
    private PacManGraphic pacManGraphic;

    @Override
    public SceneElementGraphic getSceneElementGraphic() {
        return pacManGraphic;
    }

    @Override
    public void setSceneElementGraphic(SceneElementGraphic sceneElementGraphic) throws IllegalArgumentException {
        if (!(sceneElementGraphic instanceof PacManGraphic)) {
            throw new IllegalArgumentException("The SceneElementGraphic must be a PacManGraphic object.");
        } else pacManGraphic = (PacManGraphic) sceneElementGraphic;
    }

    @Override
    public KeyFrame getTranslationKeyFrame() {

        KeyValue keyValueX = new KeyValue(pacManGraphic.translateXProperty(),
                ScreenManager.convertGameToScreenX(pacMan));
        KeyValue keyValueY = new KeyValue(pacManGraphic.translateYProperty(),
                ScreenManager.convertGameToScreenY(pacMan));

        return new KeyFrame(Duration.millis(SystemGame.deltaTime), keyValueX, keyValueY);
    }

    @Override
    public KeyFrame getAnimationKeyFrame() {

        System.out.println("Angulo: " + pacManGraphic.getMouthAngle());

        double finalMouthAngle;

        if (pacManGraphic.getMouthAngle() == 30) finalMouthAngle = 0;
        else finalMouthAngle = 30;

        KeyValue keyValueMouth = new KeyValue(pacManGraphic.mouthAngleProperty(), finalMouthAngle);
//        pacManGraphic.setMouthAngle(finalMouthAngle);

        return new KeyFrame(Duration.millis(SystemGame.deltaTime), keyValueMouth);
    }

    @Override
    public void destroy() {


    }

    private final EventHandler<KeyEvent> keyEventHandler = new EventHandler<KeyEvent>() {

        @Override
        public void handle(KeyEvent event) {

            switch (event.getCode()) {
                case UP:
//                    pacMan.changeDirectionTo(Velocity.Direction.DOWN);
                    pacMan.getVelocity().setDirection(Velocity.Direction.DOWN);
                    pacManGraphic.setDirection(PacManGraphic.Direction.DOWN);
                    break;

                case DOWN:
//                    pacMan.changeDirectionTo(Velocity.Direction.UP);
                    pacMan.getVelocity().setDirection(Velocity.Direction.UP);
                    pacManGraphic.setDirection(PacManGraphic.Direction.UP);
                    break;

                case RIGHT:
//                    pacMan.changeDirectionTo(Velocity.Direction.RIGHT);
                    pacMan.getVelocity().setDirection(Velocity.Direction.RIGHT);
                    pacManGraphic.setDirection(PacManGraphic.Direction.RIGHT);
                    break;

                case LEFT:
//                    pacMan.changeDirectionTo(Velocity.Direction.LEFT);
                    pacMan.getVelocity().setDirection(Velocity.Direction.LEFT);
                    pacManGraphic.setDirection(PacManGraphic.Direction.LEFT);
                    break;
            }
        }
    };

    public EventHandler<KeyEvent> keyEventHandler() {
        return keyEventHandler;
    }
}
