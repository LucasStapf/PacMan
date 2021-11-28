package com.pacman.systemelements;

import com.pacman.engine.GameManager;

/**
 * Classe que representa todos os SceneElement que podem interagir entre si via colisão.
 */
public abstract class GameObject extends SceneElement {

    /**
     * HitBox do GameObject.
     * Por padrão, possui a mesma dimensão e posição do seu SceneElement.
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

    /**
     * Método que é chamado a cada frame do jogo.
     */
    public abstract void update();

    /**
     * Método que é chamado quando uma colisão é detectada.
     */
    public abstract void onCollision();

    /**
     * Retorna a HitBox do GameObject.
     * @return hitBox
     */
    public HitBox getHitBox() {
        return hitBox;
    }

    /**
     * Retorna o GameObject com o qual a colisão ocorreu.
     * @return collider
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

    /**
     * Método utilizado para destruir o atual GameObject.
     */
    public final void destroy() {
        GameManager.destroyGameObject(this);
    }

    /**
     * Verifica se o atual GameObject está no Floor passado.
     * @param floor Floor que será analisado.
     * @return true se o GameObject está no floor analisado, false caso contrário.
     */
    public boolean isOnFloor(Floor floor) {

        float x = floor.getPosition().getX();
        float y = floor.getPosition().getY();
        float[] projX = {x - (floor.getDimension().getWidth() / 2), x + (floor.getDimension().getWidth() / 2)};
        float[] projY = {y - (floor.getDimension().getHeight() / 2), y + (floor.getDimension().getHeight() / 2)};

        if (getPosition().getX() < projX[0]) return false;
        else if (getPosition().getX() > projX[1]) return false;
        else if (getPosition().getY() < projY[0]) return false;
        else if (getPosition().getY() > projY[1]) return false;

        return true;
    }

    /**
     * Verifica se o GameObject atual está centralizado no Floor passado.
     * A tolerância é de 0.1f para mais ou para menos.
     * @param floor Floor que analisádo.
     * @return true se o GameObject estiver centralizado, false caso contrário.
     */
    public boolean isCenteredOnFloor(Floor floor) {
        float deltaX = Math.abs(getPosition().getX() - floor.getPosition().getX());
        float deltaY = Math.abs(getPosition().getY() - floor.getPosition().getY());
        return (deltaX < 0.10f && deltaY < 0.10f);
    }


    /**
     * Altera a atual posição do GameObject e de sua HitBox.
     * @param position nova posição do GameObject.
     * @throws NullPointerException caso a posição passada for null.
     */
    @Override
    public void setPosition(Position position) throws NullPointerException {
        super.setPosition(position);
        getHitBox().setPosition(position);
    }

    /**
     * Altera a atual dimensão do GameObject e de sua HitBox.
     * @param dimension nova dimensão do GameObject.
     * @throws NullPointerException caso a dimensão passada for null.
     */
    @Override
    public void setDimension(Dimension dimension) throws NullPointerException {
        super.setDimension(dimension);
        getHitBox().setDimension(dimension);
    }
}
