package com.pacman.graphicinterface;

import com.pacman.systemelements.*;

import java.util.Iterator;

// classe temporaria

public class Print {

    public static void printArena(Arena arena){

        Ghost ghost = null;
        Iterator iterator = arena.getHashGameObjects().keySet().iterator();

        while (iterator.hasNext() && ghost == null) {
            GameObject gameObject = (GameObject) iterator.next();
            if (gameObject instanceof Ghost) ghost = (Ghost) gameObject;
        }

        for (int i = 0; i < arena.getArena().size(); i++) {
            for (int j = 0; j < arena.getArena().get(i).size(); j++) {
                SceneElement se = arena.getArena().get(i).get(j).getLast();
                se.print();
                System.out.print(" ");
            }

            if (i == 10) {
                System.out.print("\t");
                System.out.print("Ghost: ");
            }

            if (i == 11) {
                System.out.print("\t");
                System.out.print(ghost.getPosition());
            }

            if (i == 12) {
                System.out.print("\t");
                System.out.print(ghost.getVelocity());
            }

//            if (i == 13) {
//                System.out.print("\t");
//                System.out.print("Total Points: ");
//            }

            System.out.println();
        }
    }
}
