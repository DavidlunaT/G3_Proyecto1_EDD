/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package g3.g3_proyecto_contactos.models;


import g3.g3_proyecto_contactos.dataStructures.ArrayList;
import g3.g3_proyecto_contactos.interfaces.List;
import g3.g3_proyecto_contactos.utilties.General;

/**
 *
 * @author David
 */
public class User {
    private String username;
    private String password;
    private static List<Contact> contacts;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static List<Contact> getContacts() {
        return contacts;
    }

    /*public  void loadContacts (){
        contacts = General.load(contacts);
        */
    
    
}
