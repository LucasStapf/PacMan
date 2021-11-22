package com.pacman.systemelements;

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

                        case '-':
                            arena.get(i).get(j).add(new Wall(new Position(i, j), Wall.Orientation.HORIZONTAL));

                        case '|':
                            arena.get(i).get(j).add(new Wall(new Position(i, j), Wall.Orientation.VERTICAL));

                        case ':':
                            arena.get(i).get(j).add(null);
                            break;

                        default:
                            arena.get(i).get(j).add(new Floor(new Position(i, j)));
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
}
