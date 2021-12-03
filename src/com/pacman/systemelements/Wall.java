package com.pacman.systemelements;

/**
 * Classe que representa uma parede no jogo.
 */
public class Wall extends GameObject {

    /**
     * Constantes que representam a orientação do Wall.
     */
    public enum Orientation {
        HORIZONTAL,
        VERTICAL,
        CORNER,
        CORNER_TOP_LEFT,
        CORNER_TOP_RIGHT,
        CORNER_BUTTON_LEFT,
        CORNER_BUTTON_RIGHT;
    }

    /**
     * Atributo que guarda a orientação do Wall.
     */
    public Orientation orientation;

    /**
     * Construtor Padrão.
     * Por padrão, a camada do Wall é 1 (layer = 1) e é um corpo rígido (rigidBody = true).
     * @param position {@link Position} onde será criada a parede.
     * @param orientation orientação da parede.
     */
    public Wall(Position position, Orientation orientation) {
        setLayer(1);
        setRigidBody(true);
        setPosition(position);
        setOldPosition(position);
        setDimension(new Dimension(Floor.width, Floor.height));
        this.orientation = orientation;
    }

    @Override
    public void update() {

    }

    @Override
    public void onCollision() {

    }

    @Override
    public void print() {
        switch (orientation) {
            case HORIZONTAL:
                System.out.print("-");
                break;
            case VERTICAL:
                System.out.print("|");
                break;
            case CORNER:
                System.out.print("+");
                break;
        }
    }

}
