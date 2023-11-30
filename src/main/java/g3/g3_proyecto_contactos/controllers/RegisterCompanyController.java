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
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
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
    @FXML
    private VBox vbPhones;
    @FXML
    private VBox vbEmails;
    @FXML
    private VBox vbAddresses;
    @FXML
    private VBox vbSpecialDates;

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
    public void switchToContactVisualization() throws IOException {
        App.setRoot("contactVisualization");
    }

    @FXML
    public void switchToRegisterPerson() throws IOException {
        App.setRoot("registerPerson");
    }

    @FXML
    public void saveCompany() {
        extractPhones();
        extractEmails();
        extractAddresses();
        extractSpecialDates();

        if (isRegisteredCorrectly()) {

            Company c = new Company(txtName.getText(), phones);
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
        int currentSize = vbPhones.getChildren().size();
        vbPhones.getChildren().add(currentSize - 1, createContainer(vbPhones));
    }

    @FXML
    public void addEmail() {
        int currentSize = vbEmails.getChildren().size();
        vbEmails.getChildren().add(currentSize - 1, createContainer(vbEmails));

    }

    @FXML
    public void addAddress() {
        int currentSize = vbAddresses.getChildren().size();
        vbAddresses.getChildren().add(currentSize - 1, createContainer(vbAddresses));

    }

    @FXML
    public void addSpecialDate() {
        int currentSize = vbSpecialDates.getChildren().size();
        vbSpecialDates.getChildren().add(currentSize - 1, createContainer(vbSpecialDates));
    }

    public void extractPhones() {
        for (int i = 0; i < vbPhones.getChildren().size() - 1; i++) {
            HBox hb = (HBox) vbPhones.getChildren().get(i);
            ComboBox cbp = (ComboBox) hb.getChildren().get(1);
            TextField tfp = (TextField) hb.getChildren().get(2);
            if (!tfp.getText().equals("")) {
                phones.addLast(new Phone(tfp.getText(), String.valueOf(cbp.getValue())));
            }
        }
    }

    public void extractEmails() {
        for (int i = 0; i < vbEmails.getChildren().size() - 1; i++) {
            HBox hb = (HBox) vbEmails.getChildren().get(i);
            ComboBox cbe = (ComboBox) hb.getChildren().get(1);
            TextField tfe = (TextField) hb.getChildren().get(2);
            if (!tfe.getText().equals("")) {
                emails.addLast(new Email(tfe.getText(), String.valueOf(cbe.getValue())));
            }
        }
    }

    public void extractAddresses() {
        for (int i = 0; i < vbAddresses.getChildren().size() - 1; i++) {
            HBox hb = (HBox) vbAddresses.getChildren().get(i);
            TextField tfa = (TextField) hb.getChildren().get(1);
            VBox vba = (VBox) hb.getChildren().get(2);

            TextField tf1 = (TextField) vba.getChildren().get(0);
            TextField tf2 = (TextField) vba.getChildren().get(1);
            TextField tf3 = (TextField) vba.getChildren().get(2);
            TextField tf4 = (TextField) vba.getChildren().get(3);
            TextField tf5 = (TextField) vba.getChildren().get(4);
            if (!tfa.getText().equals("") && !tf1.getText().equals("")) {
                addresses.addLast(new Address(tf1.getText(), tf2.getText(), tf3.getText(), tf4.getText(), tf5.getText(), tfa.getText()));
            }
        }
    }

    public void extractSpecialDates() {
        for (int i = 0; i < vbSpecialDates.getChildren().size() - 1; i++) {
            HBox hb = (HBox) vbSpecialDates.getChildren().get(i);
            ComboBox cbs = (ComboBox) hb.getChildren().get(1);
            DatePicker dps = (DatePicker) hb.getChildren().get(2);
            if (cbs.getValue() != null && dps.getValue() != null) {
                specialDates.addLast(new SpecialDate(dps.getValue().toString(), String.valueOf(cbs.getValue())));
            }

        }
    }

    public HBox createContainer(VBox mainContainer) {
        HBox cp = new HBox();
        cp.getChildren().add(deleteContainer(cp, mainContainer));

        if (mainContainer != vbAddresses) {
            cp.getChildren().add(createfilledComboBox(mainContainer));
        } else {
            cp.getChildren().add(new TextField());
        }

        if (mainContainer != vbAddresses && mainContainer != vbSpecialDates) {
            cp.getChildren().add(new TextField());
        } else if (mainContainer == vbAddresses) {
            cp.getChildren().add(createContainerDataAddress());
        } else if (mainContainer == vbSpecialDates) {
            cp.getChildren().add(new DatePicker());
        }
        return cp;
    }

    public VBox createContainerDataAddress() {
        VBox vb = new VBox();
        for (int i = 0; i < 5; i++) {
            vb.getChildren().add(new TextField());
        }
        return vb;
    }

    public Button deleteContainer(HBox containerData, VBox mainContainer) {
        Button b = new Button("-");
        EventHandler<ActionEvent> eventoClick = (ActionEvent event) -> {
            int index = mainContainer.getChildren().indexOf(containerData);
            mainContainer.getChildren().remove(index);
        };
        b.setOnAction(eventoClick);
        return b;
    }

    public ComboBox createfilledComboBox(VBox mainContainer) {
        ComboBox cb = new ComboBox();
        if (mainContainer == vbPhones) {
            cb.getItems().addAll(Type_phone.values());
            cb.setValue(Type_phone.values()[0]);
        } else if (mainContainer == vbEmails) {
            cb.getItems().addAll(Type_email.values());
            cb.setValue(Type_email.values()[0]);
        } else if (mainContainer == vbSpecialDates) {
            cb.getItems().addAll(Type_date.values());
            cb.setValue(Type_date.values()[0]);
        }
        return cb;
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
