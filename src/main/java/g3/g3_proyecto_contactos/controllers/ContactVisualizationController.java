/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package g3.g3_proyecto_contactos.controllers;

import g3.g3_proyecto_contactos.App;
import g3.g3_proyecto_contactos.dataStructures.CustomCircularIterator;
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
import java.util.Set;
import java.util.HashSet;


/**
 * FXML Controller class
 *
 * @author David
 */
public class ContactVisualizationController implements Initializable {
    
    private Stage stage;
    private Scene scene;
    private Parent root;
    
    @FXML
    public VBox listDisplay;
    
    public static List<Contact> contacts;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadContactsList();
        System.out.println(contacts);
        loadContactsView();
    }
    
    @FXML
    public void switchToRegisterPerson(ActionEvent e)throws IOException{
        App.setRoot("registerPerson");   
    }
    
    @FXML
    public void loadContactsView(){
        //for(int i = 0; i <contacts.size();i++){
            
            //actual.getChildren().add(new ImageView (new Image(App.path + "images/"+ contacts.get(i).getUrlProfilePic())));
            
            //actual.getChildren().add(new Label (contacts.get(i).getName()));
            //contactListView.getChildren().add(actual);
            
            listDisplay.getChildren().clear();
            Set<Contact> miSet = new HashSet<>();
            CustomCircularIterator<Contact> contacts = new CustomCircularIterator<>(this.contacts);
            
            for(int e = 0; e<7 ;e++){            
            miSet.add(contacts.next());         
            }
            
            for(Contact aContact: miSet){
            HBox actual = new HBox();
            //actual.getChildren().add(new ImageView (new Image(aContact.getUrlProfilePic())));
            //App.path + "images/"+ aContact.getPhoto()
                System.out.println(App.path + "images/"+ aContact.getPhoto());
            actual.getChildren().add(new Label (aContact.getName()));
            
            listDisplay.getChildren().add(actual);
            }
            
        

    }
    public void loadContactsList(){
        contacts = General.load();
        System.out.println(contacts);
    }
    public void btnPreview(){
        Set<Contact> miSet = new HashSet<>();
        listDisplay.getChildren().clear();
            CustomCircularIterator<Contact> contacts = new CustomCircularIterator<>(this.contacts);
            
            for(int e = 0; e<7 ;e++){            
            miSet.add(contacts.previous());         
            }
            
            for(Contact aContact: miSet){
            HBox actual = new HBox();
            //actual.getChildren().add(new ImageView (new Image(aContact.getUrlProfilePic())));
            //App.path + "images/"+ aContact.getPhoto()
                System.out.println(App.path + "images/"+ aContact.getPhoto());
            actual.getChildren().add(new Label (aContact.getName()));
            
            listDisplay.getChildren().add(actual);
            }
    }
    
  
}
