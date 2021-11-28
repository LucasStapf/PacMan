package com.pacman.engine;

/**
 * Classe que representa um vértice de um grafo.
 * @param <T> Tipo de dado que será relacionado ao vértice.
 */
public class Vertex<T> {

    /**
     * Tipo de dado não definido que será relacionado ao vértice.
     */
    private T t;

    /**
     * Construtor padrão.
     * @param t Tipo de dado que será relacionado ao vértice.
     */
    public Vertex(T t) {
        this.t = t;
    }

    /**
     * Método que retorna o dado relaciona ao vértice.
     * @return t
     */
    public T getT() {
        return t;
    }

    @Override
    public String toString() {
        return "Vertex{" +
                "t=" + t +
                '}';
    }
}
