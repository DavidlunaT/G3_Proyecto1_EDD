/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package g3.g3_proyecto_contactos.controllers;

import g3.g3_proyecto_contactos.App;
import g3.g3_proyecto_contactos.dataStructures.ArrayList;
import g3.g3_proyecto_contactos.interfaces.List;
import g3.g3_proyecto_contactos.models.Address;
import g3.g3_proyecto_contactos.models.Contact;
import g3.g3_proyecto_contactos.models.Email;
import g3.g3_proyecto_contactos.models.Phone;
import g3.g3_proyecto_contactos.models.SpecialDate;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author gabsy
 */
public class ContactDetailController implements Initializable {

    @FXML
    private HBox rootBtns;
    @FXML
    private VBox rootImg;
    @FXML
    private VBox dataRoot;
    @FXML
    private HBox rootPhone;
    @FXML
    private ImageView phoneIcon;
    @FXML
    private ColumnConstraints gridPhone;
    @FXML
    private Label headerPhone;
    @FXML
    private Label phone;
    @FXML
    private HBox rootWork;
    @FXML
    private ImageView mailIcon;
    @FXML
    private ColumnConstraints gridMail;
    @FXML
    private Label typeMail;
    @FXML
    private Label mail;
    @FXML
    private HBox rootPhotos;
    @FXML
    private ImageView loationIcon;
    @FXML
    private Label headerPhone1;
    @FXML
    private Button btnBack;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnEdit;
    @FXML
    private ImageView pfp;
    @FXML
    private VBox rootMap;
    @FXML
    private Label address;
    @FXML
    private Label lbMemories;
    @FXML
    private HBox photosList;
    @FXML
    private Label lbName;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void loadImage(String imageUrl){
        Image img = new Image("file:" + App.path + "photos/" + imageUrl, 50, 0, true, false);
        pfp.setImage(img);
        pfp.setStyle("-fx-border-radius: 50%;");
    }
    
    public void loadDefaultPhone(List<Phone> phones){
        phones = new ArrayList<>();
        Phone defaultPhone = phones.get(0);
        headerPhone.setText(defaultPhone.getLabel());
        phone.setText(defaultPhone.getNumber());
    }
    
    public void loadDefaultMail(List<Email> emails){
        emails = new ArrayList<>();
        Email defaultEmail = emails.get(0);
        typeMail.setText(defaultEmail.getLabel());
        mail.setText(defaultEmail.getText());
    }
    
    public void loadDefaultAddress(List<Address> addresses){
        addresses = new ArrayList<>();
        Address defaultAddress = addresses.get(0);
        typeMail.setText(defaultAddress.getLabel());
        mail.setText(writeAddress(defaultAddress));
    }
    
    public String writeAddress(Address add){
        return add.getStreet()+", "+add.getSecondaryStreet()+"-"
                +add.getPostalCode()+", "+add.getCity()+", "+add.getCountry();
    }
    
    public void loadMoreData(){ 
        setSpecialDatesHeader();
        //loadDatesNodes();
        loadMatchingContactsNodes();
    }
    
    private void hboxStyle(HBox hbx) {       
        hbx.setStyle("-fx-background-radius: 10;" +
            "-fx-border-radius: 10;+"+
            "-fx-background-color: #F8F1E3;" +
            "-fx-border-color: #FBF8F2;"+
            "-fx-border-width: 2; ");          
    }
    
    
    public void setSpecialDatesHeader(){
        Label lbDates = new Label("special dates");
        lbDates.setPrefHeight(17);
        lbDates.setPrefWidth(462);
        dataRoot.getChildren().add(lbDates);
    }
    
    private void loadDatesNodes(List<SpecialDate> spDs) {
        HBox rootDates = new HBox();
        hboxStyle(rootDates);
        for(SpecialDate spD : spDs){
            loadSpecialDateStyle(spD, rootDates);
        }
    }
    
    private void loadSpecialDateStyle(SpecialDate spD, HBox rootDate) {
        VBox rootDates = new VBox();
        rootDate.setStyle("hbx");
        ScrollPane srp = new ScrollPane();
        srp.setHbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        srp.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        srp.setPadding(new Insets(20,10,0,10));
        srp.setStyle("-fx-background-color: #F8F1E3;");
    }
    
    private void loadMatchingContactsNodes() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
    
}
