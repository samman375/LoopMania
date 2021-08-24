package unsw.loopmania.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class GameOverScreenController {

    @FXML
    private Button exitButton;

    @FXML
    void handleExitGame(ActionEvent event) {
        System.exit(0);
    }

}
