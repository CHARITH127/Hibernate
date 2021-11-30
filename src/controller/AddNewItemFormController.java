package controller;

import bo.custom.impl.ItemBOImpl;
import bo.custom.ItemBo;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import dto.ItemDTO;

import java.sql.SQLException;

public class AddNewItemFormController {

    public TextField txtItemCode;
    public TextArea txtDescription;
    public TextField txtPackSize;
    public TextField txtUnitprice;
    public TextField txtQuantityOnHand;
    public JFXButton btnAddItemToTheDataBase;
    public JFXButton btnCansel;
    public TextField txtDiscount;
    ItemBo itemBo = new ItemBOImpl();

    public void AddItemToTheDataBase(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        ItemDTO itemDTO =new ItemDTO(txtItemCode.getText(),txtDescription.getText(),txtPackSize.getText()
                ,Double.parseDouble(txtUnitprice.getText()),Integer.parseInt(txtQuantityOnHand.getText()),Double.parseDouble(txtDiscount.getText()));

        if (itemBo.addItem(itemDTO)){
            new Alert(Alert.AlertType.CONFIRMATION, "Saved..").show();
        }else {
            new Alert(Alert.AlertType.WARNING, "Try Again..").show();
        }

        txtItemCode.clear();
        txtDescription.clear();
        txtPackSize.clear();
        txtUnitprice.clear();
        txtQuantityOnHand.clear();
        txtDiscount.clear();
    }

    public void goToTheMangment(ActionEvent actionEvent) {
    }
}
