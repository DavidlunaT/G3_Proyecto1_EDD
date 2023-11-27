/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package g3.g3_proyecto_contactos.controllers;

import g3.g3_proyecto_contactos.App;
import g3.g3_proyecto_contactos.dataStructures.ArrayList;
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
import java.util.LinkedHashSet;

/**
 * FXML Controller class
 *
 * @author David
 */
public class ContactVisualizationController implements Initializable {

    @FXML
    public VBox listDisplay;

    public static ArrayList<Contact> contacts;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        contModNext = 0;
        contModPreview = 0;
        loadContactsList();
        if(!contacts.isEmpty()){
            loadContactsView();
        }
    }

    @FXML
    public void switchToRegisterPerson(ActionEvent e) throws IOException {
        App.setRoot("registerPerson");
    }

    public void loadContactsView() {
        System.out.println("funciona next");
        listDisplay.getChildren().clear();
        Set<Contact> miSet = new LinkedHashSet<>();
        ArrayList<Contact> currentList = new ArrayList<>();

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

        for (Contact aContact : miSet) {
            if (aContact != null) {
                HBox actual = new HBox();
                System.out.println(aContact.getPhoto());
                actual.getChildren().add(new ImageView(new Image("file:" + App.path + "photos/" + aContact.getPhoto(), 60, 0, true, false)));
                actual.getChildren().add(new Label(aContact.getName()));
                listDisplay.getChildren().add(actual);
            }
        }
        contModNext++;

    }

    public void loadContactsList() {
        contacts = General.loadContacts();
        itContacts = new CustomCircularIterator<>(this.contacts);
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
        }else{
            for (int e = 0; e < 7; e++) {
                currentList.addFirst(itContacts.previous());
            }

            for (Contact c : currentList) {
                miSet.add(c);
            }
        }

        for (Contact aContact : miSet) {
            HBox actual = new HBox();
            actual.getChildren().add(new ImageView(new Image("file:" + App.path + "photos/" + aContact.getPhoto(), 60, 0, true, false)));
            actual.getChildren().add(new Label(aContact.getName()));

            listDisplay.getChildren().add(actual);
        }
        contModPreview++;
    }

}
