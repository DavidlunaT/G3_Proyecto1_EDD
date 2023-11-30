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
        System.out.println("funciona next");
        listDisplay.getChildren().clear();
        Set<Contact> miSet = new LinkedHashSet<>();
        ArrayList<Contact> currentList = new ArrayList<>();
        if (itContacts != null) {
            if (contModPreview != 0) {
                for (int e = 0; e < 7; e++) {
                    currentList.addFirst(itContacts.next());
                }
                currentList.clear();
                for (int e = 0; e < 7; e++) {
                    miSet.add(itContacts.next());
                }
                contModPreview = 0;
            } else {
                for (int e = 0; e < 7; e++) {
                    miSet.add(itContacts.next());
                }
            }

            System.out.println("CONJUNTO DE CONTACTOS" + miSet);
        }

        for (Contact aContact : miSet) {
            if (aContact != null) {
                HBox actual = new HBox();
                styleContact(aContact, actual);
            }
        }
        contModNext++;
    }

    public void loadContactsList() {
        contacts = General.loadContacts();
        if (!contacts.isEmpty()) {
            itContacts = new CustomCircularIterator<>(this.contacts);
        }

    }

    public void btnPreview() {

        System.out.println("funciona preview");
        Set<Contact> miSet = new LinkedHashSet<>();
        listDisplay.getChildren().clear();

        ArrayList<Contact> currentList = new ArrayList<>();

        if (contModNext != 0) {
            for (int e = 0; e < 7; e++) {
                currentList.addFirst(itContacts.previous());
            }
            currentList.clear();

            for (int e = 0; e < 7; e++) {
                currentList.addFirst(itContacts.previous());
            }

            for (Contact c : currentList) {
                miSet.add(c);
            }
            contModNext = 0;
        } else {
            for (int e = 0; e < 7; e++) {
                currentList.addFirst(itContacts.previous());
            }

            for (Contact c : currentList) {
                miSet.add(c);
            }
        }

        for (Contact aContact : miSet) {
            if (aContact != null) {
                HBox actual = new HBox();
                styleContact(aContact, actual);
            }
        }
        contModPreview++;
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

    public void filterBy() {

    }

    public void handle(ActionEvent t) {

    }

}