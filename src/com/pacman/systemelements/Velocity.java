package com.pacman.systemelements;

/**
 * Classe que representa a velocidade de um {@link DynamicGameObject}.
 */
public final class Velocity {

    /**
     * Representa a direção da velocidade.
     */
    public enum Direction {
        UP,
        DOWN,
        LEFT,
        RIGHT,
        NONE;
    }

    /**
     * Indica o módulo da velocidade.
     */
    private double modulus;

    /**
     * Guarda a direção da velocidade.
     */
    private Direction direction;

    /**
     * Construtor padrão. Velocidade possui módulo zero e não tem direção definida.
     */
    public Velocity() {
        this.modulus = 0.0;
        this.direction = Direction.NONE;
    }

    /**
     * Construtor onde são passadas os parâmetros da velocidade.
     * @param modulus Módulo da velocidade. Não pode ser negativo.
     * @param direction direção da velocidade.
     * @throws IllegalArgumentException caso a valor do módulo passado seja menor que zero.
     */
    public Velocity(double modulus, Direction direction) throws IllegalArgumentException {
        if (modulus < 0) throw new IllegalArgumentException("Modulos must be a positive number");
        this.modulus = modulus;
        this.direction = direction;
    }

    /**
     * Retorna o módulo da velocidade.
     * @return modulus
     */
    public double getModulus() {
        return modulus;
    }

    /**
     * Retorna a direção da velocidade.
     * @return direction
     */
    public Direction getDirection() {
        return direction;
    }

    /**
     * Altera o valor do módulo da velocidade.
     * @param modulus Novo valor do módulo. Deve ser não-negativo.
     * @throws IllegalArgumentException caso o valor passado seja negativo.
     */
    public void setModulus(double modulus) throws IllegalArgumentException {
        if (modulus < 0) throw new IllegalArgumentException(); // msg
        this.modulus = modulus;
    }

    /**
     * Altera a direção da velocidade.
     * @param direction nova direção da velocidade.
     */
    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    /**
     * Altera tanto módulo quanto direção da velocidade.
     * @param modulus novo valor do módulo. Deve ser não-negativo.
     * @param direction nova direção.
     * @throws IllegalArgumentException caso o valor do módulo passado seja negativo.
     */
    public void updateVelocity(float modulus, Direction direction) throws IllegalArgumentException {
        setModulus(modulus);
        setDirection(direction);
    }

    /**
     * Verifica se a direção é horizontal ou não.
     * Direções horizontais: RIGHT e LEFT.
     * @param velocity velocidade a ser analisada. Não pode ser null.
     * @return true caso seja horizontal, false caso contrário.
     * @throws NullPointerException caso a velocidade passada seja null.
     */
    public static boolean isHorizontal(Velocity velocity) throws NullPointerException {
        if (velocity == null) throw new NullPointerException();
        return (velocity.direction.equals(Direction.RIGHT) || velocity.direction.equals(Direction.LEFT));
    }

    /**
     * Verifica se a direção é vertical ou não.
     * Direções verticais: UP e DOWN.
     * @param velocity velocidade a ser analisada. Não pode ser null.
     * @return true caso seja vertical, false caso contrário.
     * @throws NullPointerException caso a velocidade passada seja null.
     */
    public static boolean isVertical(Velocity velocity) throws NullPointerException {
        if (velocity == null) throw new NullPointerException();
        return (velocity.direction.equals(Direction.UP) || velocity.direction.equals(Direction.DOWN));
    }

    /**
     * Verifica se as duas velocidades pssuem a mesma direção.
     * @param v1 primeira velocidade. Não pode ser null.
     * @param v2 segunda velocidade. Não pode ser null.
     * @return true caso possuam mesma direção (ou uma não possuir direção definida), false caso contrário.
     * @throws NullPointerException caso uma ou mais velocidades sejam null.
     */
    public static boolean isSameDirection(Velocity v1, Velocity v2) throws NullPointerException {
        if (v1 == null || v2 == null) throw new NullPointerException();
        return v1.direction.equals(v2.direction);
    }

    /**
     * Verifica se as duas velocidade possuem direção oposta.
     * @param v1 primeira velocidade. Não pode ser null.
     * @param v2 segunda velocidade. Não pode ser null.
     * @return true caso possuam direção oposta (ou um não possuir direção definida), false caso contrário.
     * @throws NullPointerException caso uma ou mais velocidades sejam null.
     */
    public static boolean isOppositeDirection(Velocity v1, Velocity v2) throws NullPointerException {

        if (v1 == null || v2 == null) throw new NullPointerException();

        if (v1.direction.equals(Direction.UP)) {
            if (v2.direction.equals(Direction.DOWN)) return true;
            else return false;
        }

        if (v1.direction.equals(Direction.DOWN)) {
            if (v2.direction.equals(Direction.UP)) return true;
            else return false;
        }

        if (v1.direction.equals(Direction.LEFT)) {
            if (v2.direction.equals((Direction.RIGHT))) return true;
            else return false;
        }

        if (v1.direction.equals(Direction.RIGHT)) {
            if (v2.direction.equals(Direction.LEFT)) return true;
            else return false;
        }

        return true;
    }

    /**
     * Verifica se duas velocidade são perpendiculares ou não.
     * @param v1 primeira velocidade. Não pode ser null.
     * @param v2 segunda velocidade. Não pode ser null.
     * @return true caso sejam perpediculares, false caso contrário.
     * @throws NullPointerException caso uma das velocidades seja null.
     */
    public static boolean isPerpendicularDirection(Velocity v1, Velocity v2) throws NullPointerException {
        if (v1 == null || v2 == null) throw new NullPointerException();
        if (v1.direction.equals(Direction.NONE) || v2.direction.equals(Direction.NONE)) return false;
        else if (isSameDirection(v1, v2)) return false;
        else if (isOppositeDirection(v1, v2)) return false;
        else return true;
    }

//    public static int sign(Velocity velocity) throws NullPointerException {
//        if (velocity == null) throw new NullPointerException();
//        if (velocity.direction.equals(Direction.UP) || velocity.direction.equals(Direction.RIGHT)) return 1;
//        else if (velocity.direction.equals(Direction.DOWN) || velocity.direction.equals(Direction.LEFT)) return -1;
//        else return 0;
//    }

    @Override
    public String toString() {
        return "Velocity{" +
                "module=" + modulus +
                ", direction=" + direction +
                '}';
    }
}
