/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package g3.g3_proyecto_contactos.controllers;

import g3.g3_proyecto_contactos.App;
import static g3.g3_proyecto_contactos.controllers.RegisterPersonController.isEdition;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
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

    /**
     * Initializes the controller class.
     */
    List<Phone> phones;
    List<Email> emails;
    List<Address> addresses;
    List<SpecialDate> specialDates;
    List<String> images;
    List<Contact> relations;
    @FXML
    private VBox vbPhones;
    @FXML
    private VBox vbEmails;
    @FXML
    private VBox vbAddresses;
    @FXML
    private VBox vbSpecialDates;
    @FXML
    private VBox vbRelations;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (!isEdition) {
            inicializarguardado();
        }
        phones = new ArrayList<>();
        emails = new ArrayList<>();
        addresses = new ArrayList<>();
        specialDates = new ArrayList<>();
        images = new ArrayList<>();
        relations = new ArrayList<>();
        

    }

    @FXML
    public void switchToContactVisualization() throws IOException {
        App.setRoot("contactVisualization");
    }

    @FXML
    public void switchToRegisterPerson() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("views/registerPerson.fxml"));//no tiene el controlador especificado
        RegisterPersonController ct = new RegisterPersonController();
        fxmlLoader.setController(ct);

        ScrollPane root = fxmlLoader.load();
        App.changeRoot(root);
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
            c.setRelatedContacts(relations);
            if (images.isEmpty()) {
                c.setPhoto(Contact.photoDefault);
                images.addLast(Contact.photoDefault);
            } else {
                c.setPhoto(this.images.get(0));
            }

            if (!isEdition) {
                int validNumber = 0;
                for(Contact comp: ContactVisualizationController.contacts){
                    if(comp.similarNumber(c)>0){
                        validNumber++;
                        //System.out.println(validNumber);
                    }
                }
                
                if (ContactVisualizationController.contacts.contains(c) || validNumber!=0) {
                    General.errorUser("El contacto que tratas de registrar ya se encuentra en la lista");
                } else {
                    ContactVisualizationController.contacts.addLast(c);
                    General.saveContacts(ContactVisualizationController.contacts);
                }

            } else {
                
                Company tmpCompany = new Company(c.getName(), phones);
                if (ContactVisualizationController.contacts.contains(ContactDetailController.c)) {
                    //int ind = ContactVisualizationController.contacts.indexOf(tmpPerson);
                    ContactVisualizationController.contacts.remove(ContactDetailController.c);
                    ContactVisualizationController.contacts.addLast(c);
                    General.saveContacts(ContactVisualizationController.contacts);
                }
            }
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

    public void addPhoneNumber(Phone p) {
        int currentSize = vbPhones.getChildren().size();
        vbPhones.getChildren().add(currentSize - 1, createContainerNoAddress(vbPhones, p.getLabel(), p.getNumber()));
    }

    @FXML
    public void addEmail() {
        int currentSize = vbEmails.getChildren().size();
        vbEmails.getChildren().add(currentSize - 1, createContainer(vbEmails));

    }

    public void addEmail(Email e) {
        int currentSize = vbEmails.getChildren().size();
        vbEmails.getChildren().add(currentSize - 1, createContainerNoAddress(vbEmails, e.getLabel(), e.getText()));

    }

    @FXML
    public void addAddress() {
        int currentSize = vbAddresses.getChildren().size();
        vbAddresses.getChildren().add(currentSize - 1, createContainer(vbAddresses));

    }

    public void addAddress(Address a) {
        int currentSize = vbAddresses.getChildren().size();
        vbAddresses.getChildren().add(currentSize - 1, createContainerAddress(vbAddresses, a));

    }

    @FXML
    public void addSpecialDate() {
        int currentSize = vbSpecialDates.getChildren().size();
        vbSpecialDates.getChildren().add(currentSize - 1, createContainer(vbSpecialDates));
    }

    public void addSpecialDate(SpecialDate sp) {
        int currentSize = vbSpecialDates.getChildren().size();
        vbSpecialDates.getChildren().add(currentSize - 1, createContainerNoAddress(vbSpecialDates, sp.getLabel(), sp.getDate()));
    }
    
    @FXML
    public void addRelation(){
        int currentSize = vbRelations.getChildren().size();
        vbRelations.getChildren().add(currentSize-1,createContainerRelation(vbRelations));
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
            TextField tfs = (TextField) hb.getChildren().get(2);
            if (cbs.getValue() != null && !tfs.getText().equals("")) {
                specialDates.addLast(new SpecialDate(tfs.getText(), String.valueOf(cbs.getValue())));
            }
        }
    }
    
    public void extractRelations(){
        for(int i = 0;i<vbRelations.getChildren().size() - 1; i++){
            HBox hb = (HBox) vbRelations.getChildren().get(i);
            ComboBox cbr = (ComboBox) hb.getChildren().get(1);
            if(cbr.getValue() != null){
                System.out.println(cbr.getValue().getClass());
                relations.addLast((Contact)cbr.getValue());
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

        if (mainContainer != vbAddresses) {
            cp.getChildren().add(new TextField());
        } else if (mainContainer == vbAddresses) {
            cp.getChildren().add(createContainerDataAddress());
        }
        return cp;
    }

    public HBox createContainerNoAddress(VBox mainContainer, String tipo, String data) {
        HBox cp = new HBox();
        cp.getChildren().add(deleteContainer(cp, mainContainer));
        cp.getChildren().add(createfilledComboBox(mainContainer, tipo));
        cp.getChildren().add(new TextField(data));
        return cp;
    }

    public HBox createContainerAddress(VBox mainContainer, Address a) {
        HBox cp = new HBox();
        cp.getChildren().add(deleteContainer(cp, mainContainer));
        cp.getChildren().add(new TextField(a.getLabel()));
        cp.getChildren().add(createContainerDataAddress(a));
        return cp;
    }

    public VBox createContainerDataAddress() {
        VBox vb = new VBox();
        for (int i = 0; i < 5; i++) {
            vb.getChildren().add(new TextField());
        }
        return vb;
    }
    public HBox createContainerRelation(VBox mainContainer){
        HBox cp = new HBox();
        cp.getChildren().add(deleteContainer(cp, mainContainer));
        cp.getChildren().add(createfilledComboBox(mainContainer));
        return cp;
    }

    public VBox createContainerDataAddress(Address a) {
        VBox vb = new VBox();
        vb.getChildren().add(new TextField(a.getStreet()));
        vb.getChildren().add(new TextField(a.getSecondaryStreet()));
        vb.getChildren().add(new TextField(a.getPostalCode()));
        vb.getChildren().add(new TextField(a.getCity()));
        vb.getChildren().add(new TextField(a.getCountry()));
        return vb;
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
        }else if(mainContainer == vbRelations){
            for(Contact currentContact:ContactVisualizationController.contacts){
                cb.getItems().add(currentContact);
            }
            
        }
        return cb;
    }

    public ComboBox createfilledComboBox(VBox mainContainer, String data) {
        ComboBox cb = new ComboBox();
        if (mainContainer == vbPhones) {
            Type_phone[] tp = Type_phone.values();
            cb.getItems().addAll(tp);
            for (Type_phone currentTP : tp) {
                if (currentTP.toString().equals(data)) {
                    cb.setValue(currentTP);
                }
            }
        } else if (mainContainer == vbEmails) {
            Type_email[] te = Type_email.values();
            cb.getItems().addAll(te);
            for (Type_email currentTE : te) {
                if (currentTE.toString().equals(data)) {
                    cb.setValue(currentTE);
                }
            }
        } else if (mainContainer == vbSpecialDates) {
            Type_date[] td = Type_date.values();
            cb.getItems().addAll(td);
            for (Type_date currentTD : td) {
                if (currentTD.toString().equals(data)) {
                    cb.setValue(currentTD);
                }
            }
        }
        return cb;
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

    public void fillFields(Company c) {
        btnChange.setDisable(true);
        btnChange.setVisible(false);
        txtName.setText(c.getName());
        txtDepartment.setText(c.getDepartment());
        txtWebsite.setText(c.getWebsite());
        imgMain.setImage(new Image("file:" + App.path + "photos/" + c.getPhoto()));
        for (int i = 0; i < c.getPhones().size(); i++) {
            addPhoneNumber(c.getPhones().get(i));
        }
        for (int i = 0; i < c.getEmails().size(); i++) {
            addEmail(c.getEmails().get(i));
        }
        for (int i = 0; i < c.getAddresses().size(); i++) {
            addAddress(c.getAddresses().get(i));
        }
        for (int i = 0; i < c.getSpecialDates().size(); i++) {
            addSpecialDate(c.getSpecialDates().get(i));
        }
    }

    public void inicializarguardado() {
        addPhoneNumber();
        addEmail();
        addSpecialDate();
        addAddress();
    }
}
