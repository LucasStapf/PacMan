package com.pacman.systemelements;

public class SceneElement {

    private Dimension dimension = new Dimension();
    private Position position = new Position();

    private int layer;

    public Dimension getDimension() {
        return dimension;
    }

    public Position getPosition() {
        return position;
    }

    public int getLayer() {
        return layer;
    }

    public void setDimension(Dimension dimension) throws NullPointerException {
        if (dimension == null) throw new NullPointerException("Dimension cannot be null");
        this.dimension = dimension;
    }

    public void setPosition(Position position) throws NullPointerException {
        if (position == null) throw new NullPointerException("Position cannot be null");
        this.position = position;
    }

    public void setLayer(int layer) {
        this.layer = layer;
    }
}
