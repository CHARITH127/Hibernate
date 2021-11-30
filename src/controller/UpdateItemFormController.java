package controller;

import bo.custom.impl.ItemBOImpl;
import bo.custom.ItemBo;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import dto.ItemDTO;

import java.sql.SQLException;

public class UpdateItemFormController {

    public TextField txtItemId;
    public Button btnSearch;
    public TextArea txtDescription;
    public TextField txtPackSize;
    public TextField txtUnitPrice;
    public TextField txtQtyOnHand;
    public JFXButton btnUpdate;
    public JFXButton btnCansel;
    public TextField txtDiscount;
    public ItemBo itemBo = new ItemBOImpl();

    public void SearchItemDelais(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String itemId = txtItemId.getText();

        ItemDTO itemDTO = itemBo.getItem(itemId);

        if (itemDTO ==null){
            new Alert(Alert.AlertType.WARNING, "Empty Result Set").show();
        }else{
            txtItemId.setText(itemDTO.getItemCode());
            txtDescription.setText(itemDTO.getDescription());
            txtPackSize.setText(itemDTO.getPackSize());
            txtUnitPrice.setText(String.valueOf(itemDTO.getUnitPrice()));
            txtQtyOnHand.setText(String.valueOf(itemDTO.getQtyOnHand()));
            txtDiscount.setText(String.valueOf(itemDTO.getDiscount()));
        }
    }

    public void UpdateItemsTable(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        ItemDTO itemDTO = new ItemDTO(
          txtItemId.getText(),
          txtDescription.getText(),
          txtPackSize.getText(),
          Double.parseDouble(txtUnitPrice.getText()),
          Integer.parseInt(txtQtyOnHand.getText()),
          Double.parseDouble(txtDiscount.getText())
        );

        if(itemBo.updateItem(itemDTO)){
            new Alert(Alert.AlertType.CONFIRMATION,"Updated..").show();
        }else{
            new Alert(Alert.AlertType.WARNING,"Try Again").show();
        }
    }

    public void goToTheMangemnt(ActionEvent actionEvent) {
    }
}
