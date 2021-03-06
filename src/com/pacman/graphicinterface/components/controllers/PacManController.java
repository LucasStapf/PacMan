package com.pacman.graphicinterface.components.controllers;

import com.pacman.engine.GameSystem;
import com.pacman.engine.ScreenManager;
import com.pacman.graphicinterface.components.javafx.PacManGraphic;
import com.pacman.graphicinterface.components.javafx.SceneElementGraphic;
import com.pacman.systemelements.Direction;
import com.pacman.systemelements.GameObject;
import com.pacman.systemelements.PacMan;
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

    /**
     * Retorna o {@link PacMan} controlado.
     * @return o {@link PacMan} controlado.
     */
    public PacMan getPacMan() {
        return pacMan;
    }

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

        KeyValue keyValueX = new KeyValue(pacManGraphic.layoutXProperty(),
                ScreenManager.convertGameToScreenX(pacMan));
        KeyValue keyValueY = new KeyValue(pacManGraphic.layoutYProperty(),
                ScreenManager.convertGameToScreenY(pacMan));

        return new KeyFrame(Duration.millis(GameSystem.deltaTime), keyValueX, keyValueY);
    }

    @Override
    public KeyFrame getAnimationKeyFrame() {

        double finalMouthAngle;

        if (pacManGraphic.getMouthAngle() == 30) finalMouthAngle = 0;
        else finalMouthAngle = 30;

        KeyValue keyValueMouth = new KeyValue(pacManGraphic.mouthAngleProperty(), finalMouthAngle);

        return new KeyFrame(Duration.millis(GameSystem.deltaTime * 10), keyValueMouth);
    }

    @Override
    public void update() {
        pacMan.update();
        pacManGraphic.setDirection(pacMan.getVelocity().getDirection());
    }

    @Override
    public void destroy() {


    }

    public void requestFocus() {
        pacManGraphic.requestFocus();
    }

    private final EventHandler<KeyEvent> keyEventHandler = new EventHandler<KeyEvent>() {

        @Override
        public void handle(KeyEvent event) {

            switch (event.getCode()) {
                case UP:
                    pacMan.changeDirectionTo(Direction.DOWN);
                    break;

                case DOWN:
                    pacMan.changeDirectionTo(Direction.UP);
                    break;

                case RIGHT:
                    pacMan.changeDirectionTo(Direction.RIGHT);
                    break;

                case LEFT:
                    pacMan.changeDirectionTo(Direction.LEFT);
                    break;
            }
        }
    };

    public EventHandler<KeyEvent> keyEventHandler() {
        return keyEventHandler;
    }
}
