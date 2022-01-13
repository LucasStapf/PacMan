package com.pacman.graphicinterface.components.javafx;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class ScoreBoardGraphic extends VBox {

    public ScoreBoardGraphic() {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("ScoreBoard.fxml"));
        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        setValue(0);
    }

    @FXML
    private Label scoreBoardText;

    private final StringProperty text = new SimpleStringProperty();

    public String getText() {
        return text.get();
    }

    public StringProperty textProperty() {
        return text;
    }

    public void setText(String text) {
        this.text.set(text);
        scoreBoardText.setText(text);
    }

    @FXML
    private Label scoreBoardValue;

    private final IntegerProperty value = new SimpleIntegerProperty(0);

    public int getValue() {
        return value.get();
    }

    public IntegerProperty valueProperty() {
        return value;
    }

    public void setValue(int value) {
        this.value.set(value);
        scoreBoardValue.setText(Integer.toString(value));
    }
}
