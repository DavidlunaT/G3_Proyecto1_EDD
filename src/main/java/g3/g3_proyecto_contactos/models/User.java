/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package g3.g3_proyecto_contactos.models;


import g3.g3_proyecto_contactos.dataStructures.ArrayList;

/**
 *
 * @author David
 */
public class User {
    private static String username;
    private String password;
    private static ArrayList<Contact> contacts;

    public static String getUsername() {
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

    public static ArrayList<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(ArrayList<Contact> contactos) {
        this.contacts = contactos;
    }
    private void loadContacts (){
        //lee el archivo de contactos y los carga en contacts
    }
    
    
}
