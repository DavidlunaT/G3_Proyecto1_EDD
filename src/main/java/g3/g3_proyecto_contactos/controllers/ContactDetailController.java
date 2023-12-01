/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package g3.g3_proyecto_contactos.controllers;

import g3.g3_proyecto_contactos.App;
import static g3.g3_proyecto_contactos.controllers.ContactVisualizationController.contacts;
import g3.g3_proyecto_contactos.dataStructures.ArrayList;
import g3.g3_proyecto_contactos.dataStructures.CustomCircularIterator;
import g3.g3_proyecto_contactos.interfaces.List;
import g3.g3_proyecto_contactos.models.Address;
import g3.g3_proyecto_contactos.models.Company;
import g3.g3_proyecto_contactos.models.Contact;
import g3.g3_proyecto_contactos.models.Email;
import g3.g3_proyecto_contactos.models.Person;
import g3.g3_proyecto_contactos.models.Phone;

import g3.g3_proyecto_contactos.models.SpecialDate;

import g3.g3_proyecto_contactos.utilties.General;
import java.io.IOException;

import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;

/**
 * FXML Controller class
 *
 * @author gabsy
 */
public class ContactDetailController implements Initializable {

    public Button btnPrevious;
    public Button btnNext;
    public HBox RetatedContact;
    public Button btnPreviewRelated;
    public Button btnNextRelated;
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
    private Button btnBack;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnEdit;
    
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
    @FXML
    private Circle pfp;
    

    public static Contact c;

    @FXML
    private ImageView locationIcon;
    @FXML
    private Label typeAddress;
    @FXML
    private ImageView imvMap;
    
    

    public List<String> cImages;
    CustomCircularIterator<String> itImages;

    @FXML
    private Button btnNextImage;
    @FXML
    private Button btnPreviousImage;
    private CustomCircularIterator<Contact> itContacts = new CustomCircularIterator<>(c.getRelatedContacts());
    
    



    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        cImages = c.getImages();
        
        if(cImages.isEmpty()){
            loadImage(c.getPhoto());
        }else{
            itImages = new CustomCircularIterator(cImages);
            loadImage(itImages.next());
        }

