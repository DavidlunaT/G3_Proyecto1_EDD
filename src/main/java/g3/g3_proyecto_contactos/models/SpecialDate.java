/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package g3.g3_proyecto_contactos.models;
import java.io.Serializable;
import java.time.LocalDate;
/**
 *
 * @author oweny
 */
public class SpecialDate implements Serializable{

    public LocalDate date;
    public String label;
    
    public SpecialDate(LocalDate date, String label) {
        this.date = date;
        this.label = label;
    }  

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
    
    
}
