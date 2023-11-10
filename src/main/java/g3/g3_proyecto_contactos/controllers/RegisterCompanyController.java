/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package g3.g3_proyecto_contactos.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
/**
 * FXML Controller class
 *
 * @author oweny
 */
public class RegisterCompanyController implements Initializable {


    @FXML
    private TextField txtName;
    @FXML
    private TextField txtSecondName;
    @FXML
    private TextField txtLastName;
    @FXML
    private TextField txtSecondLastName;
    @FXML
    private TextField txtNickname;
    @FXML
    private ComboBox<?> cmbPhones;
    @FXML
    private TextField txtPhoneNumber;
    @FXML
    private ComboBox<?> cmbEmails;
    @FXML
    private TextField txtEmail;
    @FXML
    private ComboBox<?> cmbAddress;
    @FXML
    private TextField txtStreet;
    @FXML
    private TextField txtSecondaryStreet;
    @FXML
    private TextField txtCodePostal;
    @FXML
    private TextField txtCity;
    @FXML
    private TextField txtCountry;
    @FXML
    private ComboBox<?> cmbDates;
    @FXML
    private TextField txtDate;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
}
