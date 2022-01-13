package com.pacman.engine;

public class ScoreManager {

    /**
     * Bonus aplicado na pontuação padrão originada pelo Ghost.
     */
    public static int bonusScoreGhost = 1;

    /**
     * Quantidade de pontos dada pelo {@link com.pacman.systemelements.Ghost}
     */
    public final static int scoreFromGhost = 200;

    /**
     * Quantidade de pontos dada pela {@link com.pacman.systemelements.EnergyPill}
     */
    public final static int scoreFromEnergyPill = 50;

    /**
     * Quantidade de pontos dado pelo {@link com.pacman.systemelements.PacDot}
     */
    public final static int scoreFromPacDot = 10;

}
