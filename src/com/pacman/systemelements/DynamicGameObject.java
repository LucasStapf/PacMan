package com.pacman.systemelements;

import com.pacman.engine.GameSystem;

/**
 * Classe que representa todos os {@link GameObject} que podem transladar pelo tabuleiro.
 */
public abstract class DynamicGameObject extends GameObject {

    /**
     * Atributo que guarda a {@link Velocity} do DynamicGameObject.
     */
    private Velocity velocity = new Velocity();

    /**
     * Retorna a {@link Velocity} do DynamicGameObject.
     * @return a {@link Velocity} do DynamicGameObject
     */
    public Velocity getVelocity() {
        return velocity;
    }

    /**
     * Altera a atual {@link Velocity} do DynamicGameObject.
     * @param velocity nova {@link Velocity}.
     */
    public void setVelocity(Velocity velocity) {
        this.velocity = velocity;
    }

    /**
     * Método que permite o objeto transladar pelo tabuleiro por um determina período.
     * A translação move tanto o DynamicGameObject quanto sua {@link HitBox}.
     * @param time tempo que o objeto irá transladar (milissegundos).
     */
    public void translate(double time) {

        double x = getPosition().getX();
        double y = getPosition().getY();

        time = time / 1000.0;

        switch (velocity.getDirection()) {

            case UP:
                y += velocity.getModulus() * time;
                break;

            case DOWN:
                y -= velocity.getModulus() * time;
                break;

            case LEFT:
                x -= velocity.getModulus() * time;
                break;

            case RIGHT:
                x += velocity.getModulus() * time;
                break;
        }

        Position position = new Position(x, y);
        setPosition(position);
        getHitBox().setPosition(position);
    }

    public void turn(Direction direction) {

        Floor floor = Floor.getFloorFrom(this);

        double x = floor.getPosition().getX();
        double y = floor.getPosition().getY();

        if (!canMakeCurve(floor)) return;

        switch (direction) {
            case UP:
                y += Floor.height;
                break;

            case DOWN:
                y -= Floor.height;
                break;

            case RIGHT:
                x += Floor.width;
                break;

            case LEFT:
                x -= Floor.width;
                break;
        }

        if (GameSystem.arenaManager.getArena().hasFloorOn(x, y)) {
            setPosition(new Position(floor.getPosition().getX(), floor.getPosition().getY()));
            getVelocity().setDirection(direction);
        }
    }

    public void changeDirectionTo(Direction direction) {

        if (direction.equals(Direction.UP) || direction.equals(Direction.DOWN)) {
            if (getVelocity().getDirection().equals(Direction.UP) || getVelocity().getDirection().equals(Direction.DOWN)) {
                getVelocity().setDirection(direction);
                return;
            }
        }

        if (direction.equals(Direction.LEFT) || direction.equals(Direction.RIGHT)) {
            if (getVelocity().getDirection().equals(Direction.LEFT) || getVelocity().getDirection().equals(Direction.RIGHT)) {
                getVelocity().setDirection(direction);
                return;
            }
        }

        Floor floor = Floor.getFloorFrom(this);

        if (!isCenteredOnFloor(floor)) return;

        double x = floor.getPosition().getX();
        double y = floor.getPosition().getY();

        switch (direction) {
            case UP:
                y += Floor.height;
                break;

            case DOWN:
                y -= Floor.height;
                break;

            case RIGHT:
                x += Floor.width;
                break;

            case LEFT:
                x -= Floor.width;
                break;
        }

        if (GameSystem.arenaManager.getArena().hasFloorOn(x, y)) {
            setPosition(new Position(floor.getPosition().getX(), floor.getPosition().getY()));
            getVelocity().setDirection(direction);
        }
    }
}
