package com.pacman.graphicinterface.components.javafx;

import com.pacman.systemelements.Direction;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.*;

import java.io.IOException;

/**
 * Representação gráfica da entidade Ghost no jogo.
 */
public class GhostGraphic extends AnchorPane implements SceneElementGraphic {

    /**
     * Construtor padrão no qual o arquivo fxml referente a entidade Ghost é carregado.
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

        setWidthValue(102);
        setHeightValue(130);
        setDirection(Direction.RIGHT);
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
    private Ellipse leftEye;

    /**
     * Retorna o olho esquerdo do Ghost.
     * @return o olho esquerdo do Ghost.
     */
    public Ellipse leftEye() {
        return leftEye;
    }

    /**
     * Altera o olho esquerdo do Ghost.
     * @param leftEye novo olho.
     */
    public void setLeftEye(Ellipse leftEye) {
        this.leftEye = leftEye;
    }

    /**
     * Representação gráfica da pupula esquerda do Ghost.
     */
    @FXML
    private Ellipse leftPupil;

    /**
     * Retorna a pupila esquerda do Ghost.
     * @return a pupila esquerda do Ghost.
     */
    public Ellipse leftPupil() {
        return leftPupil;
    }

    /**
     * Altera a pupila esquerda do Ghost.
     * @param leftPupil nova pupila.
     */
    public void setLeftPupil(Ellipse leftPupil) {
        this.leftPupil = leftPupil;
    }

    /**
     * Representação gráfica do olho direito do Ghost.
     */
    @FXML
    private Ellipse rightEye;

    /**
     * Retorna o olho direito do Ghost.
     * @return o olho direito do Ghost.
     */
    public Ellipse rightEye() {
        return rightEye;
    }

    /**
     * Altera o olho direito do Ghost.
     * @param rightEye novo olho.
     */
    public void setRightEye(Ellipse rightEye) {
        this.rightEye = rightEye;
    }

    /**
     * Representação gráfica da pupila direita do Ghost.
     */
    @FXML
    private Ellipse rightPupil;

    /**
     * Retorna a pupila direita do Ghost.
     * @return a pupila direita do Ghost
     */
    public Ellipse rightPupil() {
        return rightPupil;
    }

    /**
     * Altera a pupila direita do Ghost.
     * @param rightPupil nova pupila.
     */
    public void setRightPupil(Ellipse rightPupil) {
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
     * Atributo relacionado à direção onde o Ghost olha.
     */
    private final ObjectProperty<Direction> direction = new SimpleObjectProperty<>(Direction.RIGHT);

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

        double rX = getWidthValue() / 102.0; // 102 valor padrão de inicialização;
        double rY = getHeightValue() / 130.0; // 130 valor padrão de inicialização;

        switch (direction) {
            case UP:
                leftPupil.setLayoutX(45 * rX);
                leftPupil.setLayoutY(55 * rY);
                rightPupil.setLayoutX(85 * rX);
                rightPupil.setLayoutY(55 * rY);
                break;

            case DOWN:
                leftPupil.setLayoutX(45 * rX);
                leftPupil.setLayoutY(45 * rY);
                rightPupil.setLayoutX(85 * rX);
                rightPupil.setLayoutY(45 * rY);
                break;

            case LEFT:
                leftPupil.setLayoutX(35 * rX);
                leftPupil.setLayoutY(50 * rY);
                rightPupil.setLayoutX(75 * rX);
                rightPupil.setLayoutY(50 * rY);
                break;

            case RIGHT:
                leftPupil.setLayoutX(45 * rX);
                leftPupil.setLayoutY(50 * rY);
                rightPupil.setLayoutX(85 * rX);
                rightPupil.setLayoutY(50 * rY);
                break;
        }
    }

    @FXML
    private MoveTo initialPoint;

    @FXML
    private ArcTo arcHead;

    @FXML
    private LineTo leftEdge;

    @FXML
    private ArcTo leftFoot;

    @FXML
    private ArcTo middleFoot;

    @FXML
    private ArcTo rightFoot;

    @FXML
    private ClosePath endEdge;

    /**
     * Propriedade relacionada ao comprimento do Ghost.
     */
    private final DoubleProperty widthValue = new SimpleDoubleProperty();

