/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.unideb.inf.controller;

import Extensions.SceneExtentions;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
/**
 * FXML Controller class
 *
 * @author andra
 */

public class FXMLLoginSceneController implements Initializable{

    @FXML
    private TextField textemail;

    @FXML
    private PasswordField textjelszo;

    @FXML
    void handleBelepesButtonPressed(ActionEvent event) throws IOException {
        SceneExtentions sc = new SceneExtentions();
        sc.ChangeScene(event, "FXMLindexScene");
    }

    @FXML
    void handleRegisztracioButtonPressed(ActionEvent event) throws IOException {
        SceneExtentions sc = new SceneExtentions();
        sc.ChangeScene(event, "FXMLRegisterScene");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }

}

