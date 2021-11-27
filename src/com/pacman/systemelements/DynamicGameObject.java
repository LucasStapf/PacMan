package com.pacman.systemelements;

public abstract class DynamicGameObject extends GameObject {

    private Velocity velocity = new Velocity();

    public Velocity getVelocity() {
        return velocity;
    }

    public void setVelocity(Velocity velocity) {
        this.velocity = velocity;
    }

    public void translate(float time) {

        float x = getPosition().getX();
        float y = getPosition().getY();

        switch (velocity.getDirection()) {

            case UP:
                y += velocity.getModulus() * time;
                getPosition().setY(y);
                break;

            case DOWN:
                y -= velocity.getModulus() * time;
                getPosition().setY(y);
                break;

            case LEFT:
                x -= velocity.getModulus() * time;
                getPosition().setX(x);
                break;

            case RIGHT:
                x += velocity.getModulus() * time;
                getPosition().setX(x);
                break;
        }

        getHitBox().setPosition(getPosition());
    }
}
