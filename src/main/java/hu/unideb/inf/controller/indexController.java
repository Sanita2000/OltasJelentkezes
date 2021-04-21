/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.unideb.inf.controller;
import Extensions.SceneExtentions;
import hu.unideb.inf.model.DAO;
import hu.unideb.inf.model.JPADAO;
import hu.unideb.inf.model.Szemely;
import hu.unideb.inf.model.Szemely.NemTipus;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;

public class indexController implements Initializable {

    public static int userID = 1;

    public static int ev = 0;
    public static int honap = 0;
    public static int nap = 0;

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
    private DatePicker BDPicker;

    @FXML
    private ChoiceBox<NemTipus> nemChoiceBox;

    @FXML
    private Button Change;

    @FXML
    private Button OKButton;

    public static boolean isNumeric(String strNum) {
    if (strNum == null) {
        return false;
    }
    try {
        double d = Double.parseDouble(strNum);
    } catch (NumberFormatException nfe) {
        return false;
    }
    return true;
    }
    
    private boolean ErvenyesNev(String nev) {
        int szamok = 0;
        for (int i = 0; i < nev.length(); i++) {
            if (Character.isDigit(nev.charAt(i))) {
                szamok++;
            }
        }
        return (nev.contains(" ") && nev.length() >= 5 && szamok == 0);
    }

    @FXML
    void OKButtonPushed(ActionEvent event) {
        String nev = nevTextField.getText();
        String TAJ=TAJTextField.getText();
        //LocalDate BirthDay=BDPicker.getValue();
        if(!isNumeric(TAJ) || TAJ.length()!=9 || TAJ.charAt(0)=='0')
        {
            JOptionPane.showMessageDialog(null,"Hibás adatot adtál meg.","Hiba",JOptionPane.ERROR_MESSAGE);
            return;
        }


        if (!ErvenyesNev(nev)) 
        {
            JOptionPane.showMessageDialog(null,"Hibás nevet adtál meg.","Hiba",JOptionPane.ERROR_MESSAGE);
            return;
        } 
        else {
        JOptionPane.showMessageDialog(null,
                    "Az adatok sikeresen módosítva.",
                    "Üzenet",
                    JOptionPane.PLAIN_MESSAGE);

            Change.setVisible(true);
            nevLabel.setVisible(true);
            TAJLabel.setVisible(true);
            SzuletesiDatumLabel.setVisible(true);
            nemLabel.setVisible(true);
            nevTextField.setVisible(false);
            TAJTextField.setVisible(false);
            BDPicker.setVisible(false);
            nemChoiceBox.setVisible(false);
            OKButton.setVisible(false);

            nevLabel.setText(nevTextField.getText());
            TAJLabel.setText("" + TAJTextField.getText());
            SzuletesiDatumLabel.setText(BDPicker.getValue().toString());
            nemLabel.setText(nemChoiceBox.getValue().toString());

            DAO dao = new JPADAO();
            Szemely szemely = dao.GetUserById(userID);
            szemely.setNem(nemChoiceBox.getValue());
            szemely.setNev(nevTextField.getText());
            szemely.setSzuletesiDatum(BDPicker.getValue()); //innen kiszedtem a --honap, mert ha tovabbmegy az oltasra, és vissza
                                                                                //akkor a beallitott honapot 1-el csökkenti, így viszont rendben lesz databaseben is jo
            szemely.setTAJ(Integer.parseInt(TAJTextField.getText()));
            dao.update(szemely);
            dao.save(szemely);
        }
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
        BDPicker.setVisible(true);
        nemChoiceBox.setVisible(true);
        OKButton.setVisible(true);
        nevTextField.setText(nevLabel.getText());
        TAJTextField.setText(TAJLabel.getText());
        String nemRaw=nevLabel.getText();
        if(nemRaw=="FERFI"){
        nemChoiceBox.setValue(Szemely.NemTipus.FERFI);}
        else {
        nemChoiceBox.setValue(Szemely.NemTipus.NO);}

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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
        DAO dao = new JPADAO();
        Szemely szemely = dao.GetUserById(userID);
        nevLabel.setText(szemely.getNev());
        nemLabel.setText(szemely.getNem().toString());
        SzuletesiDatumLabel.setText(szemely.getSzuletesiDatum().toString());
        TAJLabel.setText("" + szemely.getTAJ());

        ObservableList nemek = FXCollections.observableArrayList();
        nemek.add(Szemely.NemTipus.FERFI);
        nemek.add(Szemely.NemTipus.NO);
        nemChoiceBox.setItems(nemek);
        nemChoiceBox.getSelectionModel().selectFirst();

    }

}
