package com.pacman.systemelements;

import com.pacman.engine.Graph;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Arena {

    private ArrayList<ArrayList<LinkedList<SceneElement>>> arena;
    private HashMap<GameObject, Floor> gameObjects;
    private Graph graph = new Graph();

    public Arena(String fileName) {
        arena = new ArrayList<>();
        gameObjects = new HashMap<>();
        loadArena(fileName);
    }

    public ArrayList<ArrayList<LinkedList<SceneElement>>> getArena() {
        return arena;
    }

    public HashMap<GameObject, Floor> getGameObjects() {
        return gameObjects;
    }

    public Graph getGraph() {
        return graph;
    }

    public void loadArena(String fileName) {

        arena.clear();
        gameObjects.clear();

        try {

            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line;
            int i = 0, j = 0;

            line = br.readLine();

            while (line != null) {

                arena.add(new ArrayList<>());
                for (j = 0; j < line.length(); j++) {

                    arena.get(i).add(new LinkedList<>());

                    switch (line.charAt(j)) {

                        case '+':
                            arena.get(i).get(j).add(new Wall(new Position(i, j), Wall.Orientation.CORNER));
                            break;

                        case '-':
                            arena.get(i).get(j).add(new Wall(new Position(i, j), Wall.Orientation.HORIZONTAL));
                            break;

                        case '|':
                            arena.get(i).get(j).add(new Wall(new Position(i, j), Wall.Orientation.VERTICAL));
                            break;

                        case 'P':
                            Floor fPac = new Floor(new Position(i, j));
                            arena.get(i).get(j).add(fPac);
                            graph.addVertex(fPac.getVertex());

                            PacMan pacMan = new PacMan(new Position(i, j));
                            arena.get(i).get(j).add(pacMan);
                            gameObjects.put(pacMan, fPac);
                            break;

                        case 'G':
                            Floor fGhost = new Floor(new Position(i, j));
                            arena.get(i).get(j).add(fGhost);
                            graph.addVertex(fGhost.getVertex());

                            Ghost ghost = new Ghost(new Position(i, j));
                            arena.get(i).get(j).add(ghost);
                            gameObjects.put(ghost, fGhost);
                            break;

                        default:
                            arena.get(i).get(j).add(new Floor(new Position(i, j)));
                            graph.addVertex(((Floor) arena.get(i).get(j).getLast()).getVertex());
                            break;
                    }

                    Collections.sort(arena.get(i).get(j));
                    updateEdgesArena(i, j);
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
    }

    private void updateEdgesArena(int i, int j) {

        SceneElement se = arena.get(i).get(j).getFirst();

        if (se instanceof Floor) {

            if (i >= 1) {

                SceneElement seAux = arena.get(i - 1).get(j).getFirst();

                if (seAux instanceof Floor) {
                    graph.addEdge(((Floor) se).getVertex(), ((Floor) seAux).getVertex());
                }
            }

            if (j >= 1) {

                SceneElement seAux = arena.get(i).get(j - 1).getFirst();

                if (seAux instanceof Floor) {
                    graph.addEdge(((Floor) se).getVertex(), ((Floor) seAux).getVertex());
                }
            }
        }
    }

    public void updateArena() {

        Iterator iterator = gameObjects.keySet().iterator();

        while (iterator.hasNext()) {

            GameObject go = (GameObject) iterator.next();
            go.update();

            int x = Math.round(go.getPosition().getX());
            int y = Math.round(go.getPosition().getY());

            Floor f = (Floor) arena.get(x).get(y).getFirst();
            Floor fGameObject = gameObjects.get(go);
            fGameObject.highlighted = false;

            if (!f.equals(fGameObject)) {
                arena.get((int) fGameObject.getPosition().getX()).get((int) fGameObject.getPosition().getY()).remove(go);
                arena.get(x).get(y).add(go);
                gameObjects.replace(go, f);
                Collections.sort(arena.get(x).get(y));
            }
        }
    }
}
