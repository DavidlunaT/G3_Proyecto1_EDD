/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package g3.g3_proyecto_contactos.controllers;

import g3.g3_proyecto_contactos.App;
import static g3.g3_proyecto_contactos.App.loadFXML;
import g3.g3_proyecto_contactos.dataStructures.ArrayList;
import g3.g3_proyecto_contactos.dataStructures.CustomCircularIterator;
import g3.g3_proyecto_contactos.interfaces.List;
import g3.g3_proyecto_contactos.models.Company;
import g3.g3_proyecto_contactos.models.Contact;
import g3.g3_proyecto_contactos.models.Person;
import g3.g3_proyecto_contactos.models.Phone;
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
public class ContactVisualizationController implements Initializable {

    public VBox listDisplay;
    public HBox labelNameroot;
    public HBox addContactRoot;
    public RadioButton rdbtnPC;
    public RadioButton rdbtnName;
    public RadioButton rdbtnPN;
    @FXML
    private Stage orderBy;

    @FXML
    public Button btnOrderBy;
    public ComboBox<String> filterBy;

    public static List<Contact> contacts;

    private CustomCircularIterator<Contact> itContacts;
    private int contModNext;
    private int contModPreview;

    //needed to create a scene
    //CD: Contact Detail
    private Scene sceneCD;

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
        RegisterPersonController.isEdition = false;
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("views/registerPerson.fxml"));//no tiene el controlador especificado
        RegisterPersonController ct = new RegisterPersonController();
        fxmlLoader.setController(ct);
        ScrollPane root = fxmlLoader.load();
        App.changeRoot(root);

    }

    public void loadContactsView() {
        listDisplay.getChildren().clear();
        Contact last = contacts.remove(contacts.size() - 1);
        contacts.addFirst(last);
        for (int i = 0; i < 7; i++) {
            if(contacts.get(i)==null){
                
            }else{
                HBox hb= new HBox();
                styleContact(contacts.get(i),hb);
            }
            
        }
    }

    public void loadContactsList() {

        contacts = General.loadContacts();
        if (!contacts.isEmpty()) {
            itContacts = new CustomCircularIterator<>(this.contacts);
        }

    }

    public void btnPreview() {
        listDisplay.getChildren().clear();
        Contact fisrt = contacts.remove(0);
        contacts.addLast(fisrt);
        for (int i = 0; i < 7; i++) {
            if(contacts.get(i)==null){
                
            }else{
                HBox hb= new HBox();
                styleContact(contacts.get(i),hb);
            }
        }
        

    }

    public void styleContact(Contact c, HBox hbx) {
        HBox rootA = new HBox();
        rootA.setAlignment(Pos.CENTER);
        rootA.setPadding(new Insets(1, 3, 1, 3));
        rootA.setPrefHeight(10);
        hbx.setPadding(new Insets(10, 5, 10, 5));
        hbx.setPrefWidth(450);

        hbx.setStyle("-fx-background-radius: 10;"
                + "-fx-border-radius: 10;"
                + "-fx-background-color: #5A8165;"
                + "-fx-border-color: #FBF8F2;"
                + "-fx-border-width: 2;");

        ImageView imv = new ImageView(new Image("file:" + App.path + "photos/" + c.getPhoto(), 50, 0, true, false));
        Label lb = new Label(c.getName());

        lb.setPadding(new Insets(10, 20, 10, 5));
        lb.setAlignment(Pos.CENTER_LEFT);

        lb.setFont(new Font("Arial", 20));

        lb.setTextFill(Color.web("#FBF8F2"));

        hbx.getChildren().addAll(imv, lb);
        rootA.getChildren().addAll(hbx);
        EventHandler<MouseEvent> eventoClick = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (c instanceof Person) {
                    Person p = new Person(c.getName(), new ArrayList<Phone>());
                    if (contacts.contains(p)) {
                        int ind = contacts.indexOf(p);
                        ContactDetailController.c = contacts.get(ind);
                    }
                } else if (c instanceof Company) {
                    Company comp = new Company(c.getName(), new ArrayList<Phone>());
                    if (contacts.contains(comp)) {
                        int ind = contacts.indexOf(comp);
                        ContactDetailController.c = contacts.get(ind);
                    }
                }
                switchToContactDetail();
            }
        };
        rootA.setOnMouseClicked(eventoClick);
        listDisplay.getChildren().add(rootA);
    }

    public void switchToContactDetail() {
        try {App.setRoot("contactDetail");} catch (IOException ex) {}
    }



    public void TypeSelected(ActionEvent actionEvent) {
        if (rdbtnPC.isSelected()) {
            ArrayList<Contact> nContacts = new ArrayList<>();
            for (Contact aContact : contacts) {
            
            if (aContact instanceof Person) {
                
                nContacts.addLast(aContact);
                
            }
        }
        for (Contact aContact : contacts) {
            if (aContact instanceof Company) {
                nContacts.addLast(aContact);
                
            }
        } 

        contacts = nContacts;
         loadContactsView();
         btnPreview();
         
        }
        if (!rdbtnPC.isSelected()) {
            ArrayList<Contact> nContacts = new ArrayList<>();
            for (Contact aContact : contacts) {
            if (aContact instanceof Company) {
                nContacts.addLast(aContact);
                
            }
        } 
            
            for (Contact aContact : contacts) {
            
            if (aContact instanceof Person) {
                
                nContacts.addLast(aContact);
                
            }
        }
        

        contacts = nContacts;
         loadContactsView();
         btnPreview();
         
        }
        
    }
    

    public void filterBy() {

    }

    public void handle(ActionEvent t) {

    }

    public void pNSelected(ActionEvent actionEvent) {
        Comparator<Contact> cmp = (c1, c2) -> {
        if (c1 instanceof Person && c2 instanceof Person) {
            return 0; // Ambos son Person, se consideran iguales en la clasificación principal
        } else if (c1 instanceof Company && c2 instanceof Company) {
            return 0; // Ambos son Company, se consideran iguales en la clasificación principal
        } else if (c1 instanceof Person && c2 instanceof Company) {
            return -1; // Person se clasifica antes que Company
        } else {
            return 1; // Company se clasifica después de Person
        }
    };
        Comparator<Contact> cmp2 =  (c1,c2)->{
            int number1 = Integer.parseInt(c1.getPhones().getFirst().getNumber());
            int number2 = Integer.parseInt(c2.getPhones().getFirst().getNumber());
            return number1-number2;
        };
        
        
        
        if(rdbtnPN.isSelected() && rdbtnPC.isSelected() ){
            Comparator cmp3 = cmp.thenComparing(cmp2);
            PriorityQueue<Contact> contactsOrdered = new PriorityQueue<>(cmp3);
            for(Contact aContact: contacts){
                contactsOrdered.offer(aContact);
                
            }
            int hasta = contacts.size();
            contacts.clear();
            for(int i = 0; i<hasta; i++){
                
                Contact a = contactsOrdered.poll();
                System.out.println(a.getName());
                contacts.addLast(a);
                
            }
            loadContactsView();
                
        }
        else if(rdbtnPN.isSelected()){
            
            PriorityQueue<Contact> contactsOrdered = new PriorityQueue<>(cmp2);
            for(Contact aContact: contacts){
                contactsOrdered.offer(aContact);
            }
            int hasta = contacts.size();
            contacts.clear();
            for(int i = 0; i<hasta; i++){
                
                Contact a = contactsOrdered.poll();
                System.out.println(a.getName());
                System.out.println(a.getPhones().getFirst().getNumber());
                contacts.addLast(a);
                
            }
            loadContactsView();
                
        }
    }

    public void nameSelected(ActionEvent actionEvent) {
         Comparator<Contact> cmp = (c1, c2) -> {
        if (c1 instanceof Person && c2 instanceof Person) {
            return 0; // Ambos son Person, se consideran iguales en la clasificación principal
        } else if (c1 instanceof Company && c2 instanceof Company) {
            return 0; // Ambos son Company, se consideran iguales en la clasificación principal
        } else if (c1 instanceof Person && c2 instanceof Company) {
            return -1; // Person se clasifica antes que Company
        } else {
            return 1; // Company se clasifica después de Person
        }
    };
        Comparator<Contact> cmp2 =  (c1,c2)->{
            return c1.getName().toLowerCase().compareTo(c2.getName().toLowerCase());
        };
        
        
        
        if(rdbtnName.isSelected() && rdbtnPC.isSelected() ){
            Comparator cmp3 = cmp.thenComparing(cmp2);
            PriorityQueue<Contact> contactsOrdered = new PriorityQueue<>(cmp3);
            for(Contact aContact: contacts){
                contactsOrdered.offer(aContact);
                
            }
            int hasta = contacts.size();
            contacts.clear();
            for(int i = 0; i<hasta; i++){
                
                Contact a = contactsOrdered.poll();
                System.out.println(a.getName());
                contacts.addLast(a);
                
            }
            loadContactsView();
            btnPreview();
            
            
                
        }
        else if(rdbtnName.isSelected()){
            
            PriorityQueue<Contact> contactsOrdered = new PriorityQueue<>(cmp2);
            for(Contact aContact: contacts){
                contactsOrdered.offer(aContact);
            }
            int hasta = contacts.size();
            contacts.clear();
            for(int i = 0; i<hasta; i++){
                
                Contact a = contactsOrdered.poll();
                System.out.println(a.getName());
                System.out.println(a.getPhones().getFirst().getNumber());
                contacts.addLast(a);
                
            }
            loadContactsView();
            btnPreview();
                
        }
    }
}
