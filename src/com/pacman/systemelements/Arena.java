package com.pacman.systemelements;

import com.pacman.engine.Graph;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

public class Arena {

    private ArrayList<ArrayList<LinkedList<SceneElement>>> arena;
    private ArrayList<GameObject> gameObjects;
    private Graph graph = new Graph();

    public Arena(String fileName) {
        arena = new ArrayList<>();
        gameObjects = new ArrayList<>();
        loadArena(fileName);
    }

    public ArrayList<ArrayList<LinkedList<SceneElement>>> getArena() {
        return arena;
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

                        default:
                            arena.get(i).get(j).add(new Floor(new Position(i, j)));
                            graph.addVertex(((Floor) arena.get(i).get(j).getLast()).getVertex());
                            updateEdgesArena(i, j);
                            break;
                    }

                    Collections.sort(arena.get(i).get(j));
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

            if (i > 1) {

                SceneElement seAux = arena.get(i - 1).get(j).getFirst();

                if (seAux instanceof Floor) {
                    graph.addEdge(((Floor) se).getVertex(), ((Floor) seAux).getVertex());
                }
            }

            if (j > 1) {

                SceneElement seAux = arena.get(i).get(j - 1).getFirst();

                if (seAux instanceof Floor) {
                    graph.addEdge(((Floor) se).getVertex(), ((Floor) seAux).getVertex());
                }
            }
        }
    }
}
