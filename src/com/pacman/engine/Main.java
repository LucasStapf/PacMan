package com.pacman.engine;

import com.pacman.graphicinterface.Print;
import com.pacman.systemelements.*;

import javax.swing.plaf.synth.SynthTextAreaUI;
import javax.xml.bind.ValidationException;
import java.util.Iterator;
import java.util.LinkedList;

public class Main {

    public static void main(String[] args) {

        Arena arena = new Arena("src/com/pacman/systemelements/Arena.txt");
        Print.printArena(arena);

        PacMan pacMan = null;
        Ghost ghost = null;

        Iterator i = arena.getGameObjects().iterator();

        while (i.hasNext() && pacMan == null || ghost == null) {

            GameObject go = (GameObject) i.next();
            if (go instanceof PacMan) pacMan = (PacMan) go;
            else if (go instanceof Ghost) ghost = (Ghost) go;
        }

        Floor fPacMan, fGhost;
        int xPac, yPac, xGhost, yGhost;

        xPac = (int) pacMan.getPosition().getX();
        yPac = (int) pacMan.getPosition().getY();
        fPacMan = (Floor) arena.getArena().get(xPac).get(yPac).getFirst();

        xGhost = (int) ghost.getPosition().getX();
        yGhost = (int) ghost.getPosition().getY();
        fGhost = (Floor) arena.getArena().get(xGhost).get(yGhost).getFirst();

        System.out.println(arena.getGraph().getAdjVertices().get(fGhost.getVertex()));

        DijkstraAlgorithm dijkstraAlgorithm = new DijkstraAlgorithm(arena.getGraph(), fGhost.getVertex());
        LinkedList<Vertex<Floor>> path = dijkstraAlgorithm.getShortestPath(fPacMan.getVertex());

        i = path.iterator();
        while (i.hasNext()) {
            Floor f = ((Vertex<Floor>) i.next()).getT();
            f.highlighted = true;
        }

        System.out.println();
        System.out.println();

        Print.printArena(arena);
    }
}
