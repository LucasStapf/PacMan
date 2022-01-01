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

import java.io.IOException;

/**
 * Representação gráfica do {@link com.pacman.systemelements.PacMan} no jogo.
 */
public class PacManGraphic extends AnchorPane {

    /**
     * Construtor padrão no qual o arquivo fxml referente ao {@link com.pacman.systemelements.PacMan} é carregado.
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

        /* -- Default Values -- */
        directionProperty().set(Direction.LEFT);
        radiusBodyProperty().set(65);
        mouthAngleProperty().set(30);
    }

    /**
     * Corpo do PacMan.
     */
    @FXML
    private Arc bodyPacMan;

    /**
     * Retorna o corpo do PacMan.
     * @return o corpo do PacMan.
     */
    public Arc getBodyPacMan() {
        return bodyPacMan;
    }

    /**
     * Altera o corpo do PacMan.
     * @param bodyPacMan novo corpo.
     */
    public void setBodyPacMan(Arc bodyPacMan) {
        this.bodyPacMan = bodyPacMan;
    }

    /**
     * Olho do {@link com.pacman.systemelements.PacMan}.
     */
    @FXML
    private Circle eyePacMan;

    /**
     * Retorna o olho do PacMan.
     * @return o olho do PacMan.
     */
    public Circle getEyePacMan() {
        return eyePacMan;
    }

    /**
     * Altera o olho do PacMan.
     * @param eyePacMan novo olho na representação de um círculo.
     */
    public void setEyePacMan(Circle eyePacMan) {
        this.eyePacMan = eyePacMan;
    }

    /**
     * Atributo relacionado ao ângulo de abertura da boca do PacMan.
     */
    private final DoubleProperty mouthAngle = new SimpleDoubleProperty();

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
        this.bodyPacMan.setStartAngle(mouthAngle);
        this.bodyPacMan.setLength((-2.0) * mouthAngle + 360.0);
    }

    /**
     * Atributo relacionado ao raio do corpo do PacMan.
     */
    private final DoubleProperty radiusBody = new SimpleDoubleProperty();

    /**
     * Retorna o valor do raio do corpo do PacMan.
     * @return o valor do raio do corpo do PacMan.
     */
    public double getRadiusBody() {
        return radiusBody.get();
    }

    /**
     * Retorna a propriedade relacionada ao raio do corpo do PacMan.
     * @return a propriedade relacionada ao raio do corpo do PacMan.
     */
    public DoubleProperty radiusBodyProperty() {
        return radiusBody;
    }

    /**
     * Altera o valor do raio do corpo do PacMan.
     * @param radiusBody novo valor do raio.
     */
    public void setRadiusBody(double radiusBody) {
        double r = radiusBody / this.radiusBody.get();

        this.radiusBody.set(radiusBody);
        this.radiusBody.set(radiusBody);
        this.bodyPacMan.setRadiusX(radiusBody);
        this.bodyPacMan.setRadiusY(radiusBody);

        this.eyePacMan.setRadius(this.eyePacMan.getRadius() * r);
        this.eyePacMan.setLayoutX(this.eyePacMan.getLayoutX() * r);
        this.eyePacMan.setLayoutY(this.eyePacMan.getLayoutY() * r);
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
    private final ObjectProperty<Direction> direction = new SimpleObjectProperty<>();

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
                setScaleX((-1)*getScaleX());
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
        this.bodyPacMan.setFill(bodyColor);
    }
}
