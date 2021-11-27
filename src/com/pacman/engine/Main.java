package com.pacman.engine;

import com.pacman.graphicinterface.Print;
import com.pacman.systemelements.*;

import javax.swing.plaf.synth.SynthTextAreaUI;
import javax.xml.bind.ValidationException;
import java.util.Iterator;
import java.util.LinkedList;

public class Main {

    public static void main(String[] args) {
        GameManager.start();
    }

//    public static void main(String[] args) {
//
//        Arena arena = new Arena("src/com/pacman/systemelements/Arena.txt");
//        CollisionManager collisionManager = new CollisionManager(arena);
//
////        Print.printArena(arena);
//
//        PacMan pacMan = null;
//        Ghost ghost = null;
//
//        Iterator i = arena.getGameObjects().keySet().iterator();
//
//        while (i.hasNext() && pacMan == null || ghost == null) {
//
//            GameObject go = (GameObject) i.next();
//            if (go instanceof PacMan) pacMan = (PacMan) go;
//            else if (go instanceof Ghost) ghost = (Ghost) go;
//        }
//
//        Floor fPacMan, fGhost;
//        int iPac, jPac, iGhost, jGhost;
//
//        iPac = Math.round(pacMan.getPosition().getY() / 2.0f - 1 / 2.0f);
//        jPac = Math.round(pacMan.getPosition().getX() / 2.0f - 1 / 2.0f);
//
//        System.out.println(pacMan.getPosition().getX() + " " + pacMan.getPosition().getY());
//        System.out.println(iPac + " " + jPac);
//
//        fPacMan = (Floor) arena.getArena().get(iPac).get(jPac).getFirst();
//
//        iGhost = Math.round(ghost.getPosition().getY() / 2.0f - 1 / 2.0f);
//        jGhost = Math.round(ghost.getPosition().getX() / 2.0f - 1 / 2.0f);
//        fGhost = (Floor) arena.getArena().get(iGhost).get(jGhost).getFirst();
//
////        System.out.println(arena.getGraph().getAdjVertices().get(fGhost.getVertex()));
//
//        DijkstraAlgorithm dijkstraAlgorithm = new DijkstraAlgorithm(arena.getGraph(), fGhost.getVertex());
//        LinkedList<Vertex<Floor>> path = dijkstraAlgorithm.getShortestPath(fPacMan.getVertex());
//
//        i = path.iterator();
//        while (i.hasNext()) {
//            Floor f = ((Vertex<Floor>) i.next()).getT();
//            f.highlighted = true;
//        }
//
//        System.out.println();
//        System.out.println();
//
//        Print.printArena(arena);
//
//        int count = 0;
//
//        ghost.setMovement(Ghost.Movement.FOLLOW_PACMAN);
//        ghost.setPath(path);
//        ghost.getVelocity().setModulus(1f);
//
//        while (count < 200) {
//
//            try {
//                Thread.sleep(1000);
//                arena.updateArena();
//                collisionManager.handleCollisions();
//                Print.printArena(arena);
//                count++;
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//    }
}
