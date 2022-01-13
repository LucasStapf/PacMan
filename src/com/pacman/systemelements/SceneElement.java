package com.pacman.systemelements;

/**
 * Classe responsável por todos os objetos que compõem o tabuleiro do jogo.
 */
public abstract class SceneElement implements Comparable {

    /**
     * {@link Dimension} do SceneElement no Tabuleiro.
     * Valor padrão: Dimension(2,2).
     */
    private Dimension dimension = new Dimension(2,2);

    /**
     * Posição do SceneElement no Tabuleiro.
     */
    private Position position = new Position();

    /**
     * Valor da camada em que o SceneElement será desenhado.
     */
    private int layer = 0;

    /**
     * Retorna a {@link Dimension} do SceneElement.
     * @return a {@link Dimension} do SceneElement.
     */
    public Dimension getDimension() {
        return dimension;
    }

    /**
     * Retorna a {@link Position} do SceneElement.
     * @return a {@link Position} do SceneElement.
     */
    public Position getPosition() {
        return position;
    }

    /**
     * Retorna a camada atual do SceneElement.
     * @return a camada atual do SceneElement.
     */
    public int getLayer() {
        return layer;
    }

    /**
     * Altera a {@link Dimension} atual do SceneElement.
     * @param dimension nova {@link Dimension} do SceneElement.
     * @throws NullPointerException se a {@link Dimension} for null.
     */
    public void setDimension(Dimension dimension) throws NullPointerException {
        if (dimension == null) throw new NullPointerException("Dimension cannot be null");
        this.dimension = dimension;
    }

    /**
     * Altera a {@link Position} atual do SceneElement.
     * @param position nova {@link Position} do SceneElement.
     * @throws NullPointerException se a {@link Position} for null.
     */
    public void setPosition(Position position) throws NullPointerException {
        if (position == null) throw new NullPointerException("Position cannot be null");
        this.position = position;
    }

    /**
     * Altera a layer atual do SceneElement.
     * @param layer nova camada do SceneElement.
     */
    public void setLayer(int layer) {
        this.layer = layer;
    }

    /**
     * Compara as camadas dos dois SceneElement.
     * @param o SceneElement com o qual a comparação será feita.
     * @return 1 se a camada do SceneElement passado for menor,
     *         0 se a camada for igual,
     *         -1 se a camada for maior.
     */
    @Override
    public int compareTo(Object o) {
        SceneElement se = (SceneElement) o;
        return Integer.compare(layer, se.layer);
    }
}
