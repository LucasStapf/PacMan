package com.pacman.systemelements;

public class Dimension {

    private float width;
    private float height;

    public Dimension() {
        this.width = 0.0f;
        this.height = 0.0f;
    }

    public Dimension(float width, float height) throws IllegalArgumentException {
        setWidth(width);
        setHeight(height);
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) throws IllegalArgumentException {
        if (width < 0) throw new IllegalArgumentException("Width can not be a negative number");
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) throws IllegalArgumentException {
        if (height < 0) throw new IllegalArgumentException("Height can not be a negative number");
        this.height = height;
    }

    public void updateDimension(float width, float height) throws IllegalArgumentException {
        setWidth(width);
        setHeight(height);
    }

    @Override
    public String toString() {
        return "Dimension{" +
                "width=" + width +
                ", height=" + height +
                '}';
    }
}
