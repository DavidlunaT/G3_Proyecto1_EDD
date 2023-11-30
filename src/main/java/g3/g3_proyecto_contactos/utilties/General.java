package g3.g3_proyecto_contactos.utilties;

import g3.g3_proyecto_contactos.App;
import g3.g3_proyecto_contactos.models.Contact;
import g3.g3_proyecto_contactos.dataStructures.ArrayList;
import g3.g3_proyecto_contactos.interfaces.List;
import g3.g3_proyecto_contactos.models.Address;
import g3.g3_proyecto_contactos.models.Company;
import g3.g3_proyecto_contactos.models.Email;
import g3.g3_proyecto_contactos.models.Person;
import g3.g3_proyecto_contactos.models.Phone;
import g3.g3_proyecto_contactos.models.SpecialDate;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javafx.scene.control.Alert;

/**
 *
 * @author gabsy
 */
public class General {

    public static ArrayList<Contact> loadContacts() {
        ArrayList<Contact> contacts = new ArrayList<>();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(App.path+"files/contacts.ser"))) {
            contacts = (ArrayList<Contact>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return contacts;
    }

    public static void saveContacts(List<Contact> contacts) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(App.path+"files/contacts.ser"))) {
            oos.writeObject(contacts);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void feedbackUser(String feedback) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);

        alert.setContentText(feedback);
        alert.show();
    }

    public static void errorUser(String feedback) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error Dialog");
        alert.setHeaderText(null);

        alert.setContentText(feedback);
        alert.show();
    }
}
