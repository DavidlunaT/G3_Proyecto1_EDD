/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package g3.g3_proyecto_contactos.models;

import g3.g3_proyecto_contactos.interfaces.List;
import java.io.Serializable;




/**
 *
 * @author oweny
 */
public class Person extends Contact implements Serializable, Comparable<Person>{
    private static final long serialVersionUID = 1099110916014732399L;
    
    public String firstName1;
    public String firstName2;
    public String lastName1;
    public String lastName2;
    
    public Person(String name, List<Phone> phones) {
        super(name, phones);
    }

    public String getFirstName1() {
        return firstName1;
    }

    public void setFirstName1(String firstName1) {
        this.firstName1 = firstName1;
    }

    public String getFirstName2() {
        return firstName2;
    }

    public void setFirstName2(String firstName2) {
        this.firstName2 = firstName2;
    }

    public String getLastName1() {
        return lastName1;
    }

    public void setLastName1(String lastName1) {
        this.lastName1 = lastName1;
    }

    public String getLastName2() {
        return lastName2;
    }

    public void setLastName2(String lastName2) {
        this.lastName2 = lastName2;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public List<Phone> getPhones() {
        return phones;
    }

    public void setPhones(List<Phone> phones) {
        this.phones = phones;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public List<Email> getEmails() {
        return emails;
    }

    public void setEmails(List<Email> emails) {
        this.emails = emails;
    }

    public List<SpecialDate> getSpecialDates() {
        return specialDates;
    }

    public void setSpecialDates(List<SpecialDate> specialDates) {
        this.specialDates = specialDates;
    }

    public String getPhoto() {
        return Photo;
    }

    public void setPhoto(String Photo) {
        this.Photo = Photo;
    }

    @Override
    public String toString() {
        return super.toString()+"Person{" + "firstName1=" + firstName1 + ", firstName2=" + firstName2 + ", lastName1=" + lastName1 + ", lastName2=" + lastName2 + '}';
    }

    @Override
    public int compareTo(Person o) {
        int comp = firstName1.compareTo(o.getFirstName1());
        if(comp == 0){
            comp = lastName1.compareTo(o.getLastName1());
        }
        return comp;
    }
    
    
    
    
    
    
}
