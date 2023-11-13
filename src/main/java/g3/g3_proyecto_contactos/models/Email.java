package g3.g3_proyecto_contactos.models;

import java.io.Serializable;

/**
 *
 * @author oweny
 */
public class Email implements Serializable {
    public String emailAddress;
    public String label;

    public Email(String emailAddress, String label) {
        this.emailAddress = emailAddress;
        this.label = label;
    }

    public String getText() {
        return emailAddress;
    }

    public void setText(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return label + ":" + emailAddress;
    }
    
    
}
