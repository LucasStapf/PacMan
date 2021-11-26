package com.pacman.systemelements;

import com.pacman.engine.Vertex;

public class Floor extends SceneElement {

    private Vertex<Floor> vertex = new Vertex<>(this);

    public boolean highlighted = false;

    public Floor(Position position) {
        setLayer(0);
        setPosition(position);
    }

    public Vertex<Floor> getVertex() {
        return vertex;
    }

    @Override
    public String toString() {
        return "Floor{" +
                "Position=" + getPosition() +
                '}';
    }

    @Override
    public void print() {
        if (highlighted) System.out.print("o");
        else System.out.print(" ");
    }
}
