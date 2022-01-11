package com.pacman.graphicinterface.components.javafx;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.io.IOException;

/**
 * Representação gráfica da entidade Wall no jogo.
 */
public class WallGraphic extends AnchorPane implements SceneElementGraphic {

    /**
     * Construtor padrão no qual o arquivo fxml referente ao GameObject Wall é carregado.
     */
    public WallGraphic() {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Wall.fxml"));
        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        setOrientation(Orientation.HORIZONTAL);
        setBorderWidth(16);
        setBorderColor(Color.BLUE);
        setWidthValue(130);
        setHeightValue(130);
    }

    /**
     * Região que representa graficamente uma parede.
     */
    @FXML
    private Region wallRegion;

    /**
     * Retorna a região que representa a parede.
     * @return a região que representa a parede.
     */
    public Region getWallRegion() {
        return wallRegion;
    }

    /**
     * Altera a região que representa a parede.
     * @param wallRegion nova região.
     */
    public void setWallRegion(Region wallRegion) {
        this.wallRegion = wallRegion;
    }

    /**
     * Constantes para identificar a orientação da parede.
     */
    public enum Orientation {

        HORIZONTAL,
        VERTICAL,
        CORNER_TOP_LEFT,
        CORNER_TOP_RIGHT,
        CORNER_BOTTOM_LEFT,
        CORNER_BOTTOM_RIGHT,
        CORNER_LEFT,
        CONER_RIGHT
    }

    /**
     * Atributo relacionado à orientação da parede.
     */
    private final ObjectProperty<Orientation> orientation = new SimpleObjectProperty<>(Orientation.HORIZONTAL);

    /**
     * Retorna a orientação da parede.
     * @return a orientação da parede.
     */
    public Orientation getOrientation() {
        return orientation.get();
    }

    /**
     * Retorna a propriedade relacionada à orientação da parede.
     * @return a propriedade relacionada à orientação da parede.
     */
    public ObjectProperty<Orientation> orientationProperty() {
        return orientation;
    }

    /**
     * Alterada a orientação da parede.
     * @param orientation nova orientação.
     */
    public void setOrientation(Orientation orientation) {

        this.orientation.set(orientation);
        String sBorderColor = "-fx-border-color: #" + getBorderColor().toString().substring(2) + "; ";
        String sBorderWidth;

        switch (orientation) {

            case HORIZONTAL:
                sBorderWidth = "-fx-border-width: " + getBorderWidth() + " " + 0 + " " + getBorderWidth() + " " + 0;
                this.wallRegion.setStyle(sBorderColor + sBorderWidth);
                break;

            case VERTICAL:
                sBorderWidth = "-fx-border-width: " + 0 + " " + getBorderWidth() + " " + 0 + " " + getBorderWidth();
                this.wallRegion.setStyle(sBorderColor + sBorderWidth);
                break;

            case CORNER_TOP_LEFT:
                sBorderWidth = "-fx-border-width: " + getBorderWidth() + " " + 0 + " " + 0 + " " + getBorderWidth();
                this.wallRegion.setStyle("-fx-border-radius: 60 0 0 0; " + sBorderColor + sBorderWidth);
                break;

            case CORNER_TOP_RIGHT:
                sBorderWidth = "-fx-border-width: " + getBorderWidth() + " " + getBorderWidth() + " " + 0 + " " + 0;
                this.wallRegion.setStyle("-fx-border-radius: 0 60 0 0; " + sBorderColor + sBorderWidth);
                break;

            case CORNER_BOTTOM_LEFT:
                sBorderWidth = "-fx-border-width: " + 0 + " " + 0 + " " + getBorderWidth() + " " + getBorderWidth();
                this.wallRegion.setStyle("-fx-border-radius: 0 0 0 60; " + sBorderColor + sBorderWidth);
                break;

            case CORNER_BOTTOM_RIGHT:
                sBorderWidth = "-fx-border-width: " + 0 + " " + getBorderWidth() + " " + getBorderWidth() + " " + 0;
                this.wallRegion.setStyle("-fx-border-radius: 0 0 60 0; " + sBorderColor + sBorderWidth);
                break;

            case CORNER_LEFT:
                sBorderWidth = "-fx-border-width: " + getBorderWidth() + " " + 0 + " " + getBorderWidth() + " " + getBorderWidth();
                this.wallRegion.setStyle("-fx-border-radius: 40 0 0 40; " + sBorderColor + sBorderWidth);
                break;

            case CONER_RIGHT:
                sBorderWidth = "-fx-border-width: " + getBorderWidth() + " " + getBorderWidth() + " " + getBorderWidth() + " " + 0;
                this.wallRegion.setStyle("-fx-border-radius: 0 40 40 0; " + sBorderColor + sBorderWidth);
                break;
        }
    }

    /**
     * Propriedade relacionada a largura da borda.
     */
    private final DoubleProperty borderWidth = new SimpleDoubleProperty();

    /**
     * Retorna a largura da borda.
     * @return a largura da borda.
     */
    public double getBorderWidth() {
        return borderWidth.get();
    }

    /**
     * Retorna a propriedade relacionada à largura da borda.
     * @return a propriedade relacionada à largura da borda.
     */
    public DoubleProperty borderWidthProperty() {
        return borderWidth;
    }

    /**
     * Alterqa o valor da largura da borda.
     * @param borderWidth nova largura.
     */
    public void setBorderWidth(double borderWidth) {
        this.borderWidth.set(borderWidth);
        setOrientation(getOrientation());
    }

    /**
     * Propriedade relacionada a cor da borda.
     */
    private final SimpleObjectProperty<Paint> borderColor = new SimpleObjectProperty<>(Color.BLUE);

    /**
     * Retorna a cor da borda.
     * @return a cor da borda.
     */
    public Paint getBorderColor() {
        return borderColor.get();
    }

    /**
     * Retorna a propriedade relacionada à cor da borda.
     * @return a propriedade relacionada à cor da borda.
     */
    public SimpleObjectProperty<Paint> borderColorProperty() {
        return borderColor;
    }

    /**
     * Altera a cor da borda.
     * @param borderColor nova cor.
     */
    public void setBorderColor(Paint borderColor) {
        this.borderColor.set(borderColor);
        setOrientation(getOrientation());
    }

    /**
     * Propriedade responsável pelo comprimento do Wall.
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
        wallRegion.setPrefWidth(widthValue);
    }

    /**
     * Propriedade responsável pela altura do Wall.
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
        wallRegion.setPrefHeight(heightValue);
    }
}
