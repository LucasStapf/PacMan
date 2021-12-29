package com.pacman.graphicinterface;

import com.pacman.systemelements.PacMan;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class InfoBoardController {

    @FXML
    public Label scoreLabelID;

    @FXML
    public Label lifeLabelID;

    private PacMan pacMan;

    public PacMan getPacMan() {
        return pacMan;
    }

    public void setPacMan(PacMan pacMan) {
        this.pacMan = pacMan;
    }

    public void updateInfo() {
        lifeLabelID.setText(String.valueOf(pacMan.getLife()));
        scoreLabelID.setText(String.valueOf(pacMan.getScore()));
    }
}
