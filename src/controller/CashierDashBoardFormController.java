package controller;

import bo.BoFactory;
import bo.custom.CustomerBO;
import bo.custom.ItemBo;
import bo.custom.PurchaseOrderBO;
import com.jfoenix.controls.JFXButton;
import dao.DAOFactory;
import dao.custom.ItemDAO;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import dto.*;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanArrayDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import view.tm.Carttm;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class CashierDashBoardFormController {
    public TextField txtCustomerAddress;
    public TextField txtCustomerID;
    public JFXButton btnAddNewCustomer;
    public TextField txtCustomerCity;
    public TextField txtCustomerProvince;
    public TextField txtCustomerPostalCode;
    public ComboBox cmbItemCode;
    public TextField txtItemDescription;
    public TextField txtItemPackSize;
    public TextField txtItemUnitPrice;
    public TextField txtItemQtyOnHand;
    public JFXButton btnAddToCart;
    public Label lblOrderID;
    public Label lblDate;
    public Label lblTime;
    public TableView tblOrderTable;
    public TableColumn colItemCode;
    public TableColumn colItemDescription;
    public TableColumn colUnitPrice;
    public TableColumn colQty;
    public TableColumn colTotal;
    public TextField txtItemQtyOnCustomer;
    public TextField txtItemDiscount;
    public JFXButton btnClear;
    public JFXButton btnConfermOrder;
    public Label lblTotalCost;
    public JFXButton btnPayment;
    public JFXButton btnManageOrder;
    public TextField txtCutomerTitle;
    public TextField txtCustomerName;
    public TableColumn colDiscount;
    public JFXButton btnManagemnt;
    public AnchorPane CashierContext;
    String icode;
    int selectRow = -1;
    public PurchaseOrderBO orderBO = (PurchaseOrderBO) BoFactory.getBoFactory().getBo(BoFactory.BoTypes.PurchaseOrder);
    public CustomerBO customerBO = (CustomerBO) BoFactory.getBoFactory().getBo(BoFactory.BoTypes.Customer);
    public ItemBo itemBo = (ItemBo) BoFactory.getBoFactory().getBo(BoFactory.BoTypes.Item);
    public ItemDAO itemDAO = (ItemDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ITEM);

    public void initialize() {

        colItemCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        colItemDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        colDiscount.setCellValueFactory(new PropertyValueFactory<>("discount"));

        loadDateAndTime();
        setOrderId();


        try {
            loadItemIdes();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        //Listners------------------------------------

        cmbItemCode.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == null) {
                return;
            }
            try {
                setItemData(String.valueOf(newValue));
                btnAddToCart.setVisible(true);
                icode = String.valueOf(newValue);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        tblOrderTable.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            selectRow = (int) newValue;
        });

        //Listners------------------------------------

        btnAddNewCustomer.setVisible(false);
        btnAddToCart.setVisible(false);
    }

    public void AddNewCustomerOnAction(ActionEvent actionEvent) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("../view/AddCustomer.fxml"));
        load.getStylesheets().add("/StileSheet.css");
        Scene scene = new Scene(load);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    ObservableList<Carttm> obList = FXCollections.observableArrayList();

    public void AddItemsToTheTable(ActionEvent actionEvent) {
        String itemCode = icode;
        String ItemDiscription = txtItemDescription.getText();
        double UnitPrice = Double.parseDouble(txtItemUnitPrice.getText());
        int qtyOnHand = Integer.parseInt(txtItemQtyOnHand.getText());
        int qty = Integer.parseInt(txtItemQtyOnCustomer.getText());
        double discount = Double.parseDouble(txtItemDiscount.getText()) * qty;
        double total = (UnitPrice * qty) - discount;

        if (qtyOnHand < qty) {
            new Alert(Alert.AlertType.WARNING, "Invalid QTY").show();
            return;
        }

        Carttm carttm = new Carttm(
                itemCode,
                ItemDiscription,
                UnitPrice,
                qty,
                total,
                discount
        );

        int rowNumber = isExists(carttm);
        if (rowNumber == -1) {
            obList.add(carttm);
        } else {

            Carttm temp = obList.get(rowNumber);
            Carttm newTm = new Carttm(
                    temp.getItemCode(),
                    temp.getDescription(),
                    temp.getUnitPrice(),
                    temp.getQty() + qty,
                    temp.getTotal() + total,
                    temp.getDiscount() + discount
            );
            if (qtyOnHand < temp.getQty() + qty) {
                new Alert(Alert.AlertType.WARNING, "Invalid QTY").show();
                return;
            }
            obList.remove(rowNumber);
            obList.add(newTm);
        }


        tblOrderTable.setItems(obList);
        calculateCost();

    }

    public void ClearItemsOnTable(ActionEvent actionEvent) {
        if (selectRow == -1) {
            new Alert(Alert.AlertType.WARNING, "Please Select a row").show();
        } else {
            obList.remove(selectRow);
            calculateCost();
            tblOrderTable.refresh();
        }
    }

    public void ConfermOrderOnAction(ActionEvent actionEvent) throws IOException {

        ArrayList<OrderDetailsDTO> orderItems = new ArrayList<>();
        double total = 0;

        for (Carttm temp : obList
        ) {
            total += temp.getTotal();
            orderItems.add(new OrderDetailsDTO(
                    temp.getItemCode(),
                    lblOrderID.getText(),
                    temp.getQty(),
                    temp.getDiscount()
            ));
        }

        OrderDTO orderDTO = new OrderDTO(
                lblOrderID.getText(),
                lblDate.getText(),
                txtCustomerID.getText(),
                total,
                orderItems
        );

        CustomerDTO customerDTO = new CustomerDTO(txtCustomerID.getText(), txtCutomerTitle.getText(), txtCustomerName.getText(), txtCustomerAddress.getText(), txtCustomerCity.getText(), txtCustomerProvince.getText(), txtCustomerPostalCode.getText());
        try {

            if (orderBO.placeOder(orderDTO,customerDTO)) {
                Parent load = FXMLLoader.load(getClass().getResource("../view/PayemntInforForm.fxml"));
                load.getStylesheets().add("/StileSheet.css");
                Scene scene = new Scene(load);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();

                showCustomerBill();
                setOrderId();

            } else {
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Allclear();

    }

    private void Allclear() throws IOException {
        txtCustomerID.clear();
        txtCutomerTitle.clear();
        txtCustomerName.clear();
        txtCustomerAddress.clear();
        txtCustomerCity.clear();
        txtCustomerProvince.clear();
        txtCustomerPostalCode.clear();
        txtItemDescription.clear();
        txtItemPackSize.clear();
        txtItemUnitPrice.clear();
        txtItemDiscount.clear();
        txtItemQtyOnHand.clear();
        txtItemQtyOnCustomer.clear();
        tblOrderTable.getItems().clear();
        tblOrderTable.refresh();
        cmbItemCode.setValue(null);
        initialize();

        lblTotalCost.setText("0.00/=");

    }


    public void ManageOrderOnAction(ActionEvent actionEvent) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("../view/ManageOrderForm.fxml"));
        load.getStylesheets().add("/StileSheet.css");
        Scene scene = new Scene(load);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    public void LoadCustomerDetails(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        CustomerDTO customerDTO = customerBO.getCustomer(txtCustomerID.getText());
        if (customerDTO == null) {
            new Alert(Alert.AlertType.WARNING, "You have to Add this Customer").showAndWait();
            btnAddNewCustomer.setVisible(true);
        } else {
            txtCutomerTitle.setText(customerDTO.getCutTitle());
            txtCustomerName.setText(customerDTO.getCustomerName());
            txtCustomerAddress.setText(customerDTO.getCustomerAddress());
            txtCustomerCity.setText(customerDTO.getCustomerCity());
            txtCustomerProvince.setText(customerDTO.getCustomerProvince());
            txtCustomerPostalCode.setText(customerDTO.getCustomerPostalCode());
        }
    }

    public void loadItemIdes() throws SQLException, ClassNotFoundException {
        List<String> ItemIdes = itemDAO.getItemIdeas();
        cmbItemCode.getItems().addAll(ItemIdes);
    }

    public void setItemData(String idemCode) throws SQLException, ClassNotFoundException {
        ItemDTO itemDTO = itemBo.getItem(idemCode);
        if (itemDTO == null) {
            new Alert(Alert.AlertType.WARNING, "Empty Result Set");
        } else {
            txtItemDescription.setText(itemDTO.getDescription());
            txtItemPackSize.setText(itemDTO.getPackSize());
            txtItemUnitPrice.setText(String.valueOf(itemDTO.getUnitPrice()));
            txtItemDiscount.setText(String.valueOf(itemDTO.getDiscount()));
            txtItemQtyOnHand.setText(String.valueOf(itemDTO.getQtyOnHand()));
        }
    }

    private void loadDateAndTime() {
        Date date = new Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        lblDate.setText(f.format(date));

        // load Time
        Timeline time = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            LocalTime currentTime = LocalTime.now();
            lblTime.setText(
                    currentTime.getHour() + " : " + currentTime.getMinute() +
                            " : " + currentTime.getSecond()
            );
        }),
                new KeyFrame(Duration.seconds(1))
        );
        time.setCycleCount(Animation.INDEFINITE);
        time.play();
    }

    private int isExists(Carttm carttm) {
        for (int i = 0; i < obList.size(); i++) {
            if (carttm.getItemCode().equals(obList.get(i).getItemCode())) {
                return i;
            }
        }
        return -1;
    }

    private void calculateCost() {

        double netTotal = 0;
        for (Carttm tm : obList) {
            netTotal += tm.getTotal();
        }
        lblTotalCost.setText(netTotal + "/=");
    }

    private void setOrderId() {
        try {
            lblOrderID.setText(orderBO.orderId());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void GoToLoginManagemntLogin(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/ManagemntLogin.fxml");
        Parent load = FXMLLoader.load(resource);
        load.getStylesheets().add("/StileSheet.css");
        Stage window = (Stage) CashierContext.getScene().getWindow();
        window.setScene(new Scene(load));
    }

    public void showCustomerBill(){
        try {
            JasperDesign load = JRXmlLoader.load(this.getClass().getResourceAsStream("/view/reports/Bill.jrxml"));
            JasperReport compileReport = JasperCompileManager.compileReport(load);

            ObservableList<Carttm> carttms = tblOrderTable.getItems();

            String custName = txtCustomerName.getText();
            String netTotal = lblTotalCost.getText();

            HashMap map = new HashMap();
            map.put("CustomerName",custName);
            map.put("netTotal",netTotal);

            JasperPrint jasperPrint = JasperFillManager.fillReport(compileReport, map, new JRBeanArrayDataSource(carttms.toArray()));
            JasperViewer.viewReport(jasperPrint);
        } catch (JRException e) {
            e.printStackTrace();
        }
    }

}
