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
 * Representação gráfica da entidade Floor no jogo.
 */
public class FloorGraphic extends AnchorPane implements SceneElementGraphic {

    /**
     * Construtor padrão no qual o arquivo fxml referente a entidade Floor é carregado.
     */
    public FloorGraphic() {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Floor.fxml"));
        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        setBackgroundColor(Color.GRAY);
        setBorderColor(Color.BLACK);
        setWidthValue(130);
        setHeightValue(130);
    }

    /**
     * Representação gráfica do Floor.
     */
    @FXML
    private Region floorRegion;

    /**
     * Método utilizado para atualizar o Style no formato CSS da representação gráfica do Floor.
     */
    public void updateStyle() {
        String style = sBackgroundColor + " " + sBorderColor;
        floorRegion.setStyle(style);
    }

    /**
     * Atributo responsável pelo parâmetro da cor do plano de fundo.
     */
    private String sBackgroundColor = "";

    /**
     * Propriedade responsável pela cor do fundo do Floor.
     */
    private final ObjectProperty<Paint> backgroundColor = new SimpleObjectProperty<>(Color.GRAY);

    /**
     * Retorna a cor do fundo do Floor.
     * @return a cor do fundo do Floor.
     */
    public Paint getBackgroundColor() {
        return backgroundColor.get();
    }

    /**
     * Retorna a propriedade resposável pela cor do fundo do Floor.
     * @return a propriedade resposável pela cor do fundo do Floor.
     */
    public ObjectProperty<Paint> backgroundColorProperty() {
        return backgroundColor;
    }

    /**
     * Altera a cor do fundo do Floor.
     * @param backgroundColor nova cor.
     */
    public void setBackgroundColor(Paint backgroundColor) {
        this.backgroundColor.set(backgroundColor);
        sBackgroundColor = "-fx-background-color: #" + backgroundColor.toString().substring(2) + ";";
        updateStyle();
    }

    /**
     * Atributo responsável pelo parâmetro da cor da borda do Floor.
     */
    private String sBorderColor = "";

    /**
     * Propriedade responsável pela cor da borda do Floor.
     */
    private final ObjectProperty<Paint> borderColor = new SimpleObjectProperty<>(Color.BLACK);

    /**
     * Retorna a cor da borda do Floor.
     * @return a cor da borda do Floor.
     */
    public Paint getBorderColor() {
        return borderColor.get();
    }

    /**
     * Retorna a propriedade responsável pela cor da borda do Floor.
     * @return a propriedade responsável pela cor da borda do Floor.
     */
    public ObjectProperty<Paint> borderColorProperty() {
        return borderColor;
    }

    /**
     * Altera a cor da borda do Floor.
     * @param borderColor nova cor.
     */
    public void setBorderColor(Paint borderColor) {
        this.borderColor.set(borderColor);
        sBorderColor = "-fx-border-color: #" + borderColor.toString().substring(2) + ";";
        updateStyle();
    }

    /**
     * Propriedade responsável pelo comprimento do Floor.
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
        floorRegion.setPrefWidth(widthValue);
    }

    /**
     * Propriedade responsável pela altura do Floor.
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
        floorRegion.setPrefHeight(heightValue);
    }
}
