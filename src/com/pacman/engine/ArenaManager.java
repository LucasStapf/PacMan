package com.pacman.engine;

import com.pacman.graphicinterface.GameObjectController;
import com.pacman.graphicinterface.PacManController;
import com.pacman.graphicinterface.WallController;
import com.pacman.systemelements.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

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

    public static final double widthFloor = 20.0;
    public static final double heightFloor = 20.0;

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
        SystemManager.getGameObjectManager().getGameObjectController().put(gameObject, gameObjectController);

        gameObjectController.setGameObject(gameObject);

        double x = GraphicManager.convertGameToScreenX(gameObject);
        gameObjectController.getGameObjectRectangle().setTranslateX(x);

        double y = GraphicManager.convertGameToScreenY(gameObject);
        gameObjectController.getGameObjectRectangle().setTranslateY(y);

        gameObjectController.getGameObjectRectangle().setWidth(gameObject.getDimension().getWidth());
        gameObjectController.getGameObjectRectangle().setHeight(gameObject.getDimension().getHeight());

        return node;
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

                    SceneElement sceneElement;

//                    double x = (widthFloor / 2.0) + (j * widthFloor);
//                    double y = (heightFloor / 2.0) + (i * heightFloor);

                    double x = (widthFloor / 2.0) + (j * widthFloor);
                    double y = (heightFloor / 2.0) + (i * heightFloor);

                    switch (line.charAt(j)) {

                        case '+':
                            Wall wallCorner =  new Wall(new Position(x, y), Wall.Orientation.CORNER);
                            wallCorner.setDimension(new Dimension(widthFloor,heightFloor));
                            board.get(i).get(j).add(wallCorner);
                            SystemManager.getGameObjectManager().getGameObjects().add(wallCorner);
                            GraphicManager.root.getChildren().add(createNodeOf(wallCorner));
                            break;

                        case '-':
                            Wall wallHorizontal =  new Wall(new Position(x, y), Wall.Orientation.HORIZONTAL);
                            wallHorizontal.setDimension(new Dimension(widthFloor,heightFloor));
                            board.get(i).get(j).add(wallHorizontal);
                            SystemManager.getGameObjectManager().getGameObjects().add(wallHorizontal);
                            GraphicManager.root.getChildren().add(createNodeOf(wallHorizontal));
                            break;

                        case '|':
                            Wall wallVertical =  new Wall(new Position(x, y), Wall.Orientation.VERTICAL);
                            wallVertical.setDimension(new Dimension(widthFloor,heightFloor));
                            board.get(i).get(j).add(wallVertical);
                            SystemManager.getGameObjectManager().getGameObjects().add(wallVertical);
                            GraphicManager.root.getChildren().add(createNodeOf(wallVertical));
                            break;

                        case '.':
                            Floor fPacDot = new Floor(new Position(x, y));
                            board.get(i).get(j).add(fPacDot);
                            graph.addVertex(fPacDot.getVertex());

//                            PacDot pacDot = new PacDot(new Position(x, y));
//                            pacDot.setDimension(new Dimension(0.5f,0.5f));
//                            board.get(i).get(j).add(pacDot);
//                            gameObjectFloorHashMap.put(pacDot, fPacDot);
//
//                            SystemManager.getGameObjectManager().getGameObjects().add(pacDot);
                            break;

                        case 'E':
                            Floor fEnergyPill = new Floor(new Position(x, y));
                            board.get(i).get(j).add(fEnergyPill);
                            graph.addVertex(fEnergyPill.getVertex());

                            EnergyPill energyPill = new EnergyPill(new Position(x, y));
//                            energyPill.setDimension(new Dimension(1,1));
                            board.get(i).get(j).add(energyPill);
                            gameObjectFloorHashMap.put(energyPill, fEnergyPill);

                            SystemManager.getGameObjectManager().getGameObjects().add(energyPill);
                            break;

                        case 'F':
                            Floor fFruit = new Floor(new Position(x, y));
                            board.get(i).get(j).add(fFruit);
                            graph.addVertex(fFruit.getVertex());

                            Fruit fruit = new Fruit(new Position(x, y));
                            fruit.setDimension(new Dimension(0.5f,0.5f));
                            board.get(i).get(j).add(fruit);
                            gameObjectFloorHashMap.put(fruit, fFruit);

                            SystemManager.getGameObjectManager().getGameObjects().add(fruit);
                            break;

                        case 'P':
                            Floor fPac = new Floor(new Position(x, y));
                            board.get(i).get(j).add(fPac);
                            graph.addVertex(fPac.getVertex());

                            PacMan pacMan = new PacMan(new Position(x, y));
                            pacMan.setDimension(new Dimension(18,18));
                            pacMan.getVelocity().updateVelocity(0, Velocity.Direction.NONE);
                            board.get(i).get(j).add(pacMan);
                            gameObjectFloorHashMap.put(pacMan, fPac);

                            SystemManager.getGameObjectManager().getGameObjects().add(pacMan);
                            GraphicManager.root.getChildren().add(createNodeOf(pacMan));
                            break;

                        case 'G':
                            Floor fGhost = new Floor(new Position(x, y));
                            board.get(i).get(j).add(fGhost);
                            graph.addVertex(fGhost.getVertex());

                            Ghost ghost = new Ghost(new Position(x, y));
                            ghost.setDimension(new Dimension(18f,18f));
                            ghost.getVelocity().updateVelocity(50, Velocity.Direction.NONE);
                            ghost.setMovement(Ghost.Movement.FOLLOW_TARGET);
                            board.get(i).get(j).add(ghost);
                            gameObjectFloorHashMap.put(ghost, fGhost);

                            SystemManager.getGameObjectManager().getGameObjects().add(ghost);
                            GraphicManager.root.getChildren().add(createNodeOf(ghost));
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
