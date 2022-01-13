package com.pacman.graphicinterface.components.javafx;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;

import java.io.IOException;

public class LifeBoardGraphic extends HBox {

    public LifeBoardGraphic() {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("LifeBoard.fxml"));
        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        setLifes(3);
    }

    private final IntegerProperty lifes = new SimpleIntegerProperty();

    public int getLifes() {
        return lifes.get();
    }

    public IntegerProperty lifesProperty() {
        return lifes;
    }

    public void setLifes(int lifes) {
        this.lifes.set(lifes);
        getChildren().clear();
        for (int i = 0; i < lifes; i++) {
            PacManGraphic pacManGraphic = new PacManGraphic();
            pacManGraphic.setWidthValue(20);
            pacManGraphic.setHeightValue(20);
            getChildren().add(pacManGraphic);
        }
    }
}
