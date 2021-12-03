package com.pacman.graphicinterface;

import com.pacman.systemelements.*;

import java.util.Iterator;


/**
 * Classe temporária
 */
public class Print {

//    /**
//     * Método para printar a arena do jogo no terminal
//     * @param arena arena que terá o tabuleiro printado.
//     */
//    public static void printArena(Arena arena){
//
//        PacMan pacMan = null;
//        Iterator iterator = arena.getGameObjectFloorHashMap().keySet().iterator();
//
//        while (iterator.hasNext() && pacMan == null) {
//            GameObject gameObject = (GameObject) iterator.next();
//            if (gameObject instanceof PacMan) pacMan = (PacMan) gameObject;
//        }
//
//        for (int i = 0; i < arena.getBoard().size(); i++) {
//            for (int j = 0; j < arena.getBoard().get(i).size(); j++) {
//                SceneElement se = arena.getBoard().get(i).get(j).getLast();
//                se.print();
//                System.out.print(" ");
//            }
//
//            if (i == 10) {
//                System.out.print("\t");
//                System.out.print("Pac-Man: ");
//            }
//
//            if (i == 11) {
//                System.out.print("\t");
//                System.out.print(pacMan.getPosition());
//            }
//
//            if (i == 12) {
//                System.out.print("\t");
//                System.out.print(pacMan.getVelocity());
//            }
//
////            if (i == 13) {
////                System.out.print("\t");
////                System.out.print("Total Points: ");
////            }
//
//            System.out.println();
//        }
//    }
}
