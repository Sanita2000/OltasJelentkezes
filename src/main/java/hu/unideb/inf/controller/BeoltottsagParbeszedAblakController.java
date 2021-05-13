/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.unideb.inf.controller;

import Extensions.SceneExtentions;
import hu.unideb.inf.model.DAO;
import hu.unideb.inf.model.Felhasznalo;
import hu.unideb.inf.model.JPADAO;
import hu.unideb.inf.model.OltasEsemeny;
import hu.unideb.inf.model.Orvos;
import hu.unideb.inf.model.Szemely;
import hu.unideb.inf.model.Vakcina;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;


public class BeoltottsagParbeszedAblakController implements Initializable {
    
    @FXML
    private Label kerdesLabel;

    @FXML
    void OnIgenButtonClicked(ActionEvent event) throws IOException {
        SceneExtentions sc = new SceneExtentions();
            sc.ChangeScene(event, "FXMLErtekelesScene");
    }

    @FXML
    void onNemButtonClicked(ActionEvent event) throws IOException {
        List<OltasEsemeny> oltasok = SceneExtentions.CheckPastOltasEsemenyek();
        if (oltasok.size() > 0)
        {   
            Init();
        }
        else
        {
            SceneExtentions sc = new SceneExtentions();
            sc.ChangeScene(event, "FXMLStudentsScene");
            System.out.println("Nincs több oltása");
        }
    }

    /**
     * Initializes the controller class.
     */    
    @Override
    public void initialize(URL url, ResourceBundle rb) {        
        Init();
    }
    
    public void Init()
    {
        List<OltasEsemeny> oltasok = SceneExtentions.CheckPastOltasEsemenyek();
        for (int i = 0; i < oltasok.size(); i++) {
            System.out.println("oltás: " + oltasok.get(i).vakcina.getNev());
        }
        if (oltasok.size() != 0)
        {
            JPADAO dao = new JPADAO();
            var currentOltas = oltasok.get(0);
            currentOltas.setVizsgalva(true);
            String title = String.format("Megkapta a(z) %s oltást ", currentOltas.vakcina.getNev()) + currentOltas.getIdopont() + " időpontban?";
            kerdesLabel.setText(title);
            dao.update(currentOltas);
        }
    }

}

