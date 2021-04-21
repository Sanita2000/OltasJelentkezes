/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.unideb.inf.controller;

import Extensions.SceneExtentions;
import hu.unideb.inf.model.Felhasznalo;
import hu.unideb.inf.model.Szemely;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;

public class FXMLRegisterSceneController implements Initializable{
    
    ObservableList nemek = FXCollections.observableArrayList();
    
    @FXML
    private TextField textemail;

    @FXML
    private TextField textnev;

    @FXML
    private TextField texttaj;

    @FXML
    private PasswordField textjelszo;

    @FXML
    private PasswordField textjelszoujra;

    @FXML
    private ChoiceBox<Integer> textev;

    @FXML
    private ChoiceBox<Integer> texthonap;

    @FXML
    private ChoiceBox<Integer> textnap;

    @FXML
    private ChoiceBox<String> textnem;

    @FXML
    private DatePicker BDPicker;

    @FXML
    void handleRegisztracioButtonPressed(ActionEvent event) {
        final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("br.com.fredericci.pu");
        final EntityManager entityManager = entityManagerFactory.createEntityManager();
        
        //Felhasználó adatai
        String email = textemail.getText();
        String jelszo = textjelszo.getText();
        String jelszoujra = textjelszo.getText();
        
        //Személy adatai
        String nev = textnev.getText();
        int taj = Integer.parseInt(texttaj.getText());
        String nem = textnem.getValue().toString();
        LocalDate szuletesidatum = BDPicker.getValue();
        
        //Adatok tesztelése
        boolean a = ErvenyesEmail(email);
        boolean b = ErvenyesJelszo(jelszo, jelszoujra);
        boolean c = ErvenyesNev(nev);
        boolean d = ErvenyesTaj(taj);

        
        if(a == false) System.out.println("Az email cím érvénytelen");
        if(b == false) System.out.println("A jelszó, minimum 8 karakter tartalmaznia kell kis és nagybetűket"); 
        if(c == false) System.out.println("A név megfelelő"); 
        if(d == false) System.out.println("A tajszám nem megfelelő"); 
        System.out.println("A dátum: " + szuletesidatum.toString());
        
        if(a && b && c && d)
        {
            //Felhasználó példányosítása
            Felhasznalo uriember = new Felhasznalo();
            uriember.setFelhasznalonev(email);
            uriember.setJelszo(jelszo);
            
            //Személy példányosítása
            Szemely uram = new Szemely();
            uram.setNev(nev);
            uram.setTAJ(taj);
            uram.setSzuletesiDatum(szuletesidatum);
            if(nem.equals("Nő"))
                uram.setNem(Szemely.NemTipus.NO);
            else uram.setNem(Szemely.NemTipus.FERFI);
            
            entityManager.getTransaction().begin();
            entityManager.persist(uriember);
            entityManager.getTransaction().commit();
            
            entityManager.getTransaction().begin();
            entityManager.persist(uram);
            entityManager.getTransaction().commit();
            
            JOptionPane.showMessageDialog(null,
            "Az adatok sikeresen feltöltve.",
            "Üzenet",
            JOptionPane.PLAIN_MESSAGE);
        }
        else
        {
            JOptionPane.showMessageDialog(null,
            "Hiba történt az adatok megadásakor.",
            "Hiba",
            JOptionPane.ERROR_MESSAGE);
        }
    }

    @FXML
    void handleVisszaButtonPressed(ActionEvent event) throws IOException {
        SceneExtentions sc = new SceneExtentions();
        sc.ChangeScene(event, "FXMLLoginScene");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        nemBetolto();
    }    
    
    private void nemBetolto() {
        nemek.removeAll(nemek);
        nemek.addAll("Férfi", "Nő");
        textnem.getItems().addAll(nemek);
    }
    
    public boolean ErvenyesEmail(String email) {
        return email.contains("@") && email.lastIndexOf("@") < email.lastIndexOf(".");
    }
    
    public boolean ErvenyesJelszo(String jelszo1, String jelszo2) {
        boolean a;
        int nagybetuk = 0; int kisbetuk = 0; int szamok = 0;
        for(int i = 0; i < jelszo1.length(); i++)
        {
            if(Character.isUpperCase(jelszo1.charAt(i))) nagybetuk++;
            else if(Character.isLowerCase(jelszo1.charAt(i))) kisbetuk++;
            else if(Character.isDigit(jelszo1.charAt(i))) szamok++;
        }

    return (jelszo1.equals(jelszo2)) && kisbetuk > 0 && nagybetuk > 0 && szamok > 0 && jelszo1.length() > 8;
    }

    private boolean ErvenyesNev(String nev) {
        int szamok = 0;
        for(int i = 0; i < nev.length(); i++)
            if(Character.isDigit(nev.charAt(i))) szamok++;
        return(nev.contains(" ") && nev.length() >=5 && szamok == 0);
    }

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
    private boolean ErvenyesTaj(int taj) {
        String TAJ=""+taj;
            return isNumeric(TAJ) && TAJ.length()==9 && TAJ.charAt(0)!='0';
    }
}
