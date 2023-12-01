/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package g3.g3_proyecto_contactos.controllers;

import g3.g3_proyecto_contactos.App;
import static g3.g3_proyecto_contactos.App.loadFXML;
import static g3.g3_proyecto_contactos.controllers.ContactDetailController.c;
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
        System.out.println(contacts.size());
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
        for(Contact c: contacts){
            System.out.println(c.getName());
        }
        System.out.println(contacts.size());
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
            HBox hb= new HBox();
            styleContact(contacts.get(i),hb);
        }
        for (Contact a : contacts) {
            System.out.println(a.getName());
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
            HBox hb= new HBox();
            styleContact(contacts.get(i),hb);
        }
        for (Contact a : contacts) {
            System.out.println(a.getName());
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


        
        ImageView imv = new ImageView(new Image("file:" + App.path + "photos/" + c.getPhoto(), 50, 1000, true, false));
        imv.setStyle("-fx-background-radius: 100");
        
        Label lb = new Label(c.getName()+"\n"+c.getPhones().get(0).getNumber());

        
        System.out.println(c.getName() + " "+c.getPhoto());
        


        lb.setPadding(new Insets(10, 20, 10, 5));
        lb.setAlignment(Pos.TOP_LEFT);
        lb.setMinHeight(54);

        lb.setFont(new Font("Arial", 15));

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
        try {
            App.setRoot("contactDetail");
                    
        } catch (IOException ex) {
        }
    }

    public void orderBy() throws IOException {

        Parent root = App.loadFXML("orderBy");
        Stage nView = new Stage();
        nView.setTitle("Order By");
        Scene scene = new Scene(root);
        nView.setScene(scene);
        nView.show();
    }

    public void TypeSelected(ActionEvent actionEvent) {
        if (rdbtnPC.isSelected()) {
            ArrayList<Contact> nContacts = new ArrayList<>();
            for (Contact aContact : contacts) {
            System.out.println(aContact.getClass().getName());
            if (aContact instanceof Person) {
                nContacts.addLast(aContact);
                System.out.println("a");
            }
        }
        for (Contact aContact : contacts) {
            if (aContact instanceof Company) {
                nContacts.addLast(aContact);
                System.out.println("b");
            }
        }for(Contact a: nContacts){
            System.out.println(a.getName());
        }
        contacts = nContacts;
         loadContactsView();
        }
        
    }
    

    public void filterBy() {

    }

    public void handle(ActionEvent t) {

    }

}
