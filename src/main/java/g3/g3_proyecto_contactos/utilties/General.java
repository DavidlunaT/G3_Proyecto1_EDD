/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package g3.g3_proyecto_contactos.utilties;

import g3.g3_proyecto_contactos.models.Contact;
import g3.g3_proyecto_contactos.dataStructures.ArrayList;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author gabsy
 */
public class General {
    
    
    
    public static ArrayList load(ArrayList<Contact> contacts) {
        
       //leer la lista de contactos del archivo serializado
        try (ObjectInputStream oi = new ObjectInputStream(new FileInputStream("ruta"))) {
            contacts = (ArrayList<Contact>) oi.readObject();            
            
        } catch (FileNotFoundException ex) {
            System.out.println("archivo no existe");
        } catch (IOException ex) {
            System.out.println("error io:"+ex.getMessage());
        } catch (ClassNotFoundException ex) {
            System.out.println("error class:"+ex.getMessage());
        } 
        return contacts;
    }
    
    public static void save(Contact c, ArrayList<Contact> contacts){
        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("archivo"))){
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
