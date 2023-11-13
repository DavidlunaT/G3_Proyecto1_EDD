/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package g3.g3_proyecto_contactos.utilties;

import g3.g3_proyecto_contactos.App;
import g3.g3_proyecto_contactos.models.Contact;
import g3.g3_proyecto_contactos.dataStructures.ArrayList;
import g3.g3_proyecto_contactos.interfaces.List;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author gabsy
 */
public class General {
    
    
    
    public static List<Contact> load() {
        List<Contact> contacts = new ArrayList<>();
        String nombreArchivo = "contacts.txt";
        try(BufferedReader br = new BufferedReader (new FileReader (nombreArchivo))){
            String linea;
            while((linea = br.readLine())!= null){
                
            }
            
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
        return contacts;
    }
    
    public static void save(Contact c, List<Contact> contacts){
        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(App.path + "files/contacts.ser"))){
            contacts.addLast(c);
            out.writeObject(contacts);
        }catch(FileNotFoundException e1){
            System.out.println(e1);
        }catch(IOException e2){
            System.out.println(e2);
        }catch(Exception e3){
            System.out.println(e3);
        }
    }
}
