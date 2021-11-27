package com.pacman.graphicinterface;

import com.pacman.systemelements.*;

import java.util.Iterator;

// classe temporaria

public class Print {

    public static void printArena(Arena arena){

        PacMan pacMan = null;
        Iterator iterator = arena.getGameObjects().keySet().iterator();

        while (iterator.hasNext() && pacMan == null) {
            GameObject gameObject = (GameObject) iterator.next();
            if (gameObject instanceof PacMan) pacMan = (PacMan) gameObject;
        }

        for (int i = 0; i < arena.getArena().size(); i++) {
            for (int j = 0; j < arena.getArena().get(i).size(); j++) {
                SceneElement se = arena.getArena().get(i).get(j).getLast();
                se.print();
                System.out.print(" ");
            }

            if (i == 10) {
                System.out.print("\t");
                System.out.print("Pac-Man: ");
            }

            if (i == 11) {
                System.out.print("\t");
                System.out.print(pacMan.getPosition());
            }

            if (i == 12) {
                System.out.print("\t");
                System.out.print(pacMan.getVelocity());
            }

            if (i == 13) {
                System.out.print("\t");
                System.out.print("Total Points: ");
            }

            System.out.println();
        }
    }
}
