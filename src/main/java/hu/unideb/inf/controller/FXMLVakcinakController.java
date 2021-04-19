
package hu.unideb.inf.controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Extensions.SceneExtentions;
import static hu.unideb.inf.controller.FXMLOltasokController.oltasAzonosito;
import hu.unideb.inf.model.JPADAO;
import hu.unideb.inf.model.Vakcina;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

//import org.controlsfx.control.Rating;

/**
 * FXML Controller class
 *
 * @author Tamás Ádám
 */
public class FXMLVakcinakController extends SceneExtentions implements Initializable {

    /**
     * Initializes the controller class.
     */
    JPADAO dao = new JPADAO();
    ObservableList list = FXCollections.observableArrayList();
    
    @FXML
    private ListView<String> oltasLista;
    
    @FXML
    private Button fooldal;
    
    /*@FXML
    private Rating ertekeles; //dependenciesben hozzáadtam a controlsfx 11.1.0.jar-t
    */
    private float ertek;
    
    @FXML
    private Text szoveg;

    
    @FXML
    void displaySelected(MouseEvent event) {
        
        //String ertekeles = oltasLista.getSelectionModel().getSelectedItem();
        oltasLista.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> ov, String oldValue, String newValue) {
                System.out.println(newValue);
            }
        });
        
    

    }


    @FXML
    void goBackToIndex(ActionEvent event) throws IOException {
        ChangeScene(event, "FXMLindexScene");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        List<Vakcina> vakcinak = dao.getAllVakcina();
        int i = 0;
        for (Vakcina v: vakcinak)
        {
            list.add(v.getNev());
            if (i < 10)
            {
                if (i == 0)
                {
                    i++;
                    continue;
                }
                v.setErtekeles((float) 2.4);
            }
            else
            {
                v.setErtekeles((float)4.2);
            }
            i++;
            
        }
        
       
        oltasLista.getItems().addAll(list);
        


        
        /*
        ertekeles.setMouseTransparent(true); // ne lehessen modosítani, disable nemjo, mert elszürkiti
        ertekeles.ratingProperty().set(floatKerekit(0));
        szoveg.setText("Válassz egy oltást!");
        
        oltasLista.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> ov, String oldValue, String newValue) {
                //System.out.println(newValue);
            for (Vakcina v: vakcinak)
            {
                if(v.getNev() == newValue)
                {
                    
                    ertek = v.getErtekeles();
                    break;
                }
            }
            if (ertek < 1)
            {
                szoveg.setVisible(true);
                szoveg.setText("Még nem érkezett értékelés ehhez az oltáshoz!");
                ertekeles.ratingProperty().set(0);
            }
            else
            {
            szoveg.setVisible(false);
            ertekeles.ratingProperty().set(floatKerekit(ertek));
            }
            }
        });
*/
    }

    private double floatKerekit(float szam) {
        return Math.round(szam);
    }

}