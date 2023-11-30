/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package g3.g3_proyecto_contactos.models;

import g3.g3_proyecto_contactos.App;
import g3.g3_proyecto_contactos.interfaces.List;
import java.io.Serializable;

//implementar tda
/**
 *
 * @author oweny
 */
public abstract class Contact implements Serializable {
    private static final long serialVersionUID = 1099110916014732399L;

    public static final String photoDefault = "default.png";
    protected String name;
    protected List<String> images;
    protected List<Phone> phones;
    protected List<Address> addresses;
    protected List<Email> emails;
    protected List<SpecialDate> specialDates;
    protected String Photo;
    protected boolean favorite;
    protected List<Contact> relatedContacts;

    public Contact(List<Phone> phones) {
        this.phones = phones;
    }

    public Contact(String name, List<Phone> phones) {
        this.name = name;
        this.phones = phones;
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
        return "Contact{" + "name=" + name + ", images=" + images + ", phones=" + phones + ", addresses=" + addresses + ", emails=" + emails + ", specialDates=" + specialDates + ", Photo=" + Photo + '}';
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public List<Contact> getRelatedContacts() {
        return relatedContacts;
    }

    public void setRelatedContacts(List<Contact> relatedContacts) {
        this.relatedContacts = relatedContacts;
    }
    
    @Override
    public boolean equals(Object obj){
        if(this==obj){
            return true;
        }
        if (obj != null &&  obj instanceof Contact){
            Contact other = (Contact) obj;
            return name.equals(other.name);
        }
        return false;
    }
    

}
