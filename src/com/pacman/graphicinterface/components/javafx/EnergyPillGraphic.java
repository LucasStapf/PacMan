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
import javafx.scene.shape.Ellipse;

import java.io.IOException;

public class EnergyPillGraphic extends AnchorPane implements SceneElementGraphic {

    public EnergyPillGraphic() {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("EnergyPill.fxml"));
        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        color.addListener(((observable, oldValue, newValue) -> setColor(newValue)));

        setWidthValue(65);
        setHeightValue(65);
        setColor(Color.ORANGE);
    }

    private final ObjectProperty<Paint> color = new SimpleObjectProperty<>();

    public Paint getColor() {
        return color.get();
    }

    public ObjectProperty<Paint> colorProperty() {
        return color;
    }

    public void setColor(Paint color) {
        this.color.set(color);
        energypill.setFill(color);
    }

    /**
     * Representa a EnergyPill como um todo.
     */
    @FXML
    private Ellipse energypill;

    /**
     * Propriedade responsável pelo comprimento do PacDot.
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
        energypill.setRadiusX(widthValue);
    }

    /**
     * Propriedade responsável pela altura do PacDot.
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
        energypill.setRadiusY(heightValue);
    }
}
