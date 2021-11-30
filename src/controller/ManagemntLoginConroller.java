package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class ManagemntLoginConroller {
    public JFXTextField txtuserName;
    public JFXPasswordField txtPassword;
    public JFXButton btnManagmentLodin;
    public AnchorPane loginContext;

    public void GoManagmentLoginForm(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/ManagementForm.fxml");
        Parent load = FXMLLoader.load(resource);
        load.getStylesheets().add("/StileSheet.css");
        Stage window =(Stage) loginContext.getScene().getWindow();
        window.setScene(new Scene(load));
    }
}
