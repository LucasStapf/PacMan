package com.pacman.systemelements;

import com.pacman.engine.System;

/**
 * Classe que representa todos os {@link SceneElement} que podem interagir entre si via colisão.
 */
public abstract class GameObject extends SceneElement {

    /**
     * Atributo que guarda a {@link HitBox} do GameObject.
     * Por padrão, possui a mesma dimensão e posição do seu {@link SceneElement}.
     */
    private final HitBox hitBox = new HitBox(getDimension(), getPosition());

    /**
     * GameObject com o qual ocorreu uma colisão.
     */
    private GameObject collider;

    /**
     * Atributo que registra se o GameObject é um rigido ou não. Dois GameObject rigidos são podem se sobrepor.
     */
    private boolean rigidBody;

    private Position oldPosition = getPosition();

    /**
     * Método que é chamado a cada frame do jogo.
     */
    public abstract void update();

    /**
     * Método que é chamado quando uma colisão é detectada.
     */
    public abstract void onCollision();

    /**
     * Retorna a {@link HitBox} do GameObject.
     * @return a {@link HitBox} do GameObject.
     */
    public HitBox getHitBox() {
        return hitBox;
    }

    /**
     * Retorna o GameObject com o qual a colisão ocorreu.
     * @return o GameObject com o qual a colisão ocorreu.
     */
    public GameObject getCollider() {
        return collider;
    }

    /**
     * Retorna se o corpo é rigido ou não.
     * @return true se for rigido, false caso contrário.
     */
    public boolean isRigidBody() {
        return rigidBody;
    }

    public Position getOldPosition() {
        return oldPosition;
    }

    /**
     * Altera o atual colisor.
     * @param collider novo colisor.
     */
    public void setCollider(GameObject collider) {
        this.collider = collider;
    }

    /**
     * Altera a rigidez do GameObject
     * @param rigidBody nova rigidez.
     */
    public void setRigidBody(boolean rigidBody) {
        this.rigidBody = rigidBody;
    }

    public void setOldPosition(Position position) {
        oldPosition = position;
    }

    /**
     * Método utilizado para destruir o atual GameObject.
     */
    public final void destroy() {
        System.destroyGameObject(this);
    }

    /**
     * Verifica se o atual GameObject está no {@link Floor} passado.
     * @param floor {@link Floor} que será analisado.
     * @return true se o GameObject está no {@link Floor} analisado, false caso contrário.
     */
    public boolean isOnFloor(Floor floor) {

        double x = floor.getPosition().getX();
        double y = floor.getPosition().getY();
        double[] projX = {x - (floor.getDimension().getWidth() / 2.0), x + (floor.getDimension().getWidth() / 2.0)};
        double[] projY = {y - (floor.getDimension().getHeight() / 2.0), y + (floor.getDimension().getHeight() / 2.0)};

        if (getPosition().getX() < projX[0]) return false;
        else if (getPosition().getX() > projX[1]) return false;
        else if (getPosition().getY() < projY[0]) return false;
        else if (getPosition().getY() > projY[1]) return false;

        return true;
    }

    /**
     * Verifica se o GameObject atual está centralizado no {@link Floor} passado.
     * A tolerância é de 0,1 para mais ou para menos.
     * @param floor {@link Floor} que analisádo.
     * @return true se o GameObject estiver centralizado, false caso contrário.
     */
    public boolean isCenteredOnFloor(Floor floor) {
        double fraction = 0.10;
        double deltaX = Math.abs(getPosition().getX() - floor.getPosition().getX());
        double deltaY = Math.abs(getPosition().getY() - floor.getPosition().getY());
        return (deltaX <= fraction * Floor.width && deltaY <= fraction * Floor.height);
    }

    public boolean canMakeCurve(Floor floor) {
        double porc = 0.25;
        double deltaX = Math.abs(getPosition().getX() - floor.getPosition().getX());
        double deltaY = Math.abs(getPosition().getY() - floor.getPosition().getY());
        return (deltaX <= porc * Floor.width && deltaY <= porc * Floor.height);
    }

    public void returnToOldPosition() {
        if (oldPosition != null) {
            setPosition(oldPosition);
            oldPosition = null;
        }
    }


    /**
     * Altera a atual {@link Position} do GameObject e da sua {@link HitBox}.
     * @param position nova {@link Position} do GameObject.
     * @throws NullPointerException caso a {@link Position} passada for null.
     */
    @Override
    public void setPosition(Position position) throws NullPointerException {
        oldPosition = getPosition();
        super.setPosition(position);
        getHitBox().setPosition(position);
    }

    /**
     * Altera a atual {@link Dimension} do GameObject e de sua {@link HitBox}.
     * @param dimension nova {@link Dimension} do GameObject.
     * @throws NullPointerException caso a {@link Dimension} passada for null.
     */
    @Override
    public void setDimension(Dimension dimension) throws NullPointerException {
        super.setDimension(dimension);
        getHitBox().setDimension(dimension);
    }
}
