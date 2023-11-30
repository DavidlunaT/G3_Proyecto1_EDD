package g3.g3_proyecto_contactos;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {
    // The logic for starting the application goes here
    
    public static Scene scene;
    public static String path = "src/main/resources/g3/g3_proyecto_contactos/";

    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("Contactly App");
        scene = new Scene(loadFXML("FrontPageApp"), 480, 740);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    public static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("views/" + fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }
    

}