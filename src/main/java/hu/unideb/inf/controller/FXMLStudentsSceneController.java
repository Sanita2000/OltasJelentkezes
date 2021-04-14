/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.unideb.inf.controller;

import Extensions.SceneExtentions;
import hu.unideb.inf.MainApp;
import hu.unideb.inf.model.DAO;
import hu.unideb.inf.model.Felhasznalo;
import hu.unideb.inf.model.JPADAO;
import hu.unideb.inf.model.Orvos;
import hu.unideb.inf.model.Szemely;
import hu.unideb.inf.model.Vakcina;
import java.io.IOException;
import java.net.URL;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class FXMLStudentsSceneController implements Initializable {

    @FXML
    void AdatfeltoltesButtonPushed(ActionEvent event) throws IOException {
        System.out.println("Hello world!!!");        
        try (DAO dao = new JPADAO();)
        {                        
            List<Orvos> dokik = dao.getAllOrvos();
            for (int i = 0; i < dokik.size(); i++) {
                System.out.println(dokik.get(i).getNev());
            }
            
        }
        catch (Exception e)
        {
            
        }        
        SceneExtentions sc = new SceneExtentions();
        sc.ChangeScene(event, "OrvosOsszesitoLap");
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
