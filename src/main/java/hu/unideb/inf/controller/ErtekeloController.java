package hu.unideb.inf.controller;

import Extensions.SceneExtentions;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class ErtekeloController {

    @FXML
    private Label kerdesLabel;

    @FXML
    private Label orvosErtekeloLabel;

    @FXML
    private TextArea erteklesTextArea;

    @FXML
    void BackToIndexButtonClicked(ActionEvent event) throws IOException {
        SceneExtentions sc = new SceneExtentions();
        sc.ChangeScene(event, "FXMLindexScene");
    }

    @FXML
    void ErtekelesElkuldesButtonCllicked(ActionEvent event) {
        
    }

}
