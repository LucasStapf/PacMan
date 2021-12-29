package com.pacman.engine;

import com.pacman.systemelements.Ghost;
import com.pacman.systemelements.PacDot;
import com.pacman.systemelements.PacMan;

public class ScoreManager {

    private static int bonus = 1;
    private static int bonusPacDot = 1;
    private static int bonusGhost = 1;

    private static int scoreFromGhost = 200;
    private static int scoreFromPacDot = 1;

    public static void addScore(PacMan pacMan, int score) {
        pacMan.setScore(pacMan.getScore() + score);
    }

    public static void addScore(PacMan pacMan, PacDot pacDot) {
        pacMan.setScore(pacMan.getScore() + scoreFromPacDot * bonusPacDot * bonus);
    }

    public static void addScore(PacMan pacMan, Ghost ghost) {
        pacMan.setScore(pacMan.getScore() + scoreFromGhost * bonusGhost * bonus);
    }
}
