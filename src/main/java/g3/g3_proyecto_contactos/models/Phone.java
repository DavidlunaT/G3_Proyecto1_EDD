/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package g3.g3_proyecto_contactos.models;

/**
 *
 * @author oweny
 */
public class Phone {
    public String number;
    public String label;

    public Phone(String number, String label) {
        this.number = number;
        this.label = label;
        
    }

    public String getLabel() {
        return label;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setLabel(String label) {
        this.label = label;
    }
    

    public String getNumber() {
        return number;
    }
 
}
