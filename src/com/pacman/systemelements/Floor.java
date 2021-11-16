package com.pacman.systemelements;

import com.pacman.engine.Vertex;

public class Floor extends SceneElement {

    private Vertex<Floor> vertex = new Vertex<>(this);

    public Floor(Position position) {
        setPosition(position);
    }

    public Vertex<Floor> getVertex() {
        return vertex;
    }
}
