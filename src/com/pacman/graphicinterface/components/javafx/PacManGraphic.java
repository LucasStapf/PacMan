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
import javafx.scene.shape.Arc;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;

import java.io.IOException;

/**
 * Representação gráfica da entidade PacMan no jogo.
 */
public class PacManGraphic extends AnchorPane implements SceneElementGraphic {

    /**
     * Construtor padrão no qual o arquivo fxml referente a entidade PacMan é carregado.
     */
    public PacManGraphic() {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("PacMan.fxml"));
        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        /* ---- Default Values ---- */
        setHeightValue(130);
        setWidthValue(130);
        setDirection(Direction.LEFT);
        setBodyColor(Color.YELLOW);
        /* ------------------------ */
    }

    /**
     * Representação do corpo do PacMan por meio de um {@link Arc}.
     */
    @FXML
    private Arc body;

    /**
     * Retorna o {@link Arc} que representa o corpo do PacMan.
     * @return o {@link Arc} que representa o corpo do PacMan.
     */
    public Arc getBody() {
        return body;
    }

    /**
     * Altera o corpo do PacMan.
     * @param body novo corpo na representação de um arco.
     */
    public void setBody(Arc body) {
        this.body = body;
    }

    /**
     * Representação do olho do PacMan por um {@link Circle}.
     */
    @FXML
    private Ellipse eye;

    /**
     * Retorna o olho do PacMan.
     * @return o olho do PacMan.
     */
    public Ellipse getEye() {
        return eye;
    }

    /**
     * Altera o olho do PacMan.
     * @param eye novo olho.
     */
    public void setEye(Ellipse eye) {
        this.eye = eye;
    }

    /**
     * Atributo relacionado ao ângulo de abertura da boca do PacMan.
     */
    private final DoubleProperty mouthAngle = new SimpleDoubleProperty(30);

    /**
     * Retorna o valor do ângulo de abertura da boca do PacMan.
     * @return o valor do ângulo de abertura da boca do PacMan.
     */
    public double getMouthAngle() {
        return mouthAngle.get();
    }

    /**
     * Retorna a propriedade relacionada ao ângulo de abertura da boca do PacMan.
     * @return a propriedade relacionada ao ângulo de abertura da boca do PacMan.
     */
    public DoubleProperty mouthAngleProperty() {
        return mouthAngle;
    }

    /**
     * Altera o ângulo de abertura da boca do PacMan.
     * @param mouthAngle novo ângulo de abertura da boca.
     */
    public void setMouthAngle(double mouthAngle) {
        this.mouthAngle.set(mouthAngle);
        this.body.setStartAngle(mouthAngle);
        this.body.setLength((-2.0) * mouthAngle + 360.0);
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
     * Atributo relacionado à direção onde o PacMan olha.
     */
    private final ObjectProperty<Direction> direction = new SimpleObjectProperty<>(Direction.LEFT);

    /**
     * Retorna a direção que o PacMan olha.
     * @return a direção que o PacMan olha.
     */
    public Direction getDirection() {
        return direction.get();
    }

    /**
     * Retorna a propriedade relacionada a direção do PacMan.
     * @return a propriedade relacionada a direção do PacMan.
     */
    public ObjectProperty<Direction> directionProperty() {
        return direction;
    }

    /**
     * Altera a direção do PacMan.
     * @param direction nova direção.
     */
    public void setDirection(Direction direction) {
        this.direction.set(direction);

        switch (direction) {
            case UP:
                setRotate(90);
                break;

            case LEFT:
                setRotate(0);
                break;

            case RIGHT:
                double r = getScaleX() > 0 ? -1 : 1;
                setScaleX(r * getScaleX());
                break;

            case DOWN:
                setRotate(-90);
                break;
        }
    }

    /**
     * Propriedade responsável pela cor do corpo do PacMan.
     */
    private final ObjectProperty<Paint> bodyColor = new SimpleObjectProperty<>(Color.YELLOW);

    /**
     * Retorna a cor do corpo do PacMan.
     * @return a cor do corpo do PacMan.
     */
    public Paint getBodyColor() {
        return bodyColor.get();
    }

    /**
     * Retorna a propriedade relacionada à cor do corpo do PacMan.
     * @return a propriedade relacionada à cor do corpo do PacMan.
     */
    public ObjectProperty<Paint> bodyColorProperty() {
        return bodyColor;
    }

    /**
     * Altera a cor do corpo do PacMan.
     * @param bodyColor nova cor.
     */
    public void setBodyColor(Paint bodyColor) {
        this.bodyColor.set(bodyColor);
        this.body.setFill(bodyColor);
    }


    /**
     * Propriedade responsável pelo comprimento do PacMan.
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

        body.setRadiusX(body.getRadiusX() * r);
        body.setLayoutX(body.getLayoutX() * r);
        eye.setRadiusX(eye.getRadiusX() * r);
        eye.setLayoutX(eye.getLayoutX() * r);
    }

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

        body.setRadiusY(body.getRadiusY() * r);
        body.setLayoutY(body.getLayoutY() * r);
        eye.setRadiusY(eye.getRadiusY() * r);
        eye.setLayoutY(eye.getLayoutY() * r);
    }
}
