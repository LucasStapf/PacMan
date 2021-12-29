package com.pacman.systemelements;

import com.pacman.engine.SystemGame;
import com.pacman.engine.Vertex;

/**
 * Classe que representa o chão do tabuleiro.
 */
public class Floor extends SceneElement {

    public static final double width = 20.0;
    public static final double height = 20.0;

    /**
     * {@link Vertex} associado ao Floor.
     */
    private Vertex<Floor> vertex = new Vertex<>(this);

    /**
     * Atributo temporário para marcar o caminho.
     */
    public boolean highlighted = false;

    /**
     * Construtor padrão.
     * Por padrão, a camada (layer) do Floor é zero.
     * @param position {@link Position} em que o Floor será criado.
     */
    public Floor(Position position) {
        setLayer(0);
        setPosition(position);
        setDimension(new Dimension(Floor.width, Floor.height));
    }

    /**
     * Retorna o {@link Vertex} associado ao Floor.
     * @return o {@link Vertex} associado ao Floor.
     */
    public Vertex<Floor> getVertex() {
        return vertex;
    }

    public static Floor getFloorFrom(SceneElement sceneElement) {

        int i = (int) Math.round(sceneElement.getPosition().getY() / height - 0.5);
        int j = (int) Math.round(sceneElement.getPosition().getX() / width - 0.5);

        return SystemGame.getArenaManager().getArena().getBoard().get(i).get(j);
    }

    @Override
    public void print() {
        if (highlighted) java.lang.System.out.print("o");
        else java.lang.System.out.print(" ");
    }

    @Override
    public String toString() {
        return "Floor{" +
                "Position=" + getPosition() +
                '}';
    }
}
