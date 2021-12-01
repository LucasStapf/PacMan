package com.pacman.systemelements;

/**
 * Classe que representa a posição do {@link SceneElement} no tabuleiro.
 */
public final class Position {

    /**
     * Representa a abscissa do {@link SceneElement}.
     */
    private final double x;

    /**
     * Representa a ordenada do {@link SceneElement}.
     */
    private final double y;

    /**
     * Construtor padrão. Tanto a abscissa quanto ordenadas serão inicializados como zero.
     */
    public Position() {
        this.x = 0.0;
        this.y = 0.0;
    }

    /**
     * Construtor com os parâmetros da abscissa e ordenadas sendo passados.
     * @param x abscissa do {@link SceneElement}.
     * @param y ordenada do {@link SceneElement}.
     */
    public Position(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Retorna a abscissa do {@link SceneElement}.
     * @return a abscissa do {@link SceneElement}.
     */
    public double getX() {
        return x;
    }

    /**
     * Retorna a ordenada do {@link SceneElement}.
     * @return a ordenada do {@link SceneElement}.
     */
    public double getY() {
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