    @Override
    public double getWidthValue() {
        return widthValue.get();
    }

    public DoubleProperty widthValueProperty() {
        return widthValue;
    }

    @Override
    public void setWidthValue(double widthValue) {

        this.widthValue.set(widthValue);

        double r = widthValue / body.getLayoutBounds().getWidth();

        initialPoint.setX(initialPoint.getX() * r);

        arcHead.setRadiusX(arcHead.getRadiusX() * r);
        arcHead.setX(arcHead.getX() * r);

        leftEdge.setX(leftEdge.getX() * r);

        leftFoot.setRadiusX(leftFoot.getRadiusX() * r);
        leftFoot.setX(leftFoot.getX() * r);

        middleFoot.setRadiusX(middleFoot.getRadiusX() * r);
        middleFoot.setX(middleFoot.getX() * r);

        rightFoot.setRadiusX(rightFoot.getRadiusX() * r);
        rightFoot.setX(rightFoot.getX() * r);

        rightEye.setLayoutX(rightEye.getLayoutX() * r);
        rightEye.setRadiusX(rightEye.getRadiusX() * r);
        rightEye.setStrokeWidth(rightEye.getStrokeWidth() * r);
        rightPupil.setLayoutX(rightPupil.getLayoutX() * r);
        rightPupil.setRadiusX(rightPupil.getRadiusX() * r);
        rightPupil.setStrokeWidth(rightPupil.getStrokeWidth() * r);

        leftEye.setLayoutX(leftEye.getLayoutX() * r);
        leftEye.setRadiusX(leftEye.getRadiusX() * r);
        leftEye.setStrokeWidth(leftEye.getStrokeWidth() * r);
        leftPupil.setLayoutX(leftPupil.getLayoutX() * r);
        leftPupil.setRadiusX(leftPupil.getRadiusX() * r);
        leftPupil.setStrokeWidth(leftPupil.getStrokeWidth() * r);

        body.setLayoutX(body.getLayoutX() * r);
    }

    /**
     * Propriedade relacionada a altura do Ghost.
     */
    private final DoubleProperty heightValue = new SimpleDoubleProperty();

    @Override
    public double getHeightValue() {
        return heightValue.get();
    }

    public DoubleProperty heightValueProperty() {
        return heightValue;
    }

    @Override
    public void setHeightValue(double heightValue) {

        this.heightValue.set(heightValue);

        double r = heightValue / body.getLayoutBounds().getHeight();

        initialPoint.setY(initialPoint.getY() * r);

        arcHead.setRadiusY(arcHead.getRadiusY() * r);
        arcHead.setY(arcHead.getY() * r);

        leftEdge.setY(leftEdge.getY() * r);

        leftFoot.setRadiusY(leftFoot.getRadiusY() * r);
        leftFoot.setY(leftFoot.getY() * r);

        middleFoot.setRadiusY(middleFoot.getRadiusY() * r);
        middleFoot.setY(middleFoot.getY() * r);

        rightFoot.setRadiusY(rightFoot.getRadiusY() * r);
        rightFoot.setY(rightFoot.getY() * r);

        rightEye.setLayoutY(rightEye.getLayoutY() * r);
        rightEye.setRadiusY(rightEye.getRadiusY() * r);
        rightEye.setStrokeWidth(rightEye.getStrokeWidth() * r);
        rightPupil.setLayoutY(rightPupil.getLayoutY() * r);
        rightPupil.setRadiusY(rightPupil.getRadiusY() * r);
        rightPupil.setStrokeWidth(rightPupil.getStrokeWidth() * r);

        leftEye.setLayoutY(leftEye.getLayoutY() * r);
        leftEye.setRadiusY(leftEye.getRadiusY() * r);
        leftEye.setStrokeWidth(leftEye.getStrokeWidth() * r);
        leftPupil.setLayoutY(leftPupil.getLayoutY() * r);
        leftPupil.setRadiusY(leftPupil.getRadiusY() * r);
        leftPupil.setStrokeWidth(leftPupil.getStrokeWidth() * r);

        body.setLayoutY(body.getLayoutY() * r);
    }
}
