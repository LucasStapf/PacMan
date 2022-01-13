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


    /**
     * Guarda o atual estado do efeito da pílula de energia.
     */
    private boolean effectEnergyPill = false;

    /**
     * Duração, em milissegundos, do efeito da pílula de energia.
     */
    public final static long deltaTimeEffect = 10000;

    /**
     * Instante de tempo em que o efeito da pílula de energia foi ativado.
     */
    private long timeStarted;

    /**
     * Inicia o efeito da pílula de energia tornando os Ghosts azuis, lentos e vulneráveis.
     */
    public void startEffectEnergyPill() {

        effectEnergyPill = true;
        timeStarted = System.currentTimeMillis();

        Color color = Color.CADETBLUE;
        float modulus = GameObjectManager.modulusInEffect;

        ((Ghost) GameSystem.gameobjects.blinky().getGameObject()).setVulnerable(true);
        ((Ghost) GameSystem.gameobjects.blinky().getGameObject()).getVelocity().setModulus(modulus);
        ((Ghost) GameSystem.gameobjects.blinky().getGameObject()).setMovement(Ghost.Movement.RANDOM);
        ((GhostGraphic) GameSystem.gameobjects.blinky().getSceneElementGraphic()).setBodyColor(color);

        ((Ghost) GameSystem.gameobjects.pinky().getGameObject()).setVulnerable(true);
        ((Ghost) GameSystem.gameobjects.pinky().getGameObject()).getVelocity().setModulus(modulus);
        ((Ghost) GameSystem.gameobjects.pinky().getGameObject()).setMovement(Ghost.Movement.RANDOM);
        ((GhostGraphic) GameSystem.gameobjects.pinky().getSceneElementGraphic()).setBodyColor(color);

        ((Ghost) GameSystem.gameobjects.inky().getGameObject()).setVulnerable(true);
        ((Ghost) GameSystem.gameobjects.inky().getGameObject()).getVelocity().setModulus(modulus);
        ((GhostGraphic) GameSystem.gameobjects.inky().getSceneElementGraphic()).setBodyColor(color);

        ((Ghost) GameSystem.gameobjects.clyde().getGameObject()).setVulnerable(true);
        ((Ghost) GameSystem.gameobjects.clyde().getGameObject()).getVelocity().setModulus(modulus);
        ((GhostGraphic) GameSystem.gameobjects.clyde().getSceneElementGraphic()).setBodyColor(color);
    }

    /**
     * Verificia o atual estado da pílula de energia. Quando o efeito da pípula acabar, todos os Ghosts voltam
     * para os seus estados padrões.
     */
    public void checkEffectEnergyPill() {

        if (effectEnergyPill && System.currentTimeMillis() - timeStarted > deltaTimeEffect) {

            effectEnergyPill = false;
            ScoreManager.bonusScoreGhost = 1;
            float modulus = GameObjectManager.defaultModulus;

            ((Ghost) GameSystem.gameobjects.blinky().getGameObject()).setVulnerable(false);
            ((Ghost) GameSystem.gameobjects.blinky().getGameObject()).getVelocity().setModulus(modulus);
            ((Ghost) GameSystem.gameobjects.blinky().getGameObject()).setMovement(Ghost.Movement.FOLLOW_TARGET);
            ((GhostGraphic) GameSystem.gameobjects.blinky().getSceneElementGraphic()).setBodyColor(
                    GameSystem.gameobjects.colorBlinky
            );

            ((Ghost) GameSystem.gameobjects.pinky().getGameObject()).setVulnerable(false);
            ((Ghost) GameSystem.gameobjects.pinky().getGameObject()).getVelocity().setModulus(modulus);
            ((Ghost) GameSystem.gameobjects.pinky().getGameObject()).setMovement(Ghost.Movement.FOLLOW_TARGET);
            ((GhostGraphic) GameSystem.gameobjects.pinky().getSceneElementGraphic()).setBodyColor(
                    GameSystem.gameobjects.colorPinky
            );

            ((Ghost) GameSystem.gameobjects.inky().getGameObject()).setVulnerable(false);
            ((Ghost) GameSystem.gameobjects.inky().getGameObject()).getVelocity().setModulus(modulus);
            ((GhostGraphic) GameSystem.gameobjects.inky().getSceneElementGraphic()).setBodyColor(
                    GameSystem.gameobjects.colorInky
            );

            ((Ghost) GameSystem.gameobjects.clyde().getGameObject()).setVulnerable(false);
            ((Ghost) GameSystem.gameobjects.clyde().getGameObject()).getVelocity().setModulus(modulus);
            ((GhostGraphic) GameSystem.gameobjects.clyde().getSceneElementGraphic()).setBodyColor(
                    GameSystem.gameobjects.colorClyde
            );
        }
    }
}
