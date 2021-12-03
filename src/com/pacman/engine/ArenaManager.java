package com.pacman.engine;

import com.pacman.graphicinterface.GameObjectController;
import com.pacman.systemelements.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

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
     * Método que atualiza as arestas de um determinado vértice.
     * @param board tabuleiro onde se encontra o vértice.
     * @param i linha do vértice.
     * @param j coluna do vértice.
     */
    private void updateEdgesArena(ArrayList<ArrayList<Floor>> board, int i, int j) {

        Floor floor = board.get(i).get(j);

        if (floor != null) {

            if (i >= 1) {
                Floor auxFloor = board.get(i - 1).get(j);
                if (auxFloor != null) graph.addEdge(floor.getVertex(), auxFloor.getVertex());
            }

            if (j >= 1) {
                Floor auxFloor = board.get(i).get(j - 1);
                if (auxFloor != null) graph.addEdge(floor.getVertex(), auxFloor.getVertex());
            }
        }
    }

    public Node createNodeOf(GameObject gameObject) {

        Node node = null;
        String path = "/com/pacman/graphicinterface/fxml/" + gameObject.getClass().getSimpleName() + ".fxml";

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(path));

        try {
            node = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        GameObjectController gameObjectController = fxmlLoader.getController();
        System.getGameObjectManager().getGameObjectController().put(gameObject, gameObjectController);

        gameObjectController.setGameObject(gameObject);

        double x = GraphicManager.convertGameToScreenX(gameObject);
        gameObjectController.getGameObjectID().setTranslateX(x);

        double y = GraphicManager.convertGameToScreenY(gameObject);
        gameObjectController.getGameObjectID().setTranslateY(y);

        gameObjectController.getGameObjectID().setMinWidth(gameObject.getDimension().getWidth());
        gameObjectController.getGameObjectID().setMinHeight(gameObject.getDimension().getHeight());

        gameObjectController.getGameObjectID().setMaxWidth(gameObject.getDimension().getWidth());
        gameObjectController.getGameObjectID().setMaxHeight(gameObject.getDimension().getHeight());

//        gameObjectController.getGameObjectID().prefWidth(gameObject.getDimension().getWidth());
//        gameObjectController.getGameObjectID().prefHeight(gameObject.getDimension().getHeight());

        gameObjectController.updateGameObjectID();

        return node;
    }

    public void loadArenaFrom(String path) {

        ArrayList<ArrayList<Floor>> board = new ArrayList<>();

        try {

            BufferedReader br = new BufferedReader(new FileReader(path));
            String line = br.readLine();
            int i = 0, j = 0;

            while (line != null) {

                board.add(new ArrayList<>());

                for (j = 0; j < line.length(); j++) {

                    double x = (Floor.width / 2.0) + (j * Floor.width);
                    double y = (Floor.height / 2.0) + (i * Floor.height);

                    GameObject gameObject = null;
                    Floor floor = null;

                    switch (line.charAt(j)) {

                        case '1':
                            gameObject = new Wall(new Position(x, y), Wall.Orientation.CORNER_TOP_LEFT);
                            break;

                        case '2':
                            gameObject = new Wall(new Position(x, y), Wall.Orientation.CORNER_TOP_RIGHT);
                            break;

                        case '3':
                            gameObject = new Wall(new Position(x, y), Wall.Orientation.CORNER_BUTTON_LEFT);
                            break;

                        case '4':
                            gameObject = new Wall(new Position(x, y), Wall.Orientation.CORNER_BUTTON_RIGHT);
                            break;

                        case '5':
                            gameObject = new Wall(new Position(x, y), Wall.Orientation.CORNER_TOP);
                            break;

                        case '6':
                            gameObject = new Wall(new Position(x, y), Wall.Orientation.CORNER_BUTTON);
                            break;

                        case '7':
                            gameObject = new Wall(new Position(x, y), Wall.Orientation.CORNER_LEFT);
                            break;

                        case '8':
                            gameObject = new Wall(new Position(x, y), Wall.Orientation.CORNER_RIGHT);
                            break;

                        case '-':
                            gameObject = new Wall(new Position(x, y), Wall.Orientation.HORIZONTAL);
                            break;

                        case '|':
                            gameObject = new Wall(new Position(x, y), Wall.Orientation.VERTICAL);
                            break;

                        case 'P':
                            gameObject = new PacMan(new Position(x, y));
                            break;

                        case 'G':
                            gameObject = new Ghost(new Position(x, y));
                            break;

                        case '.':
                            gameObject = new PacDot(new Position(x, y));
                            break;

                        case 'E':
                            gameObject = new EnergyPill(new Position(x, y));
                            break;

                        case 'F':
                            gameObject = new Fruit(new Position(x, y));
                            break;
                    }

                    if (!(gameObject instanceof Wall)) floor = new Floor(new Position(x, y));
                    if (gameObject != null) System.getGameObjectManager().getGameObjects().add(gameObject);
                    if (floor != null) graph.addVertex(floor.getVertex());
                    board.get(i).add(floor);

                    updateEdgesArena(board, i, j);
                }

                line = br.readLine();
                i++;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        arena.setBoard(board);
        Collections.sort(System.getGameObjectManager().getGameObjects());
        for (GameObject gameObject: System.getGameObjectManager().getGameObjects()) {
            System.getGraphicManager().getBoardPane().getChildren().add(createNodeOf(gameObject));
        }
    }
}
