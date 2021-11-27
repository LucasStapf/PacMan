package com.pacman.systemelements;

public final class HitBox {

    private Dimension dimension;
    private Position position;

    private final float[] projectionX = new float[2];
    private final float[] projectionY = new float[2];

//    public HitBox() {
//        this.position = new Position();
//        this.dimension = new Dimension();
//        updateProjections();
//    }

    public HitBox(Dimension dimension, Position position) {
        this.position = position;
        this.dimension = dimension;
        updateProjections();
    }

//    public HitBox(float width, float height, float x, float y) {
//        this.position = new Position(x, y);
//        this.dimension = new Dimension(width, height);
//        updateProjections();
//    }

    public float getWidth() {
        return this.dimension.getWidth();
    }

    public float getHeight() {
        return this.dimension.getHeight();
    }

//    public float getX() {
//        return this.position.getX();
//    }
//
//    public float getY() {
//        return this.position.getY();
//    }

    public void setDimension(Dimension dimension) {
        this.dimension = dimension;
        updateProjections();
    }

    public void setPosition(Position position) {
        this.position = position;
        updateProjections();
    }

//    public void setWidth(float width) throws IllegalArgumentException {
//        this.dimension.setWidth(width);
//        updateProjections();
//    }
//
//    public void setHeight(float height) throws IllegalArgumentException {
//        this.dimension.setHeight(height);
//        updateProjections();
//    }

//    public void setX(float x) {
//        this.position.setX(x);
//        updateProjections();
//    }
//
//    public void setY(float y) {
//        this.position.setY(y);
//        updateProjections();
//    }

    public void updateProjections() {

        this.projectionX[0] = position.getX() - dimension.getWidth() / 2;
        this.projectionX[1] = position.getX() + dimension.getWidth() / 2;

        this.projectionY[0] = position.getY() - dimension.getHeight() / 2;
        this.projectionY[1] = position.getY() + dimension.getHeight() / 2;
    }

    //    public void updateDimension(float width, float height) throws IllegalArgumentException {
    //        setWidth(width);
    //        setHeight(height);
    //    }

//    public void updatePosition(float x, float y) {
//        setX(x);
//        setY(y);
//    }

    public boolean hasIntersection(HitBox hitBox) {

        if (this.projectionX[1] < hitBox.projectionX[0]) return false;
        else if (this.projectionX[0] > hitBox.projectionX[1]) return false;
        else if (this.projectionY[1] < hitBox.projectionY[0]) return false;
        else if (this.projectionY[0] > hitBox.projectionY[1]) return false;

        return true;
    }

    @Override
    public String toString() {
        return "HitBox{" +
                "position=" + position +
                ", dimension=" + dimension +
                '}';
    }
}
