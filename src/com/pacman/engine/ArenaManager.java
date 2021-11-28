package com.pacman.engine;

import com.pacman.systemelements.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * Classe responsável por gerenciar a arena do jogo.
 *
 */
public class ArenaManager {

    /**
     * Atributo que armazena a arena.
     */
    private Arena arena;

    /**
     * Atributo que armazena o grafo relaciona à arena.
     */
    private Graph graph;

    /**
     * Construtor padrão.
     */
    public ArenaManager() {
        arena = new Arena();
        graph = new Graph();
    }

    /**
     * Método que retorna a arena gerenciada.
     * @return arena
     */
    public Arena getArena() {
        return arena;
    }

    /**
     * Método que retorna o grafo da arena.
     * @return graph
     */
    public Graph getGraph() {
        return graph;
    }

    /**
     * Método que atualiza as arenas de um determinado vértice.
     * @param board tabuleiro onde se encontra o vértice.
     * @param i linha do vértice.
     * @param j coluna do vértice.
     */
    private void updateEdgesArena(ArrayList<ArrayList<LinkedList<SceneElement>>> board, int i, int j) {

        SceneElement se = board.get(i).get(j).getFirst();

        if (se instanceof Floor) {

            if (i >= 1) {

                SceneElement seAux = board.get(i - 1).get(j).getFirst();

                if (seAux instanceof Floor) {
                    graph.addEdge(((Floor) se).getVertex(), ((Floor) seAux).getVertex());
                }
            }

            if (j >= 1) {

                SceneElement seAux = board.get(i).get(j - 1).getFirst();

                if (seAux instanceof Floor) {
                    graph.addEdge(((Floor) se).getVertex(), ((Floor) seAux).getVertex());
                }
            }
        }
    }

    /**
     * Carrega a arena de um arquivo.
     * @param path endereço do arquivo da arena.
     */
    public void loadArena(String path) {

        ArrayList<ArrayList<LinkedList<SceneElement>>> board = new ArrayList<>();
        HashMap<GameObject, Floor> gameObjectFloorHashMap = new HashMap<>();

        try {

            BufferedReader br = new BufferedReader(new FileReader(path));
            String line;
            int i = 0, j = 0;

            line = br.readLine();

            while (line != null) {

                board.add(new ArrayList<>());
                for (j = 0; j < line.length(); j++) {

                    board.get(i).add(new LinkedList<>());

                    float x = (2 / 2) + (j * 2);
                    float y = (2 / 2) + (i * 2);

                    switch (line.charAt(j)) {

                        case '+':
                            Wall wallCorner =  new Wall(new Position(x, y), Wall.Orientation.CORNER);
                            board.get(i).get(j).add(wallCorner);
                            GameManager.getGameObjectManager().getGameObjects().add(wallCorner);
                            break;

                        case '-':
                            Wall wallHorizontal =  new Wall(new Position(x, y), Wall.Orientation.HORIZONTAL);
                            board.get(i).get(j).add(wallHorizontal);
                            GameManager.getGameObjectManager().getGameObjects().add(wallHorizontal);
                            break;

                        case '|':
                            Wall wallVertical =  new Wall(new Position(x, y), Wall.Orientation.VERTICAL);
                            board.get(i).get(j).add(wallVertical);
                            GameManager.getGameObjectManager().getGameObjects().add(wallVertical);
                            break;

//                        case '.':
//                            Floor fPacDot = new Floor(new Position(x, y));
//                            board.get(i).get(j).add(fPacDot);
//                            graph.addVertex(fPacDot.getVertex());
//
//                            PacDot pacDot = new PacDot(new Position(x, y));
//                            pacDot.setDimension(new Dimension(0.5f,0.5f));
//                            board.get(i).get(j).add(pacDot);
//                            gameObjectFloorHashMap.put(pacDot, fPacDot);
//
//                            GameManager.getGameObjectManager().getGameObjects().add(pacDot);
//                            break;

                        case 'E':
                            Floor fEnergyPill = new Floor(new Position(x, y));
                            board.get(i).get(j).add(fEnergyPill);
                            graph.addVertex(fEnergyPill.getVertex());

                            EnergyPill energyPill = new EnergyPill(new Position(x, y));
//                            energyPill.setDimension(new Dimension(1,1));
                            board.get(i).get(j).add(energyPill);
                            gameObjectFloorHashMap.put(energyPill, fEnergyPill);

                            GameManager.getGameObjectManager().getGameObjects().add(energyPill);
                            break;

                        case 'F':
                            Floor fFruit = new Floor(new Position(x, y));
                            board.get(i).get(j).add(fFruit);
                            graph.addVertex(fFruit.getVertex());

                            Fruit fruit = new Fruit(new Position(x, y));
                            fruit.setDimension(new Dimension(0.5f,0.5f));
                            board.get(i).get(j).add(fruit);
                            gameObjectFloorHashMap.put(fruit, fFruit);

                            GameManager.getGameObjectManager().getGameObjects().add(fruit);
                            break;

                        case 'P':
                            Floor fPac = new Floor(new Position(x, y));
                            board.get(i).get(j).add(fPac);
                            graph.addVertex(fPac.getVertex());

                            PacMan pacMan = new PacMan(new Position(x, y));
//                            pacMan.setDimension(new Dimension(1.5f,1.5f));
                            board.get(i).get(j).add(pacMan);
                            gameObjectFloorHashMap.put(pacMan, fPac);

                            GameManager.getGameObjectManager().getGameObjects().add(pacMan);
                            break;

                        case 'G':
                            Floor fGhost = new Floor(new Position(x, y));
                            board.get(i).get(j).add(fGhost);
                            graph.addVertex(fGhost.getVertex());

                            Ghost ghost = new Ghost(new Position(x, y));
//                            ghost.setDimension(new Dimension(1.5f,1.5f));
                            board.get(i).get(j).add(ghost);
                            gameObjectFloorHashMap.put(ghost, fGhost);

                            GameManager.getGameObjectManager().getGameObjects().add(ghost);
                            break;

                        default:
                            board.get(i).get(j).add(new Floor(new Position(x, y)));
                            graph.addVertex(((Floor) board.get(i).get(j).getLast()).getVertex());
                            break;
                    }

                    Collections.sort(board.get(i).get(j));
                    updateEdgesArena(board, i, j);
                }

                line = br.readLine();
                i++;
            }

            br.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        arena.setBoard(board);
        arena.setGameObjectFloorHashMap(gameObjectFloorHashMap);
    }
}
