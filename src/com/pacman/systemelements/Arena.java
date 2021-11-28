package com.pacman.systemelements;

import java.util.*;

/**
 * Classe que representa a arena do jogo.
 */
public class Arena {

    /**
     * Atributo que guarda as posições de cada {@link SceneElement} no tabuleiro.
     */
    private ArrayList<ArrayList<LinkedList<SceneElement>>> board;

    /**
     * Atributo que relaciona cada {@link GameObject} do jogo a um {@link Floor}.
     */
    private HashMap<GameObject, Floor> gameObjectFloorHashMap;

    /**
     * Construtor padrão.
     */
    public Arena() {
        board = new ArrayList<>();
        gameObjectFloorHashMap = new HashMap<>();
    }

    /**
     * Método que retorna o tabuleiro do jogo.
     * @return o tabuleiro do jogo.
     */
    public ArrayList<ArrayList<LinkedList<SceneElement>>> getBoard() {
        return board;
    }

    /**
     * Método que retorna o HashMap entre {@link GameObject} e {@link Floor}.
     * @return o HashMap entre {@link GameObject} e {@link Floor}.
     */
    public HashMap<GameObject, Floor> getGameObjectFloorHashMap() {
        return gameObjectFloorHashMap;
    }

    /**
     * Método que altera o atual tabuleiro da arena.
     * @param board novo tabuleiro.
     */
    public void setBoard(ArrayList<ArrayList<LinkedList<SceneElement>>> board) {
        this.board = board;
    }

    /**
     * Método que altera o HashMap entre {@link GameObject} e {@link Floor}.
     * @param gameObjectFloorHashMap novo HashMap.
     */
    public void setGameObjectFloorHashMap(HashMap<GameObject, Floor> gameObjectFloorHashMap) {
        this.gameObjectFloorHashMap = gameObjectFloorHashMap;
    }

    /**
     * Método temporário
     */
    public void updateArena() { // temp

        Iterator iterator = gameObjectFloorHashMap.keySet().iterator();

        while (iterator.hasNext()) {

            GameObject go = (GameObject) iterator.next();
            go.update();

            float x = go.getPosition().getX();
            float y = go.getPosition().getY();

            int i = Math.round((y / 2.0f) - (1 / 2.0f));
            int j = Math.round((x / 2.0f) - (1 / 2.0f));

            Floor f = (Floor) board.get(i).get(j).getFirst();
            Floor fGameObject = gameObjectFloorHashMap.get(go);
            fGameObject.highlighted = false;

            if (!f.equals(fGameObject)) {

                int iF = Math.round(fGameObject.getPosition().getY() / 2.0f - 1 / 2.0f);
                int jF = Math.round(fGameObject.getPosition().getX() / 2.0f - 1 / 2.0f);

                board.get(iF).get(jF).remove(go);
                board.get(i).get(j).add(go);
                gameObjectFloorHashMap.replace(go, f);
                Collections.sort(board.get(i).get(j));
            }
        }
    }
}
