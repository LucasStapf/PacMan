package com.pacman.systemelements;

/**
 * Classe responsável por todos os objetos que compõem o tabuleiro do jogo.
 */
public abstract class SceneElement implements Comparable {

    /**
     * Dimensão do SceneElement no Tabuleiro.
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
     * Responsável por imprimir o SceneElement na tela.
     */
    public abstract void print();

    /**
     * Retorna a Dimension do SceneElement.
     * @return dimension
     */
    public Dimension getDimension() {
        return dimension;
    }

    /**
     * Retorna a Position do SceneElement.
     * @return position
     */
    public Position getPosition() {
        return position;
    }

    /**
     * Retorna a camada atual do SceneElement.
     * @return layer
     */
    public int getLayer() {
        return layer;
    }

    /**
     * Altera a dimensão atual do SceneElement.
     * @param dimension nova dimensão do SceneElement.
     * @throws NullPointerException se a dimensão for null.
     */
    public void setDimension(Dimension dimension) throws NullPointerException {
        if (dimension == null) throw new NullPointerException("Dimension cannot be null");
        this.dimension = dimension;
    }

    /**
     * Altera a posição atual do SceneElement.
     * @param position nova posição do SceneElement.
     * @throws NullPointerException se a posição for null.
     */
    public void setPosition(Position position) throws NullPointerException {
        if (position == null) throw new NullPointerException("Position cannot be null");
        this.position = position;
    }

    /**
     * Altera a camada atual do SceneElement.
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
