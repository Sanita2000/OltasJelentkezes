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
import java.util.GregorianCalendar;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;

public class FXMLRegisterSceneController implements Initializable{
    
    ObservableList nemek = FXCollections.observableArrayList();
    ObservableList evek = FXCollections.observableArrayList();
    ObservableList honapok = FXCollections.observableArrayList();
    ObservableList napok = FXCollections.observableArrayList();
    
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
        int ev = Integer.parseInt(textev.getValue().toString());
        int honap = Integer.parseInt(texthonap.getValue().toString());
        int nap = Integer.parseInt(textnap.getValue().toString());
        GregorianCalendar szuletesidatum = new GregorianCalendar(ev, honap, nap);
        
        //Adatok tesztelése
        boolean a = ErvenyesEmail(email);
        boolean b = ErvenyesJelszo(jelszo, jelszoujra);
        boolean c = ErvenyesNev(nev);
        boolean d = ErvenyesTaj(taj);
        boolean e = ErvenyesDátum(ev, honap, nap);
        
        if(a == false) System.out.println("Az email cím érvénytelen");
        if(b == false) System.out.println("A jelszó, minimum 8 karakter tartalmaznia kell kis és nagybetűket"); 
        if(c == false) System.out.println("A név megfelelő"); 
        if(d == false) System.out.println("A tajszám nem megfelelő"); 
        if(e == false) System.out.println("Az Dátum nem megfelelő"); 
        
        if(a && b && c && d && e)
        {
            //Felhasználó példányosítása
            Felhasznalo uriember = new Felhasznalo();
            uriember.setEmail(email);
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
        evBetolto();
        honapBetolto();
        napBetolto();
    }    
    
    private void nemBetolto() {
        nemek.removeAll(nemek);
        nemek.addAll("Férfi", "Nő");
        textnem.getItems().addAll(nemek);
    }
    
    private void evBetolto() {
        evek.removeAll(evek);
        for(int i = 2021; i >= 1900; i--)
        evek.addAll(i);
        textev.getItems().addAll(evek);
    }
    
    private void honapBetolto() {
        honapok.removeAll(honapok);
        for(int i = 1; i <= 12; i++)
            honapok.addAll(i);
        texthonap.getItems().addAll(honapok);
    }
    
    private void napBetolto() {
        napok.removeAll(napok);
        for(int i = 1; i <= 31; i++)
            napok.addAll(i);
        textnap.getItems().addAll(napok);
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

    private boolean ErvenyesTaj(int taj) {
        String tajszam = String.valueOf(taj);
        return tajszam.length() == 9 && tajszam.charAt(0) != '0';
    }

    private boolean ErvenyesDátum(int ev, int honap, int nap) {
        if(ev/4 == 0 && ev != 1900 && honap == 2 && nap > 29)
            return false;
        else if(honap == 4 || honap == 6 || honap == 9 || honap == 11 && nap > 30)
            return false;
        else if(honap == 2 || nap > 28)
            return false;
        return true;
    }
}
