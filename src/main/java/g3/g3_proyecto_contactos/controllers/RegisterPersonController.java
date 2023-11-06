/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package g3.g3_proyecto_contactos.controllers;

import g3.g3_proyecto_contactos.App;
import g3.g3_proyecto_contactos.dataStructures.ArrayList;
import g3.g3_proyecto_contactos.enums.Type_date;
import g3.g3_proyecto_contactos.enums.Type_email;
import g3.g3_proyecto_contactos.enums.Type_phone;
import g3.g3_proyecto_contactos.interfaces.List;
import g3.g3_proyecto_contactos.models.Address;
import g3.g3_proyecto_contactos.models.Contact;
import g3.g3_proyecto_contactos.models.Email;
import g3.g3_proyecto_contactos.models.Person;
import g3.g3_proyecto_contactos.models.Phone;
import g3.g3_proyecto_contactos.models.SpecialDate;
import g3.g3_proyecto_contactos.utilties.General;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author oweny
 */
public class RegisterPersonController implements Initializable {

    @FXML
    private TextField txtName;
    @FXML
    private TextField txtSecondName;
    @FXML
    private TextField txtLastName;
    @FXML
    private TextField txtSecondLastName;
    @FXML
    private TextField txtNickname;
    @FXML
    private TextField txtPhoneNumber;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtStreet;
    @FXML
    private TextField txtSecondaryStreet;
    @FXML
    private TextField txtCodePostal;
    @FXML
    private TextField txtCity;
    @FXML
    private TextField txtCountry;
    @FXML
    private Button btnCancel;
    @FXML
    private Button btnSave;
    @FXML
    private Button btnChange;
    @FXML
    private ComboBox cmbTphone;
    @FXML
    private Button btnAddPhoneNumber;
    @FXML
    private ComboBox cmbTemail;
    @FXML
    private Button btnAddEmail;
    @FXML
    private Button btnAddAddress;
    @FXML
    private Button btnAddDate;
    @FXML
    private DatePicker dpSpecialDate;
    @FXML
    private TextField txtLabelAddress;
    @FXML
    private ComboBox cmbTdate;
    
    List<Phone> phones;
    List<Email> emails;
    List<Address> addresses;
    List<SpecialDate> specialDates;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fillComboBoxes();
        phones = new ArrayList<>();
        emails = new ArrayList<>();
        addresses = new ArrayList<>();
        specialDates = new ArrayList<>();
        
        
    }
    
    public void switchToContactVisualization()throws IOException{
        App.setRoot("contactVisualization");
    }
    
    public void savePerson(){
        phones.addLast(new Phone(txtPhoneNumber.getText(),String.valueOf(cmbTphone.getValue())));
        emails.addLast(new Email(txtEmail.getText(),String.valueOf(cmbTemail.getValue())));
        addresses.addLast(new Address(txtStreet.getText(),txtSecondaryStreet.getText(),txtCodePostal.getText(),txtCity.getText(),txtCountry.getText(),txtLabelAddress.getText()));
        specialDates.addLast(new SpecialDate(dpSpecialDate.getValue(),String.valueOf(cmbTdate.getValue())));
        
        if(isRegisteredCorrectly()){
            Contact c = new Person(txtNickname.getText(),phones);         
            General.save(c, ContactVisualizationController.contacts);
            try{
                switchToContactVisualization();
                System.out.println("xd");
                }
            catch(IOException ex){
                System.out.println(ex);
                System.out.println("error");
            }
        }
    }
    
    public boolean isRegisteredCorrectly(){
        return txtNickname.getText() != null && !phones.isEmpty();
    }
    
    public void addPhoneNumber(){
        if(cmbTphone.getValue() != null && txtPhoneNumber.getText()!= null){
            phones.addLast(new Phone(txtPhoneNumber.getText(),String.valueOf(cmbTphone.getValue())));
            cmbTphone.setValue(null);
            txtPhoneNumber.clear();
            System.out.println(phones);
        }
    }
    
    public void addEmail(){
        if(cmbTemail.getValue() != null && txtEmail.getText()!= null){
            emails.addLast(new Email(txtEmail.getText(),String.valueOf(cmbTemail.getValue())));
            cmbTemail.setValue(null);
            txtEmail.clear();
        }
    }
    
    public void addAddress(){
        if(txtLabelAddress.getText() != null && txtStreet.getText()!= null){
            addresses.addLast(new Address(txtStreet.getText(),txtSecondaryStreet.getText(),txtCodePostal.getText(),txtCity.getText(),txtCountry.getText(),txtLabelAddress.getText()));
            txtLabelAddress.clear();
            txtStreet.clear();
            txtSecondaryStreet.clear();
            txtCodePostal.clear();
            txtCity.clear();
            txtCountry.clear();
        }
        
    }
    
    public void addSpecialDate(){
        if(cmbTdate.getValue() != null && dpSpecialDate.getValue()!= null){
            specialDates.addLast(new SpecialDate(dpSpecialDate.getValue(),String.valueOf(cmbTdate.getValue())));
            cmbTdate.setValue(null);
        }
    }
    
    
    
    public void fillComboBoxes(){
        Type_phone[] tp = Type_phone.values();
        cmbTphone.getItems().addAll(tp);
        
        Type_email[] te = Type_email.values();
        cmbTemail.getItems().addAll(te);
        
        Type_date[] td = Type_date.values();
        cmbTdate.getItems().addAll(td);

    }
}
