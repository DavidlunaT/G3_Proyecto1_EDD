/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package g3.g3_proyecto_contactos.controllers;

import g3.g3_proyecto_contactos.App;
import g3.g3_proyecto_contactos.dataStructures.ArrayList;
import g3.g3_proyecto_contactos.dataStructures.CustomCircularIterator;
import g3.g3_proyecto_contactos.interfaces.List;
import g3.g3_proyecto_contactos.models.Company;
import g3.g3_proyecto_contactos.models.Contact;
import g3.g3_proyecto_contactos.models.Person;
import g3.g3_proyecto_contactos.models.User;
import g3.g3_proyecto_contactos.utilties.General;
import java.io.IOException;
import java.net.URL;
import java.util.Comparator;
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
import java.util.LinkedHashSet;
import java.util.PriorityQueue;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * FXML Controller class
 *
 * @author David
 */
public class ContactVisualizationController implements Initializable, EventHandler<ActionEvent> {

    @FXML
    public VBox listDisplay;
    
    public RadioButton rdbtnName;
    public ToggleGroup np;
    public RadioButton rdbtnPN;
    public RadioButton rdbtnPC;
    @FXML
    private Stage orderBy;

    @FXML
    public Button btnOrderBy;
    @FXML
    public ComboBox<String> filterBy;

    public static List<Contact> contacts;
    @FXML
    private HBox labelNameroot;
    @FXML
    private HBox addContactRoot;
    @FXML
    private Button btnPreview;
    @FXML
    private Button loadContactsView;

    private CustomCircularIterator<Contact> itContacts;
    private int contModNext;
    private int contModPreview;
    
    private PriorityQueue<Contact> nameC ;
    
    private PriorityQueue<Contact> numberC;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        contModNext = 0;
        contModPreview = 0;
        loadContactsList();
        if (!contacts.isEmpty()) {
            loadContactsView();
        }
        loadContactsView();
        ObservableList<String> opciones = FXCollections.observableArrayList(
                "Nombre",
                "Ciudad",
                "Telefono"
        );