        setName();
        loadDefaultPhone();
        loadDefaultMail();
        loadDefaultAddress();
        loadMoreData();

    }
    @FXML
    public void switchToContactVisualization() {
        try {App.setRoot("contactVisualization");} catch (IOException ex) {}       
    }
    @FXML
    public void switchToContactImages() {
        try {App.setRoot("ContactImages");} catch (IOException ex) {}       
    }

    public void loadIcons(){
        phoneIcon.setImage(new Image("file:" + App.path + "photos/" + "phoneIcon.png", 100, 0, true, false));
        mailIcon.setImage(new Image("file:" + App.path + "photos/" + "mailIcon.png", 100, 0, true, false)); 
        locationIcon.setImage(new Image("file:" + App.path + "photos/" + "locationIcon.png", 100, 0, true, false)); 
        
    }
    
    public void loadImage(String imageUrl){
        Image img = new Image("file:" + App.path + "photos/" + imageUrl, 1000, 0, true, false);
        pfp.setFill(new ImagePattern(img));
    }
    
    public void setName(){
        lbName.setText(c.getName());
    }

    public void loadDefaultPhone() {
        List<Phone> phones = c.getPhones();
        Phone defaultPhone = phones.get(0);
        if(defaultPhone != null){
            headerPhone.setText(defaultPhone.getLabel());
            phone.setText(defaultPhone.getNumber());
        }
    }

    
    public void loadDefaultMail() {
        List<Email> emails = c.getEmails();
        Email defaultEmail = emails.get(0);
        if(defaultEmail != null){
            typeMail.setText(defaultEmail.getLabel());
            mail.setText(defaultEmail.getText());
        }
    }


    public void loadDefaultAddress() {
        List<Address> addresses = c.getAddresses();
        Address defaultAddress = addresses.get(0);
        if(defaultAddress != null){
            typeAddress.setText(defaultAddress.getLabel());
            address.setText(writeAddress(defaultAddress));
        }        
    }

    public String writeAddress(Address add) {
        return add.getStreet() + ", " + add.getSecondaryStreet() + "-"
                + add.getPostalCode() + ", " + add.getCity() + ", " + add.getCountry();
    }

    public void loadMoreData(){        
        setSpecialDatesHeader();
        loadSpecialDatesSection();        
    }
    
    private Node nodeStyle(Node node) {       
        node.setStyle("-fx-background-radius: 10;" +
            "-fx-border-radius: 10;+"+
            "-fx-background-color: #F8F1E3;" +
            "-fx-border-color: #FBF8F2;"+
            "-fx-border-width: 2; ");    
        return node;
    }
    
    
    public void setSpecialDatesHeader(){
        Label lbDates = new Label("special dates");
        lbDates.setPrefHeight(17);
        lbDates.setPrefWidth(462);
        dataRoot.getChildren().add(lbDates);
        lbDates.setStyle("-fx-text-fill: #77897c");

    }
    //good
    private HBox loadDatesNodes(List<SpecialDate> spDs) {
        //mini hbox's generator
        HBox rootDates = new HBox();       
        rootDates.setStyle("-fx-background-color: #FBF8F2");
        rootDates.prefWidth(451);
        rootDates.prefHeight(59);     
        for(SpecialDate spD : spDs){
            VBox container = styleSpecialDateMini(spD);
            rootDates.getChildren().add(container);
        }
        rootDates.setAlignment(Pos.CENTER);
        return rootDates;
    }
    
    //good
    private VBox styleSpecialDateMini(SpecialDate date){
       VBox miniContainer = new VBox();
       miniContainer.setAlignment(Pos.CENTER); 
       miniContainer.setPrefWidth(100);
       Label lbTypeDate = new Label(date.getLabel());
       lbTypeDate.setAlignment(Pos.CENTER);
       Label lbDate = new Label(date.getDate());
       lbDate.setAlignment(Pos.CENTER);
       miniContainer.getChildren().addAll(lbTypeDate, lbDate);
       miniContainer.setStyle("-fx-background-radius: 10;" +
               "-fx-border-radius: 10;" +
               "-fx-background-color: #F8F1E3;"+
               "-fx-border-color: #FBF8F2;"+
               "-fx-border-width: 2;");              
       return miniContainer;
    }
    
    
    private void loadSpecialDatesSection() {
        //setting main roots
        ScrollPane scrP = new ScrollPane();
        scrP.prefWidth(451);
        scrP.prefHeight(59);
        scrP.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrP.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrP.setStyle("-fx-background: #FBF8F2;"
                + "-fx-background-color: #FBF8F2;");
        HBox dates = loadDatesNodes(c.getSpecialDates());
        dates.setAlignment(Pos.CENTER);
        scrP.setContent(dates);
        dataRoot.getChildren().add(scrP);
    }
    
    private void loadMatchingContactsNodes() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @FXML
    public void editContact() throws IOException {
        RegisterPersonController.isEdition = true;
        if (c instanceof Person) {
            Person p = (Person) c;
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("views/registerPerson.fxml"));//no tiene el controlador especificado
            RegisterPersonController ct = new RegisterPersonController();
            fxmlLoader.setController(ct);

            ScrollPane root = fxmlLoader.load();
            ct.fillFields(p);
            App.changeRoot(root);
        } else {
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
    public void deleteContact() {
        if (ContactVisualizationController.contacts.contains(c)) {
            ContactVisualizationController.contacts.remove(c);
            General.saveContacts(ContactVisualizationController.contacts);
            switchToContactVisualization();
        }
    }

    
    @FXML
    public void nextImage(){
        loadImage(itImages.next());
    }
    
    @FXML
    public void previousImage(){
        loadImage(itImages.previous());
    }
    
    
public void styleContact(Contact c, HBox hbx) {
        HBox rootA = new HBox();
        rootA.setAlignment(Pos.CENTER);
        rootA.setPadding(new Insets(1, 3, 1, 3));
        rootA.setPrefHeight(5);
        hbx.setPadding(new Insets(10, 5, 10, 5));
        hbx.setPrefWidth(450);

        hbx.setStyle("-fx-background-radius: 10;"
                + "-fx-border-radius: 10;"
                + "-fx-background-color: #5A8165;"
                + "-fx-border-color: #FBF8F2;"
                + "-fx-border-width: 2;");

        ImageView imv = new ImageView(new Image("file:" + App.path + "photos/" + c.getPhoto(), 50, 0, true, false));
        imv.setStyle("-fx-background-radius: 100");
        
        Label lb = new Label(c.getName()+"\n"+c.getPhones().get(0).getNumber());
        lb.setMinHeight(70);
        
        System.out.println(c.getName() + " "+c.getPhoto());
        


        lb.setPadding(new Insets(10, 20, 10, 5));
        lb.setAlignment(Pos.TOP_LEFT);
        lb.setMinHeight(30);

        lb.setFont(new Font("Arial", 12));

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
        //listDisplay.getChildren().add(rootA);
    }

    public void switchToContactDetail() {
        try {App.setRoot("contactDetail");} catch (IOException ex) {}

        try {
            App.setRoot("contactDetail");
                    
        } catch (IOException ex) {
        }

    }
    public void loadRelatedContact(){
        styleContact(itContacts.next(),RetatedContact);
        
    }

    public void previewRelated(ActionEvent actionEvent) {
        styleContact(itContacts.previous(),RetatedContact);
        
        
    }

    public void nextRelated(ActionEvent actionEvent) {
        styleContact(itContacts.next(),RetatedContact);
    }

    

   
}

