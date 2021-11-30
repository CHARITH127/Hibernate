package controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class ManagementFormController {

    public JFXButton btnAnualIncome;
    public JFXButton btnMostMovableItem;
    public JFXButton btnLeastMoveableItem;
    public JFXButton btnCustomerIncome;
    public AnchorPane boardercontext;
    public AnchorPane managementContext;
    public JFXButton btnMonthlyincome;

    public void OpenIncomeReportOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/AnnualIncomeReporForm.fxml");
        Parent load = FXMLLoader.load(resource);
        boardercontext.getChildren().clear();
        boardercontext.getChildren().add(load);
    }

    public void OpenAddNewItemOnAction(ActionEvent actionEvent) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("../view/AddNewItemForm.fxml"));
        load.getStylesheets().add("/StileSheet.css");
        Scene scene = new Scene(load);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    public void OpenModifyItemOnAction(ActionEvent actionEvent) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("../view/UpdateItemForm.fxml"));
        load.getStylesheets().add("/StileSheet.css");
        Scene scene = new Scene(load);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    public void OpenRemoveItemOnAction(ActionEvent actionEvent) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("../view/RemoveItemsForm.fxml"));
        load.getStylesheets().add("/StileSheet.css");
        Scene scene = new Scene(load);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    public void OpenMostMovableItemOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/ItemMoveableForm.fxml");
        Parent load = FXMLLoader.load(resource);
        boardercontext.getChildren().clear();
        boardercontext.getChildren().add(load);
    }

    public void OpenCustomerWiseIncomeOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/CustomerViseIncomeForm.fxml");
        Parent load = FXMLLoader.load(resource);
        boardercontext.getChildren().clear();
        boardercontext.getChildren().add(load);
    }

    public void loadCashierController(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/CashierDashBoardForm.fxml");
        Parent load = FXMLLoader.load(resource);
        load.getStylesheets().add("/StileSheet.css");
        Stage window =(Stage) managementContext.getScene().getWindow();
        window.setScene(new Scene(load));
    }

    public void OpenMonthlyIncomeOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/MonthlyIncome.fxml");
        Parent load = FXMLLoader.load(resource);
        boardercontext.getChildren().clear();
        boardercontext.getChildren().add(load);
    }
}
