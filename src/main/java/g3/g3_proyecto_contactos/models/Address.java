/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package g3.g3_proyecto_contactos.models;

import java.io.Serializable;

/**
 *
 * @author oweny
 */
public class Address implements Serializable, Comparable<Address>{
    private static final long serialVersionUID = 1099110916014732399L;

    public String Street;
    public String secondaryStreet;
    public String postalCode;
    public String city;
    public String label;
    public String country;

    public Address(String Street, String secondaryStreet, String postalCode, String city, String country, String label ) {
        this.Street = Street;
        this.secondaryStreet = secondaryStreet;
        this.postalCode = postalCode;
        this.city = city;
        this.label = label;
        this.country = country;
    }

    public String getStreet() {
        return Street;
    }

    public void setStreet(String Street) {
        this.Street = Street;
    }

    public String getSecondaryStreet() {
        return secondaryStreet;
    }

    public void setSecondaryStreet(String secondaryStreet) {
        this.secondaryStreet = secondaryStreet;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return label + ":" + Street+ ":" + secondaryStreet+ ":" + postalCode+ ":" + city+":"+country;
    }

    @Override
    public int compareTo(Address o) {
       return country.compareTo(o.getCountry());
    }  

   
    

}
