package g3.g3_proyecto_contactos.utilties;

import g3.g3_proyecto_contactos.App;
import g3.g3_proyecto_contactos.models.Contact;
import g3.g3_proyecto_contactos.dataStructures.ArrayList;
import g3.g3_proyecto_contactos.interfaces.List;
import g3.g3_proyecto_contactos.models.Address;
import g3.g3_proyecto_contactos.models.Email;
import g3.g3_proyecto_contactos.models.Person;
import g3.g3_proyecto_contactos.models.Phone;
import g3.g3_proyecto_contactos.models.SpecialDate;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


/**
 *
 * @author gabsy
 */
public class General {

    public static List<Contact> loadPeople() {
        List<Contact> contacts = new ArrayList<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader(App.path + "files/people.txt"))) {
            String linea;
            while((linea = br.readLine()) != null) {
                String[] campos = linea.split("|");
              
                String[] images = campos[6].split("_");
                ArrayList<String> lImages = new ArrayList<>();
                for(String img: images){
                    lImages.addLast(img);
                }
                
                String[] phones = campos[7].split("_");
                ArrayList<Phone> lPhones = new ArrayList<>();
                for(String phone: phones){
                    String[] dataphone = phone.split(":");
                    Phone p = new Phone(dataphone[0],dataphone[1]);
                    lPhones.addLast(p);
                }
                
                String[] emails = campos[8].split("_");
                ArrayList<Email> lEmails = new ArrayList<>();
                for(String email: emails){
                    String[] dataemail = email.split(":");
                    Email e = new Email(dataemail[0],dataemail[1]);
                    lEmails.addLast(e);
                }
                
                String[] addresses = campos[9].split("_");
                ArrayList<Address> lAddresses = new ArrayList<>();
                for(String address: addresses){
                    String[] dataAddress = address.split(":");
                    Address a = new Address(dataAddress[0],dataAddress[1],dataAddress[3],dataAddress[4],dataAddress[5],dataAddress[6]);
                    lAddresses.addLast(a);
                }
                
                String[] specialDates = campos[10].split("_");
                ArrayList<SpecialDate> lSpecialDates = new ArrayList<>();
                for(String date: specialDates){
                    String[] dataSp = date.split(":");
                    SpecialDate sp = new SpecialDate(dataSp[0],dataSp[1]);
                    lSpecialDates.addLast(sp);
                }
                
                Person p = new Person(campos[4],lPhones);
                p.setFirstName1(campos[0]);
                p.setFirstName2(campos[1]);
                p.setLastName1(campos[2]);
                p.setLastName2(campos[3]);
                p.setPhoto(campos[5]);
                p.setImages(lImages);
                p.setPhones(lPhones);
                p.setEmails(lEmails);
                p.setAddresses(lAddresses);
                p.setSpecialDates(lSpecialDates);
                
                contacts.addLast(p); 
            }
        }
       catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return contacts;
    }
    

    public static void save(String line, String ruta) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ruta))) { 
            bw.write(line);
            bw.newLine();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
