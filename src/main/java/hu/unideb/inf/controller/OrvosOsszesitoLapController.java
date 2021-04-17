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
import hu.unideb.inf.model.Orvos;
import hu.unideb.inf.model.Szemely;
import hu.unideb.inf.model.Vakcina;
import java.io.IOException;
import java.net.URL;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class OrvosOsszesitoLapController implements Initializable {

    DAO dao = new JPADAO();
    
    /*TableView<Orvos> table;*/
    @FXML
    private TableView<Orvos> orvosTable;

    @FXML
    private TableColumn<Orvos, String> nameColumn;

    @FXML
    private TableColumn<Orvos, Float> rateColumn;
    
    
    
    @FXML
    void GoToIndexButtonPushed(ActionEvent event) throws IOException {
        
        SceneExtentions sc = new SceneExtentions();
        sc.ChangeScene(event, "FXMLStudentsScene");
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        List<Orvos> dokik =dao.getAllOrvos(); 
       
        nameColumn.setCellValueFactory(new PropertyValueFactory<Orvos, String>("nev"));
        
        
        rateColumn.setCellValueFactory(new PropertyValueFactory<>("ertekeles"));
        
        ObservableList<Orvos> orvosok = FXCollections.observableArrayList(dokik);
        orvosTable.setItems(orvosok);
    }

}
