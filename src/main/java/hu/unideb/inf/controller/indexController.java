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
import hu.unideb.inf.model.Szemely.NemTipus;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import static javafx.collections.FXCollections.observableArrayList;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class indexController implements Initializable {

    public static int userID=1;

    
    public static int ev=0;
    public static int honap=0;
    public static int nap=0;
    

    @FXML
    private Label nevLabel;

    @FXML
    private Label SzuletesiDatumLabel;

    @FXML
    private Label TAJLabel;

    @FXML
    private Label nemLabel;

    @FXML
    private TextField nevTextField;

    @FXML
    private TextField TAJTextField;

    @FXML
    private ChoiceBox<Integer> evChoiceBox;

    @FXML
    private ChoiceBox<Integer> honapChoiceBox;

    @FXML
    private ChoiceBox<Integer> napChoiceBox;
    
    @FXML
    private ChoiceBox<NemTipus> nemChoiceBox;

    @FXML
    private Button Change;

    @FXML
    private Button OKButton;

    @FXML
    void OKButtonPushed(ActionEvent event) {

        Change.setVisible(true);
        nevLabel.setVisible(true);
        TAJLabel.setVisible(true);
        SzuletesiDatumLabel.setVisible(true);
        nemLabel.setVisible(true);
        nevTextField.setVisible(false);
        TAJTextField.setVisible(false);
        evChoiceBox.setVisible(false);
        honapChoiceBox.setVisible(false);
        napChoiceBox.setVisible(false);
        nemChoiceBox.setVisible(false);
        OKButton.setVisible(false);

        nevLabel.setText(nevTextField.getText());
        TAJLabel.setText("" + TAJTextField.getText());
        
        ev=evChoiceBox.getValue();
        nap=napChoiceBox.getValue();
        honap=honapChoiceBox.getValue()-1;
        
        SzuletesiDatumLabel.setText(format(new GregorianCalendar(ev,honap,nap)));
        nemLabel.setText(nemChoiceBox.getValue().toString());
        
        DAO dao = new JPADAO();
        Szemely szemely = dao.GetUserById(userID);
        szemely.setNem(nemChoiceBox.getValue());
        szemely.setNev(nevTextField.getText());
        szemely.setSzuletesiDatum(new GregorianCalendar(ev, honap, nap));
        szemely.setTAJ(Integer.parseInt(TAJTextField.getText()));
        dao.update(szemely);
        dao.save(szemely);
    }

    @FXML
    void handleChangeButton(ActionEvent event) {
        Change.setVisible(false);
        nevLabel.setVisible(false);
        TAJLabel.setVisible(false);
        SzuletesiDatumLabel.setVisible(false);
        nemLabel.setVisible(false);
        nevTextField.setVisible(true);
        TAJTextField.setVisible(true);
        evChoiceBox.setVisible(true);
        honapChoiceBox.setVisible(true);
        napChoiceBox.setVisible(true);
        nemChoiceBox.setVisible(true);
        OKButton.setVisible(true);        
    }

    @FXML
    void handleToOrvosok(ActionEvent event) throws IOException {
        SceneExtentions sc = new SceneExtentions();
        sc.ChangeScene(event, "OrvosOsszesitoLap");
    }

    @FXML
    void handleToVakcinak(ActionEvent event) throws IOException {
        SceneExtentions sc = new SceneExtentions();
        sc.ChangeScene(event, "FXMLVakcinak");
    }

    @FXML
    void handleToJelentkezes(ActionEvent event) throws IOException {
        SceneExtentions sc = new SceneExtentions();
        sc.ChangeScene(event, "FXMLOltasok");
    }

    public static String format(GregorianCalendar calendar) {
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy.MMMdd.");
        fmt.setCalendar(calendar);
        String dateFormatted = fmt.format(calendar.getTime());

        return dateFormatted;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        DAO dao = new JPADAO();
        Szemely sz = new Szemely();
        sz.setNem(Szemely.NemTipus.FERFI);
        sz.setNev("Mike");
        sz.setSzuletesiDatum(new GregorianCalendar(1999, 05, 18));
        sz.setTAJ(987654321);
        dao.save(sz);
        Szemely szemely = dao.GetUserById(userID);
        nevLabel.setText(szemely.getNev());
        nemLabel.setText(szemely.getNem().toString());
        SzuletesiDatumLabel.setText(format(szemely.getSzuletesiDatum()));
        TAJLabel.setText("" + szemely.getTAJ());

        ObservableList nemek =  FXCollections.observableArrayList();
        nemek.add(Szemely.NemTipus.FERFI);
        nemek.add(Szemely.NemTipus.NO);
        nemChoiceBox.setItems(nemek);
        nemChoiceBox.getSelectionModel().selectFirst();
        
        ObservableList honapok =  FXCollections.observableArrayList();
        for(int i=1; i<13; i++)
        {
            honapok.add(i);
        }
        honapChoiceBox.setItems(honapok);
        honapChoiceBox.getSelectionModel().selectFirst();
        
        ObservableList napok =  FXCollections.observableArrayList();
        for(int i=1; i<32; i++)
        {
            napok.add(i);
        }
        napChoiceBox.setItems(napok);
        napChoiceBox.getSelectionModel().selectFirst();
        
        ObservableList evek =  FXCollections.observableArrayList();
        for(int i=1900; i<2005; i++)
        {
            evek.add(i);
        }
        evChoiceBox.setItems(evek);
        evChoiceBox.getSelectionModel().selectFirst();
    }

}