        filterBy.setItems(opciones);

    }

    @FXML
    public void switchToRegisterPerson(ActionEvent e) throws IOException {
        App.setRoot("registerPerson");
    }

    public void loadContactsView() {
        
        listDisplay.getChildren().clear();
        Set<Contact> miSet = new LinkedHashSet<>();
        ArrayList<Contact> currentList = new ArrayList<>();
        if (itContacts != null) {
            for (int e = 0; e < 7; e++) {
                    System.out.println(miSet.add(itContacts.next()));
            }
            for (Contact aContact : miSet) {
                styleContact(aContact);
        }

        
        }
    }
    public void btnPreview() {

        listDisplay.getChildren().clear();
        Set<Contact> miSet = new LinkedHashSet<>();
        ArrayList<Contact> currentList = new ArrayList<>();
        if (itContacts != null) {
            for (int e = 0; e < 7; e++) {
                System.out.println(miSet.add(itContacts.previous()));
                
            }
            for (Contact aContact : miSet) {
                styleContact(aContact);
        }

        
        }
    }

    public void loadContactsList() {
        contacts = General.loadContacts();
        if (!contacts.isEmpty()) {
            itContacts = new CustomCircularIterator<>(this.contacts);
        }
        
        //comparador name
        Comparator<Contact> cName = (c1,c2)-> {
            return c1.getName().compareTo(c2.getName());
        };
        
        
        
        
        //comparador telefono
        Comparator<Contact> cPhone = (c1,c2)-> {
            int n1 = Integer.parseInt(c1.getPhones().getFirst().getNumber());
            int n2 = Integer.parseInt(c2.getPhones().getFirst().getNumber());
            return n1 - n2;
        };
        //instancio las colas con un comparador
        nameC = new PriorityQueue<>(cName);
        
        
        
        numberC = new PriorityQueue<>(cPhone);
        
        
        //ingreso elementos a las colas
        System.out.println(contacts.size());
        for (int i = 0; i < contacts.size(); i++){
            
            nameC.offer(contacts.get(i));
            
            numberC.offer(contacts.get(i));
        }
        

    }

    

    public void styleContact(Contact c) {
        //roots
        HBox rootA = new HBox();
        HBox rootC = new HBox();
        //dimensions
        rootA.setAlignment(Pos.CENTER);
        rootA.setPadding(new Insets(1, 3, 1, 3));
        rootA.setPrefHeight(10);
        rootC.setPadding(new Insets(10, 5, 10, 5));
        rootC.setPrefWidth(450);
        //style
        rootC.setStyle("-fx-background-radius: 10;"
                + "-fx-border-radius: 10;"
                + "-fx-background-color: #5A8165;"
                + "-fx-border-color: #FBF8F2;"
                + "-fx-border-width: 2;");
        //Event
        setActionHBox(rootC);
        //children
        ImageView imv = new ImageView(new Image("file:" + App.path + "photos/" + c.getPhoto(), 50, 0, true, false));
        Label lb = new Label(c.getName());
        //dimensions
        lb.setPadding(new Insets(10, 20, 10, 5));
        lb.setAlignment(Pos.CENTER_LEFT);
        //style
        lb.setFont(new Font("Arial", 20));
        lb.setTextFill(Color.web("#FBF8F2"));
        //adding
        rootC.getChildren().addAll(imv, lb);
        rootA.getChildren().addAll(rootC);
        listDisplay.getChildren().add(rootA);
    }

    public void setActionHBox(HBox hbx) {
        hbx.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("mouseeee");
            }

        });

    }

    
    public void filterBy() {

    }

    @Override
    public void handle(ActionEvent t) {

    }

   
    public void nameSelected(ActionEvent actionEvent) {
        if(rdbtnName.isSelected()){
            ArrayList<Contact> countryOrdered = new ArrayList<>();
            for (Contact aContact: nameC){
                countryOrdered.addLast(aContact);
            }
            itContacts = new CustomCircularIterator(countryOrdered);
        }
        else if(!rdbtnName.isSelected()){
            
        }
    }
    public void numberSelected(ActionEvent actionEvent) {
        if(rdbtnPN.isSelected()){
            ArrayList<Contact> countryOrdered = new ArrayList<>();
            for (Contact aContact: numberC){
                countryOrdered.addLast(aContact);
            }
            itContacts = new CustomCircularIterator(countryOrdered);
        }
        else if(!rdbtnPN.isSelected()){
            
        }
    }
//    public void load(){
//        listDisplay.getChildren().clear();
//        Set<Contact> miSet = new LinkedHashSet<>();
//        ArrayList<Contact> currentList = new ArrayList<>();
//        if (itContacts != null) {
//            for (int e = 0; e < 7; e++) {
//                    currentList.addFirst();
//                }
//                currentList.clear();
//                for (int e = 0; e < 7; e++) {
//                    miSet.add(itContacts.next());
//                }
//        }
//        
//    }

    public void TypeSelected(ActionEvent actionEvent) {
         if(rdbtnPN.isSelected()){
            ArrayList<Contact> nContacts = new ArrayList<>();
        
        for(Contact aContact : contacts){
            if (aContact instanceof Person){
            nContacts.addLast(aContact);
            }
            
        }
        for(Contact aContact : contacts){
            if (aContact instanceof Company){
            nContacts.addLast(aContact);
            }
            
        }
        
        contacts = nContacts; 
        itContacts = new CustomCircularIterator<>(this.contacts);
        contModNext = contModPreview = 1;
        loadContactsView();
         }
         else {
             ArrayList<Contact> nContacts = new ArrayList<>();
             for(Contact aContact : contacts){
                if (aContact instanceof Company){
                    nContacts.addLast(aContact);
                }
            
             }
        
            for(Contact aContact : contacts){
                if (aContact instanceof Person){
                nContacts.addLast(aContact);
                }
                
            }      
        
            contacts = nContacts; 
            itContacts = new CustomCircularIterator<>(this.contacts);
            contModNext = contModPreview = 1;
            loadContactsView();
             
         }
        
        
    }
}
