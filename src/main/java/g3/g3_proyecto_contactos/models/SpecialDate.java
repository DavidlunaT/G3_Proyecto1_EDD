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
public class SpecialDate implements Serializable{

    public String date;
    public String label;
    
    public SpecialDate(String date, String label) {
        this.date = date;
        this.label = label;
    }  

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return label + ":" + date;
    }
    
    
    
}
