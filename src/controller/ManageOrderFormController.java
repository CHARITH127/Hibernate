package controller;

import bo.BoFactory;
import bo.custom.CustomerBO;
import bo.custom.impl.OrderManagemntControllerBOImpl;
import bo.custom.PurchaseOrderBO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import dto.CustomerDTO;
import view.tm.ManageOrderTm;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ManageOrderFormController {

    public ComboBox cmbCustomerId;
    public JFXButton btnSearchCustomer;
    public JFXTextField txtCustomerTitle;
    public JFXTextField txtCustomerName;
    public JFXTextField txtCustomerAddress;
    public JFXTextField txtCustomerCity;
    public JFXTextField txtCustomerProvince;
    public JFXTextField txtCustomerPostalCode;
    public ComboBox cmdOrderId;
    public JFXButton btnSearchorder;
    public TableView tblItemTables;
    public TableColumn colItemCode;
    public TableColumn colQty;
    public TableColumn colDiscount;
    public JFXButton btnDelete;
    public PurchaseOrderBO orderBO = (PurchaseOrderBO) BoFactory.getBoFactory().getBo(BoFactory.BoTypes.PurchaseOrder);

    public CustomerBO customerBO = (CustomerBO) BoFactory.getBoFactory().getBo(BoFactory.BoTypes.Customer);

    public void initialize(){
        colItemCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colDiscount.setCellValueFactory(new PropertyValueFactory<>("discount"));

        try {
            loadAllCustomerIds();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void searchCustomerDetails(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String id = (String) cmbCustomerId.getValue();
        CustomerDTO customerDTO = customerBO.getCustomer(id);
        if (customerDTO ==null){
            new Alert(Alert.AlertType.WARNING, "Empty Result Set").show();
        }else {
            txtCustomerTitle.setText(customerDTO.getCutTitle());
            txtCustomerName.setText(customerDTO.getCustomerName());
            txtCustomerAddress.setText(customerDTO.getCustomerAddress());
            txtCustomerCity.setText(customerDTO.getCustomerCity());
            txtCustomerProvince.setText(customerDTO.getCustomerProvince());
            txtCustomerPostalCode.setText(customerDTO.getCustomerPostalCode());
        }
        cmdOrderId.getItems().clear();
        loadAllOrders();
    }

    public void SetItemsToTheTable(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String orerId = (String) cmdOrderId.getValue();
        ObservableList<ManageOrderTm> itemList = new OrderManagemntControllerBOImpl().getItems(orerId);
       tblItemTables.setItems(itemList);
    }

    public void DeleteCustomerOrder(ActionEvent actionEvent) throws SQLException, ClassNotFoundException, IOException {
        String orderId = (String) cmdOrderId.getValue();

        ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
        ButtonType no = new ButtonType("No",ButtonBar.ButtonData.CANCEL_CLOSE);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Are you sure to delete this Order",yes,no);
        alert.setTitle("Confirmatin Alert");

        Optional<ButtonType> result= alert.showAndWait();

        if(result.orElse(no)==yes){
            if (new OrderManagemntControllerBOImpl().deleteOrderDetailTable(orderId)){
                if (new OrderManagemntControllerBOImpl().deleteOrderTable(orderId)){
                    new Alert(Alert.AlertType.CONFIRMATION, "Deleted").show();
                }else{
                    new Alert(Alert.AlertType.WARNING, "Try Again").show();
                }
            }
        }else{
            return;
        }


        clearAll();

    }

    private void clearAll() {
        txtCustomerName.clear();
        txtCustomerTitle.clear();
        txtCustomerAddress.clear();
        txtCustomerCity.clear();
        txtCustomerProvince.clear();
        txtCustomerPostalCode.clear();
        cmdOrderId.getItems().clear();
        cmbCustomerId.getItems().clear();
        initialize();
        tblItemTables.getItems().clear();
        tblItemTables.refresh();
    }

    public void loadAllCustomerIds() throws SQLException, ClassNotFoundException {
        List<String> custIds = new OrderManagemntControllerBOImpl().getCustomerIds();
        cmbCustomerId.getItems().addAll(custIds);

    }

    public void loadAllOrders() throws SQLException, ClassNotFoundException {
        String id = (String) cmbCustomerId.getValue();
        List<String> orderId = orderBO.getOrderIdes(id);
        cmdOrderId.getItems().addAll(orderId);
    }



}
