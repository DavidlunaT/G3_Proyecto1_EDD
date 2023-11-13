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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author David
 */
public class ContactVisualizationController implements Initializable {
    
    private Stage stage;
    private Scene scene;
    private Parent root;
    
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
    
    @FXML
    public void switchToRegisterPerson(ActionEvent e)throws IOException{
        App.setRoot("registerPerson");   
    }
    
    @FXML
    public void loadContactsView(){
        for(int i = 0; i <contacts.size();i++){
            HBox actual = new HBox();
            //actual.getChildren().add(new ImageView (new Image(App.path + "images/"+ contacts.get(i).getPhoto())));
            
            actual.getChildren().add(new Label (contacts.get(i).getName()));
            //contactListView.getChildren().add(actual);
            //Nuevo Codigo por sustituir-------------------------------------------------------------------------------------
            /*
            for(int i = 0; i<7,i++){
            CustomCircularIterator<Contact> contacts = new CustomCircularIterator<>();
            Contact aContact= contacts.next();
            actual.getChildren().add(new ImageView (new Image(App.path + "images/"+ aContact.getPhoto())));
            actual.getChildren().add(new Label (aContact.getName()));
            contactListView.getChildren().add(actual);
            }
            */
        }

    }
    public void loadContactsList(){
        contacts = General.load();
    }
  
}
