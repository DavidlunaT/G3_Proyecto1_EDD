/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package g3.g3_proyecto_contactos.controllers;

import g3.g3_proyecto_contactos.App;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author gabsy
 */
public class FrontPageAppController implements Initializable {
    
    private Stage stage;
    private Scene scene;
    private Parent root;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
   
    /*public void changeToList(ActionEvent e) throws IOException{
        root = App.loadFXML("contactVisualization");
        stage = (Stage)(((Node)e.getSource()).getScene().getWindow());
        scene = new Scene(root,480, 740);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();    
    }*/
    
    public void changeToList() throws IOException{
        App.setRoot("ContactVisualization");
    }
    
}
