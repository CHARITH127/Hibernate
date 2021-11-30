package controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.scene.control.*;

import java.util.Optional;

public class PayemntInforFormController {

    public RadioButton rbtCard;
    public RadioButton rbtnVisa;
    public RadioButton rbtnMaster;
    public RadioButton rbtnNaster;
    public JFXButton btnConfermPayment;
    public JFXButton btnCansel;
    public Label lblcardType;
    public Label lblCardNumber;
    public TextField txtCardNumber;
    public Label lblexpiredate;
    public TextField txtExpireDate;
    public TextField txtCustomerID;
    public TextField txtOrderID;
    public TextField txtamount;

    public void initialize(){
        lblcardType.setVisible(false);
        rbtnVisa.setVisible(false);
        rbtnMaster.setVisible(false);
        rbtnNaster.setVisible(false);
        lblCardNumber.setVisible(false);
        lblexpiredate.setVisible(false);
        txtExpireDate.setVisible(false);
        txtCardNumber.setVisible(false);

    }
    public void setViibleCardOptions(ActionEvent actionEvent) {
        lblcardType.setVisible(true);
        rbtnVisa.setVisible(true);
        rbtnMaster.setVisible(true);
        rbtnNaster.setVisible(true);
    }

    public void serTVisibleFields(ActionEvent actionEvent) {
        lblCardNumber.setVisible(true);
        txtCardNumber.setVisible(true);
        lblexpiredate.setVisible(true);
        txtExpireDate.setVisible(true);
    }

    public void PlaceAOrderOnAction(ActionEvent actionEvent) {
        ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
        ButtonType no = new ButtonType("No",ButtonBar.ButtonData.CANCEL_CLOSE);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Are you sure to pay this Order",yes,no);
        alert.setTitle("Confirmatin Alert");

        Optional<ButtonType> result= alert.showAndWait();

        if(result.orElse(no)==yes){
            new Alert(Alert.AlertType.CONFIRMATION,"Success").show();
            txtCustomerID.clear();
            txtOrderID.clear();
            txtamount.clear();
        }else{
            new Alert(Alert.AlertType.WARNING,"Try Again ").show();
        }
    }
}
