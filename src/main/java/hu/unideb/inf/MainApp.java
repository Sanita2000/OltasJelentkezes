package hu.unideb.inf;

import Extensions.SceneExtentions;
import hu.unideb.inf.model.Orvos;
import hu.unideb.inf.model.Szemely;
import java.sql.SQLException;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.h2.tools.Server;
import hu.unideb.inf.TablaFeltoltes;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        SceneExtentions.RenderOrvosIdopont();
        //TablaFeltoltes.feltolt();
        
        //FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/fxml/FXMLOltasok.fxml"));
        FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/fxml/FXMLVakcinak.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setTitle("Oltás választó");
        stage.setScene(scene);
        stage.show();
        
        
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            startDatabase();
        } catch (SQLException ex) {
            Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }
        launch(args);
        stopDatabase();
    }

    private static Server s = new Server();
    
    private static void startDatabase() throws SQLException {
        s.runTool("-tcp", "-web", "-ifNotExists");
    }

    private static void stopDatabase()  {
        System.out.println("stopdb");
        s.shutdown();
    }
    
}
