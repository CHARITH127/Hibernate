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

public class RemoveItemsFormController {
    public TextField txtID;
    public JFXButton btnSearch;
    public TextArea txtDescription;
    public TextField txtPackSize;
    public TextField txtUnitPrice;
    public TextField txtQtyOnHand;
    public JFXButton btnDelete;
    public JFXButton btnCansel;
    public TextField txtDiscount;
    public ItemBo itemBo = new ItemBOImpl();

    public void searchItemDetails(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String itemId = txtID.getText();

        ItemDTO itemDTO = itemBo.getItem(itemId);

        if (itemDTO ==null){
            new Alert(Alert.AlertType.WARNING, "Empty Result Set").show();
        }else{
            txtID.setText(itemDTO.getItemCode());
            txtDescription.setText(itemDTO.getDescription());
            txtPackSize.setText(itemDTO.getPackSize());
            txtUnitPrice.setText(String.valueOf(itemDTO.getUnitPrice()));
            txtQtyOnHand.setText(String.valueOf(itemDTO.getQtyOnHand()));
            txtDiscount.setText(String.valueOf(itemDTO.getDiscount()));
        }
    }

    public void DeleteItem(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if (itemBo.deleteUpdate(txtID.getText())){
            new Alert(Alert.AlertType.CONFIRMATION, "Deleted").show();
            txtID.clear();
            txtDescription.clear();
            txtPackSize.clear();
            txtUnitPrice.clear();
            txtQtyOnHand.clear();
            txtDiscount.clear();
        }else {
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
        }
    }

    public void goTotheManagemnt(ActionEvent actionEvent) {
    }
}
