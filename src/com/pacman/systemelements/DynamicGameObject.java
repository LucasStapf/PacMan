package com.pacman.systemelements;

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
     * @param time tempo que o objeto irá transladar.
     */
    public void translate(float time) {

        float x = getPosition().getX();
        float y = getPosition().getY();

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
}
