package com.pacman.systemelements;

/**
 * Classe que representa a posição do SceneElement no tabuleiro.
 */
public final class Position {

    /**
     * Representa a abscissa do SceneElement.
     */
    private final float x;

    /**
     * Representa a ordenada do SceneElement.
     */
    private final float y;

    /**
     * Construtor padrão. Tanto a abscissa quanto ordenadas serão inicializados como zero.
     */
    public Position() {
        this.x = 0.0f;
        this.y = 0.0f;
    }

    /**
     * Construtor com os parâmetros da abscissa e ordenadas sendo passados.
     * @param x abscissa do SceneElement.
     * @param y ordenada do SceneElement.
     */
    public Position(float x, float y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Retorna a abscissa do SceneElement.
     * @return a abscissa do SceneElement.
     */
    public float getX() {
        return x;
    }

    /**
     * Retorna a ordenada do SceneElement.
     * @return a ordenada do SceneElement.
     */
    public float getY() {
        return y;
    }


    @Override
    public String toString() {
        return "Position{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
