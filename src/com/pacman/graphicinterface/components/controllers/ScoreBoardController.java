package com.pacman.graphicinterface.components.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ScoreBoardController {

    private Label scoreBoardText;

    public Label scoreBoardText() {
        return scoreBoardText;
    }

    public void setScoreBoardText(Label scoreBoardText) {
        this.scoreBoardText = scoreBoardText;
    }

    private Label scoreBoardValue;

    public Label scoreBoardValue() {
        return scoreBoardValue;
    }

    public void setScoreBoardValue(Label scoreBoardValue) {
        this.scoreBoardValue = scoreBoardValue;
    }
}
