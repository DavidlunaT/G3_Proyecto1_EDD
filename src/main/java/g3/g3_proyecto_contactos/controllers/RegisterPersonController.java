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
    private Button btnCancel;
    @FXML
    private Button btnSave;
    @FXML
    private Button btnChange;
    @FXML
    private Button btnAddPhoneNumber;
    @FXML
    private Button btnAddEmail;
    @FXML
    private Button btnAddAddress;
    @FXML
    private Button btnAddDate;
    @FXML
    private ImageView imgMain;
    @FXML
    private VBox vbPhones;
    @FXML
    private VBox vbEmails;
    @FXML
    private VBox vbAddresses;
    @FXML
    private VBox vbSpecialDates;

    public static boolean isEdition;
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
        System.out.println("antes de incializar guardado"+vbPhones.getChildren().size());
        if (!isEdition) {
            System.out.println("antes de incializar guardado"+vbPhones.getChildren().size());
            inicializarguardado();
            System.out.println("despues de incializar guardado"+vbPhones.getChildren().size());
        }

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
    public void switchToRegisterCompany() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("views/registerCompany.fxml"));//no tiene el controlador especificado
        RegisterCompanyController ct = new RegisterCompanyController();
        fxmlLoader.setController(ct);

        ScrollPane root = fxmlLoader.load();
        App.changeRoot(root);
    }

    @FXML
    public void savePerson() {
        extractPhones();
        extractEmails();
        extractAddresses();
        extractSpecialDates();

        if (isRegisteredCorrectly()) {
            Person p = new Person(txtNickname.getText(), phones);
            p.setFirstName1(txtName.getText());
            p.setFirstName2(txtSecondName.getText());
            p.setLastName1(txtLastName.getText());
            p.setLastName2(txtSecondLastName.getText());
            p.setImages(images);
            p.setAddresses(addresses);
            p.setEmails(emails);
            p.setSpecialDates(specialDates);
            if (images.isEmpty()) {
                p.setPhoto(Contact.photoDefault);
            } else {
                p.setPhoto(this.images.get(0));
            }
            if (!isEdition) {
                ContactVisualizationController.contacts.addLast(p);
            } else {
                //busco elimino antiguo y agrego nuevo
                Person tmpPerson = new Person(p.getName(), new ArrayList<Phone>());
                if (ContactVisualizationController.contacts.contains(tmpPerson)) {
                    //int ind = ContactVisualizationController.contacts.indexOf(tmpPerson);
                    ContactVisualizationController.contacts.remove(ContactDetailController.c);
                    ContactVisualizationController.contacts.addLast(p);
                }
            }
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
        if (txtNickname.getText().equals("")) {
            txtNickname.setText(txtName.getText());
        }
        return !txtNickname.getText().equals("") && !phones.isEmpty();
    }

    @FXML
    public void addPhoneNumber() {
        int currentSize = vbPhones.getChildren().size();
        System.out.println("addPhoneNumber:"+currentSize);
        vbPhones.getChildren().add(currentSize - 1, createContainer(vbPhones));
        System.out.println("despues de addPhoneNumber:"+currentSize);
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

    public void extractPhones() {
        System.out.println("extractPhones: "+vbPhones.getChildren().size());
        for (int i = 0; i < vbPhones.getChildren().size() - 1; i++) {
            HBox hb = (HBox) vbPhones.getChildren().get(i);
            System.out.println(hb.getChildren().size());
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
        System.out.println(vbSpecialDates.getChildren().size());
        for (int i = 0; i < vbSpecialDates.getChildren().size() - 1; i++) {
            HBox hb = (HBox) vbSpecialDates.getChildren().get(i);
            ComboBox cbs = (ComboBox) hb.getChildren().get(1);
            TextField tfs = (TextField) hb.getChildren().get(2);
            if (cbs.getValue() != null && !tfs.getText().equals("")) {
                specialDates.addLast(new SpecialDate(tfs.getText(), String.valueOf(cbs.getValue())));
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

    public void fillFields(Person p) {
        btnChange.setDisable(true);
        btnChange.setVisible(false);

        txtName.setText(p.getFirstName1());
        txtSecondName.setText(p.getFirstName2());
        txtLastName.setText(p.getLastName1());
        txtSecondLastName.setText(p.getLastName2());
        txtNickname.setText(p.getName());
        imgMain.setImage(new Image("file:" + App.path + "photos/" + p.getPhoto()));
        images.addAll(p.getImages());

        for (int i = 0; i < p.getPhones().size(); i++) {
            addPhoneNumber(p.getPhones().get(i));
        }
        for (int i = 0; i < p.getEmails().size(); i++) {
            addEmail(p.getEmails().get(i));
        }
        for (int i = 0; i < p.getAddresses().size(); i++) {
            addAddress(p.getAddresses().get(i));
        }
        for (int i = 0; i < p.getSpecialDates().size(); i++) {
            addSpecialDate(p.getSpecialDates().get(i));
        }
    }

    public void inicializarguardado() {
        //if(vbPhones.getChildren().size()!=){
            
        //}
        addPhoneNumber();
        addEmail();
        addSpecialDate();
        addAddress();
    }

}
