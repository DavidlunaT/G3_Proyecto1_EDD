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
    private String username;
    private String password;
    private ArrayList<Contact> contactos;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    private String getPassword() {
        return password;
    }
    public String getPasswordRequest(){
        //logica para verificar si es autentico el usuario para poder mostrar 
        //la contraseña...
        return getPassword();
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public ArrayList<Contact> getContacts(){
        ArrayList<Contact> contactosCopy = new ArrayList<>();
        contactosCopy.addAll(contactos);
        return contactosCopy;
    }
    public void addContact(Contact contact){
        contactos.addLast(contact);
    }
    public boolean removeContact(Contact contact){
        return contactos.remove(contact);
        
    }
}
