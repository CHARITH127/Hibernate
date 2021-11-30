package controller;

import bo.BoFactory;
import bo.custom.CustomerBO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import dto.CustomerDTO;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import util.Validation;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.regex.Pattern;

public class AddCustomerController {
    public JFXTextField txtCustomerID;
    public JFXTextField txtCustomerTitle;
    public JFXTextField txtCustomerName;
    public JFXTextField txtCustomerAddress;
    public JFXTextField txtCustomeCity;
    public JFXTextField txtCustomerProvince;
    public JFXTextField txtCustomerPostalCode;
    public JFXButton btnAdd;
    public JFXButton btnCansel;
    private CustomerBO customerBO = (CustomerBO) BoFactory.getBoFactory().getBo(BoFactory.BoTypes.Customer);


    LinkedHashMap<TextField, Pattern> map = new LinkedHashMap();
    Pattern idPattern = Pattern.compile("^(C-)[0-9]{3,4}$");
    Pattern titePattern = Pattern.compile("^[A-z ]{3,20}$");
    Pattern namePattern = Pattern.compile("^[A-z ]{3,20}$");
    Pattern addressPattern = Pattern.compile("^[A-z ]{3,20}$");
    Pattern cityPattern = Pattern.compile("^[A-z ]{3,20}$");
    Pattern provincePattern = Pattern.compile("^[A-z ]{3,20}$");
    Pattern postalCodePattern = Pattern.compile("^[A-z ]{3,20}$");

    public void initialize() {
        btnAdd.setDisable(true);
        storePattern();
    }

    private void storePattern() {
        map.put(txtCustomerID, idPattern);
        map.put(txtCustomerTitle, titePattern);
        map.put(txtCustomerName, namePattern);
        map.put(txtCustomerAddress, addressPattern);
        map.put(txtCustomeCity, cityPattern);
        map.put(txtCustomerProvince, provincePattern);
        map.put(txtCustomerPostalCode, postalCodePattern);
    }


    public void AddCustomerToTheDataBase(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        CustomerDTO customerDTO = new CustomerDTO(
                txtCustomerID.getText(),
                txtCustomerTitle.getText(),
                txtCustomerName.getText(),
                txtCustomerAddress.getText(),
                txtCustomeCity.getText(),
                txtCustomerProvince.getText(),
                txtCustomerPostalCode.getText()
        );

        if (customerBO.addCustomer(customerDTO)) {
            new Alert(Alert.AlertType.CONFIRMATION, "Saved..").show();
            txtCustomerID.clear();
            txtCustomerTitle.clear();
            txtCustomerName.clear();
            txtCustomerAddress.clear();
            txtCustomeCity.clear();
            txtCustomerProvince.clear();
            txtCustomerPostalCode.clear();
        } else {
            new Alert(Alert.AlertType.WARNING, "Try Again..").show();
        }
    }

    public void GotoTheCashierLogin(ActionEvent actionEvent) {
    }

    public void textField_Key_Released(KeyEvent keyEvent) {
        Object validate = Validation.validate(map, btnAdd);

        if (keyEvent.getCode() == KeyCode.ENTER) {
            if (validate instanceof TextField) {
                TextField errorTextField =((TextField) validate);
                errorTextField.requestFocus();
            }else if (validate instanceof Boolean){
                new Alert(Alert.AlertType.INFORMATION,"Add Successfull").show();
            }
        }
    }
}
