package com.pacman.graphicinterface.components.controllers;

import com.pacman.engine.ScreenManager;
import com.pacman.engine.SystemGame;
import com.pacman.graphicinterface.GameObjectController;
import com.pacman.graphicinterface.components.javafx.PacManGraphic;
import com.pacman.graphicinterface.components.javafx.SceneElementGraphic;
import com.pacman.systemelements.GameObject;
import com.pacman.systemelements.PacMan;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
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

        double finalMouthAngle = pacManGraphic.getMouthAngle() == 30 ? 0 : 30;
        KeyValue keyValueMouth = new KeyValue(pacManGraphic.mouthAngleProperty(), finalMouthAngle);

        return new KeyFrame(Duration.millis(SystemGame.deltaTime), keyValueMouth);
    }

    @Override
    public void destroy() {


    }
}
