package com.pacman.engine;

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


}
