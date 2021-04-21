package hu.unideb.inf.controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import static Extensions.Nevesitett_konst.FOGLALT1;
import static Extensions.Nevesitett_konst.FOGLALT2;
import static Extensions.Nevesitett_konst.FOGLALT3;
import static Extensions.Nevesitett_konst.FOGLALT4;
import static Extensions.Nevesitett_konst.FOGLALT5;
import static Extensions.Nevesitett_konst.FOGLALT6;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import Extensions.SceneExtentions;
import static hu.unideb.inf.controller.FXMLAdatokController.idopontok;
import static hu.unideb.inf.controller.FXMLAdatokController.index;
import static hu.unideb.inf.controller.FXMLAdatokController.kiegeszit;
import static hu.unideb.inf.controller.FXMLAdatokController.valasztottIdopont;
import static hu.unideb.inf.controller.FXMLAdatokController.valasztottOrvos;
import static hu.unideb.inf.controller.FXMLAdatokController.valasztottOrvosID;
import static hu.unideb.inf.controller.FXMLOltasokController.oltasAzonosito;
import static hu.unideb.inf.controller.FXMLOltasokController.oltasNev;
import static hu.unideb.inf.controller.indexController.format;
import static hu.unideb.inf.controller.indexController.userID;
import hu.unideb.inf.model.JPADAO;
import hu.unideb.inf.model.OltasEsemeny;
import hu.unideb.inf.model.Orvos;
import hu.unideb.inf.model.Szemely;

import hu.unideb.inf.model.Vakcina;
import java.time.LocalDateTime;
import java.util.List;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 * FXML Controller class
 *
 * @author Tamás Ádám
 */
public class FXMLAdatok2Controller extends SceneExtentions implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Text oltasTipus;

    @FXML
    private Text valasztOrvos;
    
    @FXML
    private Text idopontText;
   
    @FXML
    private CheckBox ellenorzes;
    
    @FXML
    private Text hiba;
    
    @FXML
    private Text nevMezo;

    @FXML
    private Text tajMezo;

    @FXML
    private Text szulinapMezo;

    @FXML
    private Text nemMezo;
    
    

    
    @FXML
    void backToTheDoctors(ActionEvent event) throws IOException{
        ChangeScene(event, "FXMLAdatok");
    }
    
    void kuldesKiegeszito()
    {
        if (kiegeszit == 1)
        {
            FOGLALT1.add(index);
        }
        else if (kiegeszit == 2)
        {
            FOGLALT2.add(index);
        }
        else if (kiegeszit == 3)
        {
            FOGLALT3.add(index);
        }
        else if (kiegeszit == 4)
        {
            FOGLALT4.add(index);
        }
        else if (kiegeszit == 5)
        {
            FOGLALT5.add(index);
        }
        else if (kiegeszit == 6)
        {
            FOGLALT6.add(index);
        }
    }
    
    
    @FXML
    void kuldes(ActionEvent event) throws IOException {
        JPADAO dao = new JPADAO();
        if (!ellenorzes.isSelected())
        {
            hiba.setVisible(true);
        }
        else
        {
            OltasEsemeny oltas = new OltasEsemeny();
            oltas.setIdopont(idopontok.get(index));
            //FOGLALT1.add(index);
            kuldesKiegeszito();

            //oltFOGLALT1.add(index);as.setMegkapta(true);
            
            //final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("br.com.fredericci.pu");
            //final EntityManager entityManager = entityManagerFactory.createEntityManager();
            //TypedQuery<Vakcina> query = entityManager.createQuery("SELECT a FROM Vakcina a WHERE ID = " + oltasAzonosito, Vakcina.class);
            //List<Vakcina> vakcinak = query.getResultList();
            //List<Vakcina> vakcinak = dao.getVakcinaById(oltasAzonosito);
//          System.out.println(vakcinak.get(0).getNev());
            //TypedQuery<Orvos> o_query = entityManager.createQuery("SELECT a FROM Orvos a WHERE ID = " + valasztottOrvosID, Orvos.class);
            //List<Orvos> orvosok = o_query.getResultList();
           // List<Orvos> orvosok = dao.GetOrvosById(valasztottOrvosID);

            //Vakcina vakcina = vakcinak.get(0);
            //vakcina.beoltas.add(oltas);

            oltas.vakcina = dao.GetVakcinaById(oltasAzonosito);
            oltas.orvos = dao.GetOrvosById(valasztottOrvosID);
            oltas.user = dao.GetUserById(userID);
            //System.out.println(vakcina.beoltas);

            dao.save(oltas);
            //dao.update(vakcina);
            
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Sikeres jelentkezés");
            alert.setHeaderText(null);
            alert.setContentText("Ön sikeresen jelentkezett az oltásra!");

            alert.showAndWait();
 
            
            ChangeScene(event, "FXMLindexScene");
        }

    }
    
    @FXML
    void visszaallit(ActionEvent event) {
        hiba.setVisible(false);
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         
        oltasTipus.setText(oltasAzonosito + " / " + oltasNev);
        valasztOrvos.setText(valasztottOrvos);
        idopontText.setText(valasztottIdopont);
        JPADAO dao = new JPADAO();
        Szemely aktualisUser = dao.GetUserById(userID);
        nevMezo.setText(aktualisUser.getNev());
        tajMezo.setText("" + aktualisUser.getTAJ());
        szulinapMezo.setText(format(aktualisUser.getSzuletesiDatum()));
        nemMezo.setText(aktualisUser.getNem().toString());
        
    }    
    
}
