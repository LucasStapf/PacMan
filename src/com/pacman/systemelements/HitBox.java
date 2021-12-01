package com.pacman.systemelements;

/**
 * Classe que representa uma caixa virtual que é utilizada na detecção de colisões.
 */
public final class HitBox {

    /**
     * Atributo responsável pela dimensão da HitBox.
     */
    private Dimension dimension;

    /**
     * Atributo responsável pela posição da HitBox.
     */
    private Position position;

    /**
     * Atributo responsável por guardar os extremos da projeção da base da HitBox no eixo das abscissas.
     */
    private final double[] projectionX = new double[2];

    /**
     * Atributo responsável por guardar os extremos da projeção da altura da HitBox no eixo das coordenadas.
     */
    private final double[] projectionY = new double[2];

    /**
     * Construtor padrão.
     * @param dimension dimensão da HitBox.
     * @param position posição da HitBox.
     */
    public HitBox(Dimension dimension, Position position) {
        this.position = position;
        this.dimension = dimension;
        updateProjections();
    }

    /**
     * Retorna a largura da HitBox.
     * @return a largura da HitBox.
     */
    public double getWidth() {
        return this.dimension.getWidth();
    }

    /**
     * Retorna a altura da HitBox.
     * @return a altura da HitBox.
     */
    public double getHeight() {
        return this.dimension.getHeight();
    }

    /**
     * Retorna a abscissa da HitBox.
     * @return a abscissa da HitBox.
     */
    public double getX() {
        return this.position.getX();
    }

    /**
     * Retorna a ordenada da HitBox.
     * @return a ordenada da HitBox.
     */
    public double getY() {
        return this.position.getY();
    }

    /**
     * Altera a dimensão atual da HitBox e atualiza as projeções.
     * @param dimension nova dimensão.
     */
    public void setDimension(Dimension dimension) {
        this.dimension = dimension;
        updateProjections();
    }

    /**
     * Altera a posição atual da HitBox e atualiza as projeções.
     * @param position nova posição.
     */
    public void setPosition(Position position) {
        this.position = position;
        updateProjections();
    }

    /**
     * Atualiza as projeções com base na posição e dimensão da HitBox.
     */
    public void updateProjections() {

        this.projectionX[0] = position.getX() - dimension.getWidth() / 2;
        this.projectionX[1] = position.getX() + dimension.getWidth() / 2;

        this.projectionY[0] = position.getY() - dimension.getHeight() / 2;
        this.projectionY[1] = position.getY() + dimension.getHeight() / 2;
    }

    /**
     * Verifica se a atual HitBox possui intersecção com a HitBox passada.
     * @param hitBox que será utilizada na verificação.
     * @return true se houver interseção, false caso contrário.
     */
    public boolean hasIntersection(HitBox hitBox) {

        if (this.projectionX[1] < hitBox.projectionX[0]) return false;
        else if (this.projectionX[0] > hitBox.projectionX[1]) return false;
        else if (this.projectionY[1] < hitBox.projectionY[0]) return false;
        else if (this.projectionY[0] > hitBox.projectionY[1]) return false;

        return true;
    }

    @Override
    public String toString() {
        return "HitBox{" +
                "position=" + position +
                ", dimension=" + dimension +
                '}';
    }
}
