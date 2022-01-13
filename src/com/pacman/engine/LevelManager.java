package com.pacman.engine;

import com.pacman.graphicinterface.components.javafx.GhostGraphic;
import com.pacman.systemelements.Ghost;
import javafx.scene.paint.Color;

public class LevelManager {

    /**
     * Número de PacDots restantes na arena.
     */
    private int pacdotsNumber = 0;

    /**
     * Retorna o número de PacDots restantes na arena.
     * @return o número de PacDots restantes na arena
     */
    public int pacdotsNumber() {
        return pacdotsNumber;
    }

    /**
     * Altera o número de PacDots restantes na arena.
     * @param pacdotsNumber novo número.
     */
    public void setPacdotsNumber(int pacdotsNumber) {
        this.pacdotsNumber = pacdotsNumber;
    }

    /**
     * Número de EnergyPills restantes na arena.
     */
    private int energyPillNumber = 0;

    /**
     * Retorna o número de EnergyPills restantes na arena.
     * @return o número de EnergyPills restantes na arena.
     */
    public int energyPillNumber() {
        return energyPillNumber;
    }

    /**
     * Altera o número de EneryPills restantes na arena.
     * @param energyPillNumber novo número.
     */
    public void setEnergyPillNumber(int energyPillNumber) {
        this.energyPillNumber = energyPillNumber;
    }


    private boolean effectEnergyPill = false;

    public final static long deltaTimeEffect = 10000;
    private long timeStarted;

    public void startEffectEnergyPill() {

        effectEnergyPill = true;
        timeStarted = System.currentTimeMillis();

        Color color = Color.CADETBLUE;

        ((Ghost) GameSystem.gameObjectManager.blinky().getGameObject()).setVulnerable(true);
        ((Ghost) GameSystem.gameObjectManager.blinky().getGameObject()).setMovement(Ghost.Movement.RANDOM);
        ((GhostGraphic) GameSystem.gameObjectManager.blinky().getSceneElementGraphic()).setBodyColor(color);

        ((Ghost) GameSystem.gameObjectManager.pinky().getGameObject()).setVulnerable(true);
        ((Ghost) GameSystem.gameObjectManager.pinky().getGameObject()).setMovement(Ghost.Movement.RANDOM);
        ((GhostGraphic) GameSystem.gameObjectManager.pinky().getSceneElementGraphic()).setBodyColor(color);

        ((Ghost) GameSystem.gameObjectManager.inky().getGameObject()).setVulnerable(true);
        ((GhostGraphic) GameSystem.gameObjectManager.inky().getSceneElementGraphic()).setBodyColor(color);

        ((Ghost) GameSystem.gameObjectManager.clyde().getGameObject()).setVulnerable(true);
        ((GhostGraphic) GameSystem.gameObjectManager.clyde().getSceneElementGraphic()).setBodyColor(color);
    }

    public void checkEffectEnergyPill() {

        if (effectEnergyPill && System.currentTimeMillis() - timeStarted > deltaTimeEffect) {

            effectEnergyPill = false;

            ((Ghost) GameSystem.gameObjectManager.blinky().getGameObject()).setVulnerable(false);
            ((Ghost) GameSystem.gameObjectManager.blinky().getGameObject()).setMovement(Ghost.Movement.FOLLOW_TARGET);
            ((GhostGraphic) GameSystem.gameObjectManager.blinky().getSceneElementGraphic()).setBodyColor(
                    GameSystem.gameObjectManager.colorBlinky
            );

            ((Ghost) GameSystem.gameObjectManager.pinky().getGameObject()).setVulnerable(false);
            ((Ghost) GameSystem.gameObjectManager.pinky().getGameObject()).setMovement(Ghost.Movement.FOLLOW_TARGET);
            ((GhostGraphic) GameSystem.gameObjectManager.pinky().getSceneElementGraphic()).setBodyColor(
                    GameSystem.gameObjectManager.colorPinky
            );

            ((Ghost) GameSystem.gameObjectManager.inky().getGameObject()).setVulnerable(false);
            ((GhostGraphic) GameSystem.gameObjectManager.inky().getSceneElementGraphic()).setBodyColor(
                    GameSystem.gameObjectManager.colorInky
            );

            ((Ghost) GameSystem.gameObjectManager.clyde().getGameObject()).setVulnerable(false);
            ((GhostGraphic) GameSystem.gameObjectManager.clyde().getSceneElementGraphic()).setBodyColor(
                    GameSystem.gameObjectManager.colorClyde
            );
        }
    }
}
