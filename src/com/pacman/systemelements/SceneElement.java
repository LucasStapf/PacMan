package com.pacman.systemelements;

public abstract class SceneElement implements Comparable {

    private Dimension dimension = new Dimension(2,2);
    private Position position = new Position();

    private int layer = 0;

    public abstract void print();

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


    @Override
    public int compareTo(Object o) {
        SceneElement se = (SceneElement) o;
        if (layer > se.layer) return 1;
        else if (layer == se.layer) return 0;
        else return -1;
    }
}
