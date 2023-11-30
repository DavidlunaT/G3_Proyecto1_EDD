/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package g3.g3_proyecto_contactos.controllers;

import g3.g3_proyecto_contactos.App;
import g3.g3_proyecto_contactos.dataStructures.ArrayList;
import g3.g3_proyecto_contactos.interfaces.List;
import g3.g3_proyecto_contactos.models.Address;
import g3.g3_proyecto_contactos.models.Company;
import g3.g3_proyecto_contactos.models.Contact;
import g3.g3_proyecto_contactos.models.Email;
import g3.g3_proyecto_contactos.models.Person;
import g3.g3_proyecto_contactos.models.Phone;
import g3.g3_proyecto_contactos.utilties.General;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
    private HBox rootImg;
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
    
    public static Contact c;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println(c);
    }
    public void switchToContactVisualization() {
        try {
            App.setRoot("contactVisualization");
        } catch (IOException ex) {
        }
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
        loadMediasNodes();
        loadDatesNodes();
        loadMatchingContactsNodes();
    }

    private void loadMediasNodes() {
        HBox rootMedias = new HBox();
        rootMedias.setStyle("-fx-background-radius: 10;" +
            "-fx-border-radius: 10;+"+
            "-fx-background-color: #F8F1E3;" +
            "-fx-border-color: #FBF8F2;"+
            "-fx-border-width: 2; ");  
        Label mediasHeader = new Label("social medias");
    }
    
    
    private void loadDatesNodes() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void loadMatchingContactsNodes() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    @FXML
    public void editContact() throws IOException{
        RegisterPersonController.isEdition = true;
        if(c instanceof Person){
            Person p = (Person) c;
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("views/registerPerson.fxml"));//no tiene el controlador especificado
            RegisterPersonController ct = new RegisterPersonController();
            fxmlLoader.setController(ct);
            
           ScrollPane root = fxmlLoader.load();
            ct.fillFields(p);
            App.changeRoot(root);
        }else{
            Company comp = (Company) c;
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("views/registerCompany.fxml"));//no tiene el controlador especificado
            RegisterCompanyController ct = new RegisterCompanyController();
            fxmlLoader.setController(ct);
            
           ScrollPane root = fxmlLoader.load();
            ct.fillFields(comp);
            App.changeRoot(root);
        }
    }
    
    @FXML
    public void deleteContact(){
        if(ContactVisualizationController.contacts.contains(c)){
            ContactVisualizationController.contacts.remove(c);
            General.saveContacts(ContactVisualizationController.contacts);
             switchToContactVisualization();
            
        }
    }
    
}
