package com.pacman.graphicinterface.components.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ScoreBoardController {

    @FXML
    public Label scoreBoardText;

    public Label scoreBoardText() {
        return scoreBoardText;
    }

    @FXML
    public Label scoreBoardValue;

    public Label scoreBoardValue() {
        return scoreBoardValue;
    }
}
