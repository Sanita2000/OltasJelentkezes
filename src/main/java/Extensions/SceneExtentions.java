/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Extensions;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author karal
 */
public class SceneExtentions {
    public void ChangeScene(ActionEvent event, String scene_name) throws IOException
    {
        String file = "/fxml/" + scene_name + ".fxml";
        Parent par_scene = FXMLLoader.load(getClass().getResource(file));
        
        Scene scene = new Scene(par_scene);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow(); 
        window.setScene(scene);
        window.show();
    }
}
