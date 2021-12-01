package com.pacman.systemelements;

import com.pacman.engine.ArenaManager;
import com.pacman.engine.GameObjectManager;
import com.pacman.engine.SystemManager;

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

        int i, j;

        // temp ?
        j = (int) Math.round((x / ArenaManager.widthFloor) - (1 / 2.0));
        i = (int) Math.round((y / ArenaManager.heightFloor) - (1/ 2.0));

        Floor floor = SystemManager.getArenaManager().getArena().getGameObjectFloorHashMap().get(this);
        if (!isOnFloor(floor)) {
            floor = (Floor) SystemManager.getArenaManager().getArena().getBoard().get(i).get(j).getFirst();
            SystemManager.getArenaManager().getArena().getGameObjectFloorHashMap().replace(this, floor);
        }
        //

        Position position = new Position(x, y);
        setPosition(position);
        getHitBox().setPosition(position);
    }
}
