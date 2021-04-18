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
import hu.unideb.inf.model.Szemely;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 * FXML Controller class
 *
 * @author kocsisg
 */
public class indexController implements Initializable {
    
    @FXML
    private Label nevLabel;

    @FXML
    private Label SzuletesiDatumLabel;

    @FXML
    private Label TAJLabel;

    @FXML
    private Label nemLabel;   
    
    
    @FXML
    void handleToOrvosok(ActionEvent event) throws IOException {
        SceneExtentions sc = new SceneExtentions();
        sc.ChangeScene(event, "FXMLOrvosokScene");
    }
    @FXML
    void handleToVakcinak(ActionEvent event) throws IOException {
        SceneExtentions sc = new SceneExtentions();
        sc.ChangeScene(event, "FXMLVakcinakScene");
    }
    @FXML
    void handleToJelentkezes(ActionEvent event) throws IOException {
        SceneExtentions sc = new SceneExtentions();
        sc.ChangeScene(event, "FXMLJelentkezesScene");
    }


public static String format(GregorianCalendar calendar) {
    SimpleDateFormat fmt = new SimpleDateFormat("dd-MMM-yyyy");
    fmt.setCalendar(calendar);
    String dateFormatted = fmt.format(calendar.getTime());
    return dateFormatted;
}

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        DAO dao=new JPADAO();
        Szemely sz = new Szemely();
        sz.setNem(Szemely.NemTipus.FERFI);
        sz.setNev("Mike");
        sz.setSzuletesiDatum(new GregorianCalendar(1999,05,18));
        sz.setTAJ(987654321);
        dao.save(sz);
        Szemely szemely=dao.GetUserById(45);
        nevLabel.setText(szemely.getNev());
        nemLabel.setText(szemely.getNem().toString());
        SzuletesiDatumLabel.setText(format(szemely.getSzuletesiDatum()));
        TAJLabel.setText(""+szemely.getTAJ());
        
        
    }

}
