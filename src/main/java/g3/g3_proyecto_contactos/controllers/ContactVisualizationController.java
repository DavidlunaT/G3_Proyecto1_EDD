/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package g3.g3_proyecto_contactos.controllers;

import g3.g3_proyecto_contactos.App;
import g3.g3_proyecto_contactos.interfaces.List;
import g3.g3_proyecto_contactos.models.Contact;
import g3.g3_proyecto_contactos.models.User;
import g3.g3_proyecto_contactos.utilties.General;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;


/**
 * FXML Controller class
 *
 * @author David
 */
public class ContactVisualizationController implements Initializable {

    
    public VBox contactListView;
    protected static List<Contact> contacts;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        loadContactsList();
        loadContactsView();
        
    }

    public void switchToRegisterPerson()throws IOException{
        App.setRoot("registerPerson");
    }
    @FXML
    public void loadContactsView(){
        for(int i = 0; i <contacts.size();i++){
            HBox actual = new HBox();
            //actual.getChildren().add(new ImageView (new Image(App.path + "images/"+ contacts.get(i).getPhoto())));
            actual.getChildren().add(new Label ("a"));
            
            actual.getChildren().add(new Label (contacts.get(i).getName()));
            actual.getChildren().add(new Label ("b"));
            contactListView.getChildren().add(actual);
        }
        
        
        
    }
    public void loadContactsList(){
        contacts = General.load();
    }
  
}
