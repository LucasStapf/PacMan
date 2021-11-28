package com.pacman.systemelements;

/**
 * Classe que representa as dimensões de um {@link SceneElement}.
 */
public final class Dimension {

    /**
     * Largura do {@link SceneElement}.
     */
    private final float width;

    /**
     * Altura do {@link SceneElement}.
     */
    private final float height;

    /**
     * Construtor padrão. Os valores de altura e largura serão ambos inicializados com 0.0f.
     */
    public Dimension() {
        this.width = 0.0f;
        this.height = 0.0f;
    }

    /**
     * Construtor onde os valores das dimensões são passadas.
     * @param width largura do {@link SceneElement}.
     * @param height altura do {@link SceneElement}.
     * @throws IllegalArgumentException caso algum dos parâmetros passados seja menor que zero.
     */
    public Dimension(float width, float height) throws IllegalArgumentException {
        if (width < 0) throw new IllegalArgumentException("Width can not be a negative number");
        if (height < 0) throw new IllegalArgumentException("Height can not be a negative number");
        this.width = width;
        this.height = height;
    }

    /**
     * Retorna a largura do {@link SceneElement}.
     * @return a largura do {@link SceneElement}.
     */
    public float getWidth() {
        return width;
    }

    /**
     * Retorna a altura do {@link SceneElement}.
     * @return a altura do {@link SceneElement}.
     */
    public float getHeight() {
        return height;
    }

    @Override
    public String toString() {
        return "Dimension{" +
                "width=" + width +
                ", height=" + height +
                '}';
    }
}
