package com.pacman.graphicinterface.components.javafx;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Path;

import java.io.IOException;

/**
 * Representação gráfica da entidade {@link com.pacman.systemelements.Ghost} no jogo.
 */
public class GhostGraphic extends AnchorPane {

    /**
     * Construtor padrão no qual o arquivo fxml referente a entidade {@link com.pacman.systemelements.Ghost} é carregado.
     */
    public GhostGraphic() {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Ghost.fxml"));
        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Representação gráfica do corpo do Ghost.
     */
    @FXML
    private Path body;

    /**
     * Retorna o corpo do Ghost.
     * @return o corpo do Ghost.
     */
    public Path body() {
        return body;
    }

    /**
     * Altera o corpo do Ghost.
     * @param body novo corpo.
     */
    public void setBody(Path body) {
        this.body = body;
    }

    /**
     * Representação gráfica do olho esquerdo do Ghost.
     */
    @FXML
    private Circle leftEye;

    /**
     * Retorna o olho esquerdo do Ghost.
     * @return o olho esquerdo do Ghost.
     */
    public Circle leftEye() {
        return leftEye;
    }

    /**
     * Altera o olho esquerdo do Ghost.
     * @param leftEye novo olho.
     */
    public void setLeftEye(Circle leftEye) {
        this.leftEye = leftEye;
    }

    /**
     * Representação gráfica da pupula esquerda do Ghost.
     */
    @FXML
    private Circle leftPupil;

    /**
     * Retorna a pupila esquerda do Ghost.
     * @return a pupila esquerda do Ghost.
     */
    public Circle leftPupil() {
        return leftPupil;
    }

    /**
     * Altera a pupila esquerda do Ghost.
     * @param leftPupil nova pupila.
     */
    public void setLeftPupil(Circle leftPupil) {
        this.leftPupil = leftPupil;
    }

    /**
     * Representação gráfica do olho direito do Ghost.
     */
    @FXML
    private Circle rightEye;

    /**
     * Retorna o olho direito do Ghost.
     * @return o olho direito do Ghost.
     */
    public Circle rightEye() {
        return rightEye;
    }

    /**
     * Altera o olho direito do Ghost.
     * @param rightEye novo olho.
     */
    public void setRightEye(Circle rightEye) {
        this.rightEye = rightEye;
    }

    /**
     * Representação gráfica da pupila direita do Ghost.
     */
    @FXML
    private Circle rightPupil;

    /**
     * Retorna a pupila direita do Ghost.
     * @return a pupila direita do Ghost
     */
    public Circle rightPupil() {
        return rightPupil;
    }

    /**
     * Altera a pupila direita do Ghost.
     * @param rightPupil nova pupila.
     */
    public void setRightPupil(Circle rightPupil) {
        this.rightPupil = rightPupil;
    }

    /**
     * Propriedade responsável pela cor do corpo do Ghost.
     */
    private final ObjectProperty<Paint> bodyColor = new SimpleObjectProperty<>(Color.RED);

    /**
     * Retorna a atual cor do corpo do Ghost.
     * @return a atual cor do corpo do Ghost.
     */
    public Paint getBodyColor() {
        return bodyColor.get();
    }

    /**
     * Retorna a propriedade responsável pela cor do Ghost.
     * @return a propriedade responsável pela cor do Ghost.
     */
    public ObjectProperty<Paint> bodyColorProperty() {
        return bodyColor;
    }

    /**
     * Altera a cor do corpo do Ghost.
     * @param bodyColor nova cor.
     */
    public void setBodyColor(Paint bodyColor) {
        this.bodyColor.set(bodyColor);
        body.setFill(bodyColor);
    }

    /**
     * Representação das direções para as quais o PacMan está olhando.
     */
    public enum Direction {
        /**
         * Sentido positivo do eixo das ordenadas.
         */
        UP,

        /**
         * Sentido negativo do eixo das ordenadas.
         */
        DOWN,

        /**
         * Sentido positivo do eixo das abscissas.
         */
        LEFT,

        /**
         * Sentido negativo do eixo das abscissas.
         */
        RIGHT
    }

    /**
     * Atributo relacionado à direção onde o Ghost olha.
     */
    private final ObjectProperty<Direction> direction = new SimpleObjectProperty<>(Direction.LEFT);

    /**
     * Retorna a direção que o Ghost olha.
     * @return a direção que o Ghost olha.
     */
    public Direction getDirection() {
        return direction.get();
    }

    /**
     * Retorna a propriedade relacionada a direção do Ghost.
     * @return a propriedade relacionada a direção do Ghost.
     */
    public ObjectProperty<Direction> directionProperty() {
        return direction;
    }

    /**
     * Altera a direção do Ghost.
     * @param direction nova direção.
     */
    public void setDirection(Direction direction) {
        this.direction.set(direction);

        switch (direction) {
            case UP:
                leftPupil.setLayoutX(45);
                leftPupil.setLayoutY(55);
                rightPupil.setLayoutX(85);
                rightPupil.setLayoutY(55);
                break;

            case LEFT:
                leftPupil.setLayoutX(45);
                leftPupil.setLayoutY(50);
                rightPupil.setLayoutX(85);
                rightPupil.setLayoutY(50);
                break;

            case RIGHT:
                leftPupil.setLayoutX(35);
                leftPupil.setLayoutY(50);
                rightPupil.setLayoutX(75);
                rightPupil.setLayoutY(50);
                break;

            case DOWN:
                leftPupil.setLayoutX(45);
                leftPupil.setLayoutY(45);
                rightPupil.setLayoutX(85);
                rightPupil.setLayoutY(45);
                break;
        }
    }

    /**
     * Propriedade relacionada à largura do Ghost.
     */
    private final DoubleProperty widthGhost = new SimpleDoubleProperty();

    /**
     * Retorna o valor da largura do Ghost.
     * @return o valor da largura do Ghost.
     */
    public double getWidthGhost() {
        return widthGhost.get();
    }

    /**
     * Retorna a propriedade relacionada à largura do Ghost.
     * @return a propriedade relacionada à largura do Ghost.
     */
    public DoubleProperty widthGhostProperty() {
        return widthGhost;
    }

    /**
     * Altera o valor da largura do Ghost.
     * @param widthGhost nova largura.
     */
    public void setWidthGhost(double widthGhost) {
        this.widthGhost.set(widthGhost);
        double r = widthGhost / body.getLayoutBounds().getWidth();
        setScaleX(r);
    }

    /**
     * Propriedade relacionada à altura do Ghost.
     */
    private final DoubleProperty heightGhost = new SimpleDoubleProperty();

    /**
     * Retorna o valor da altura do Ghost.
     * @return o valor da altura do Ghost.
     */
    public double getHeightGhost() {
        return heightGhost.get();
    }

    /**
     * Retorna a propriedade relacionada à altura do Ghost.
     * @return a propriedade relacionada à altura do Ghost.
     */
    public DoubleProperty heightGhostProperty() {
        return heightGhost;
    }

    /**
     * Altera a altura do Ghost.
     * @param heightGhost nova altura.
     */
    public void setHeightGhost(double heightGhost) {
        this.heightGhost.set(heightGhost);
        double r = heightGhost / body.getLayoutBounds().getHeight();
        setScaleY(r);
    }
}
