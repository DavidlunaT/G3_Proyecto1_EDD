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
import g3.g3_proyecto_contactos.models.Company;
import g3.g3_proyecto_contactos.models.Contact;
import g3.g3_proyecto_contactos.models.Email;
import g3.g3_proyecto_contactos.models.Person;
import g3.g3_proyecto_contactos.models.Phone;
import g3.g3_proyecto_contactos.models.SpecialDate;
import g3.g3_proyecto_contactos.utilties.General;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
/**
 * FXML Controller class
 *
 * @author oweny
 */
public class RegisterCompanyController implements Initializable {


    @FXML
    private Button btnCancel;
    @FXML
    private Button btnSave;
    @FXML
    private ImageView imgMain;
    @FXML
    private Button btnChange;
    @FXML
    private Button btnAddImages;
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtDepartment;
    @FXML
    private TextField txtWebsite;
    @FXML
    private ComboBox cmbTphone;
    @FXML
    private TextField txtPhoneNumber;
    @FXML
    private Button btnAddPhoneNumber;
    @FXML
    private ComboBox cmbTemail;
    @FXML
    private TextField txtEmail;
    @FXML
    private Button btnAddEmail;
    @FXML
    private TextField txtLabelAddress;
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
    private Button btnAddAddress;
    @FXML
    private ComboBox cmbTdate;
    @FXML
    private DatePicker dpSpecialDate;
    @FXML
    private Button btnAddDate;
    /**
     * Initializes the controller class.
     */
    List<Phone> phones;
    List<Email> emails;
    List<Address> addresses;
    List<SpecialDate> specialDates;
    List<String> images;
    
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
        images = new ArrayList<>();
        
        
        
    }
    
    @FXML
    public void switchToContactVisualization()throws IOException{
        App.setRoot("contactVisualization");
    }
    
    @FXML
    public void switchToRegisterPerson()throws IOException{
        App.setRoot("registerPerson");
    }
    
    @FXML
    public void saveCompany(){
        if (cmbTphone.getValue() != null && !txtPhoneNumber.getText().equals("")) {
            phones.addLast(new Phone(txtPhoneNumber.getText(), String.valueOf(cmbTphone.getValue())));
        }
        if (cmbTemail.getValue() != null && !txtEmail.getText().equals("")) {
            emails.addLast(new Email(txtEmail.getText(), String.valueOf(cmbTemail.getValue())));
        }
        if (!txtLabelAddress.getText().equals("") && !txtStreet.getText().equals("")) {
            addresses.addLast(new Address(txtStreet.getText(), txtSecondaryStreet.getText(), txtCodePostal.getText(), txtCity.getText(), txtCountry.getText(), txtLabelAddress.getText()));
        }
        if (cmbTdate.getValue() != null && dpSpecialDate.getValue() != null) {
            specialDates.addLast(new SpecialDate(dpSpecialDate.getValue().toString(), String.valueOf(cmbTdate.getValue())));
        }
        if (isRegisteredCorrectly()) {
            Company c = new Company(txtName.getText(),phones);
            c.setDepartment(txtDepartment.getText());
            c.setWebsite(txtWebsite.getText());
            
            c.setImages(images);
            c.setAddresses(addresses);
            c.setEmails(emails);
            c.setSpecialDates(specialDates);
            if (images.isEmpty()) {
                c.setPhoto(Contact.photoDefault);
            } else {
                c.setPhoto(this.images.get(0));
            }
            
            ContactVisualizationController.contacts.addLast(c);
            General.saveContacts(ContactVisualizationController.contacts);

            try {
                switchToContactVisualization();
            } catch (IOException ex) {
                System.out.println(ex);
            }
        } else {
            General.errorUser("Necesitas registrar almenos un nombre y un número telefonico");
        }
    }

    @FXML
    private void addImages() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleccionar Imágenes");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Archivos de Imagen", "*.png", "*.jpg", "*.jpeg", "*.gif")
        );

        //showOpenMultipleDialogo retorna una List de la API de java, aqui camuflo el "casting" con un for-each
        for (File file : fileChooser.showOpenMultipleDialog(null)) {
            System.out.println("Archivo seleccionado: " + file.getAbsolutePath()); //path local maybe hay que cambiar esto
            try {
                Path sourcePath = file.toPath();
                Path targetPath = Path.of(App.path, "photos", file.getName());

                // Crea la carpeta 'photos' si no existe
                Files.createDirectories(targetPath.getParent());

                // Copia el archivo al nuevo destino
                Files.copy(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING);

                System.out.println("Archivo copiado a: " + targetPath.toString());

                images.addLast(file.getName());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean isRegisteredCorrectly() {
        return !txtName.getText().equals("") && !phones.isEmpty();
    }

    @FXML
    public void addPhoneNumber() {
        if (cmbTphone.getValue() != null && !txtPhoneNumber.getText().equals("")) {
            phones.addLast(new Phone(txtPhoneNumber.getText(), String.valueOf(cmbTphone.getValue())));
            cmbTphone.setValue(null);
            txtPhoneNumber.clear();
            General.feedbackUser("Telefono agregado con exito");
        } else if (cmbTphone.getValue() == null && !txtPhoneNumber.getText().equals("")) {
            General.errorUser("Debe seleccionar como desea etiquetar este telefono");
        } else if (txtPhoneNumber.getText().equals("") && cmbTphone.getValue() != null) {
            General.errorUser("Debe ingresar un número de telefono");
        }
    }

    @FXML
    public void addEmail() {
        if (cmbTemail.getValue() != null && !txtEmail.getText().equals("")) {
            emails.addLast(new Email(txtEmail.getText(), String.valueOf(cmbTemail.getValue())));
            cmbTemail.setValue(null);
            txtEmail.clear();
            General.feedbackUser("Email agregado con exito");
        }else if(cmbTemail.getValue() == null && !txtEmail.getText().equals("")){
            General.errorUser("Debe seleccionar como desea etiquetar este email");
        } else if (txtEmail.getText().equals("") && cmbTemail.getValue() != null) {
            General.errorUser("Debe ingresar una direccion de correo");
        }
    }

    @FXML
    public void addAddress() {
        if (!txtLabelAddress.getText().equals("") && !txtStreet.getText().equals("")) {
            addresses.addLast(new Address(txtStreet.getText(), txtSecondaryStreet.getText(), txtCodePostal.getText(), txtCity.getText(), txtCountry.getText(), txtLabelAddress.getText()));
            txtLabelAddress.clear();
            txtStreet.clear();
            txtSecondaryStreet.clear();
            txtCodePostal.clear();
            txtCity.clear();
            txtCountry.clear();
            General.feedbackUser("Direccion agregada con exito");
        }else if(txtLabelAddress.getText().equals("") && !txtStreet.getText().equals("")){
            General.errorUser("Debe etiquetar esta direccion");
        }else if(txtStreet.getText().equals("") && !txtLabelAddress.getText().equals("")){
            General.errorUser("Debe agregar al menos la calle principal");
        }
    }

    @FXML
    public void addSpecialDate() {
        if (cmbTdate.getValue() != null && dpSpecialDate.getValue() != null) {
            specialDates.addLast(new SpecialDate(dpSpecialDate.getValue().toString(), String.valueOf(cmbTdate.getValue())));
            cmbTdate.setValue(null);
            General.feedbackUser("Fecha agregada con exito");
        }else if(cmbTdate.getValue() == null && dpSpecialDate.getValue() != null){
            General.errorUser("Debe seleccionar como etiquetar esta fecha");
        }else if(dpSpecialDate.getValue() == null && cmbTdate.getValue() != null){
            General.errorUser("Debe seleccionar una fecha");
        }
    }

    public void fillComboBoxes() {
        Type_phone[] tp = Type_phone.values();
        cmbTphone.getItems().addAll(tp);
        cmbTphone.setValue(tp[0]);

        Type_email[] te = Type_email.values();
        cmbTemail.getItems().addAll(te);
        cmbTemail.setValue(te[0]);

        Type_date[] td = Type_date.values();
        cmbTdate.getItems().addAll(td);
        cmbTdate.setValue(td[0]);

    }
}

