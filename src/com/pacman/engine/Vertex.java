package com.pacman.engine;

public class Vertex<T> {

    private T t;

    public Vertex(T t) {
        this.t = t;
    }

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
