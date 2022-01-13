package com.pacman.graphicinterface.components.javafx;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.CubicCurve;
import javafx.scene.shape.Ellipse;

import java.io.IOException;

public class FruitGraphic extends AnchorPane implements SceneElementGraphic {

    public FruitGraphic() {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Fruit.fxml"));
        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        setWidthValue(177);
        setHeightValue(183);
        setColorBody(Color.ORANGE);
    }

    private final ObjectProperty<Paint> colorBody = new SimpleObjectProperty<>();

    public Paint getColorBody() {
        return colorBody.get();
    }

    public ObjectProperty<Paint> colorBodyProperty() {
        return colorBody;
    }

    public void setColorBody(Paint colorBody) {
        this.colorBody.set(colorBody);
        body.setFill(colorBody);
    }

    @FXML
    private Group group;

    @FXML
    private Ellipse body;

    @FXML
    private CubicCurve leafOne;

    @FXML
    private CubicCurve leafTwo;

    /**
     * Propriedade responsável pelo comprimento do Fruit.
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

        double r = widthValue / group.getLayoutBounds().getWidth();

        body.setRadiusX(body.getRadiusX() * r);
        body.setLayoutX(body.getLayoutX() * r);

        leafOne.setLayoutX(leafOne.getLayoutX() * r);
        leafOne.setStartX(leafOne.getStartX() * r);
        leafOne.setEndX(leafOne.getEndX() * r);
        leafOne.setControlX1(leafOne.getControlX1() * r);
        leafOne.setControlX2(leafOne.getControlX2() * r);

        leafTwo.setLayoutX(leafTwo.getLayoutX() * r);
        leafTwo.setStartX(leafTwo.getStartX() * r);
        leafTwo.setEndX(leafTwo.getEndX() * r);
        leafTwo.setControlX1(leafTwo.getControlX1() * r);
        leafTwo.setControlX2(leafTwo.getControlX2() * r);
    }

    /**
     * Propriedade responsável pela altura do Fruit.
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

        double r = heightValue / group.getLayoutBounds().getHeight();

        body.setRadiusY(body.getRadiusY() * r);
        body.setLayoutY(body.getLayoutY() * r);

        leafOne.setLayoutY(leafOne.getLayoutY() * r);
        leafOne.setStartY(leafOne.getStartY() * r);
        leafOne.setEndY(leafOne.getEndY() * r);
        leafOne.setControlY1(leafOne.getControlY1() * r);
        leafOne.setControlY2(leafOne.getControlY2() * r);

        leafTwo.setLayoutY(leafTwo.getLayoutY() * r);
        leafTwo.setStartY(leafTwo.getStartY() * r);
        leafTwo.setEndY(leafTwo.getEndY() * r);
        leafTwo.setControlY1(leafTwo.getControlY1() * r);
        leafTwo.setControlY2(leafTwo.getControlY2() * r);
    }
}
