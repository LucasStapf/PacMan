package com.pacman.systemelements;

import com.pacman.engine.Vertex;

/**
 * Classe que representa o chão do tabuleiro.
 */
public class Floor extends SceneElement {

    /**
     * Vértice associado ao Floor.
     */
    private Vertex<Floor> vertex = new Vertex<>(this);

    /**
     * Atributo temporário para marcar o caminho.
     */
    public boolean highlighted = false;

    /**
     * Construtor padrão.
     * Por padrão, a camada (layer) do Floor é zero.
     * @param position posição em que o Floor será criado.
     */
    public Floor(Position position) {
        setLayer(0);
        setPosition(position);
    }

    /**
     * Retorna o vértice associado ao Floor.
     * @return vertex
     */
    public Vertex<Floor> getVertex() {
        return vertex;
    }

    @Override
    public void print() {
        if (highlighted) System.out.print("o");
        else System.out.print(" ");
    }

    @Override
    public String toString() {
        return "Floor{" +
                "Position=" + getPosition() +
                '}';
    }
}
