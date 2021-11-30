package controller;

import bo.BoFactory;
import bo.custom.PurchaseOrderBO;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import dto.CustomerChart;

import java.sql.SQLException;
import java.util.List;

public class CustomerViseIncomeFormController {

    PurchaseOrderBO orderBO = (PurchaseOrderBO) BoFactory.getBoFactory().getBo(BoFactory.BoTypes.PurchaseOrder);

    @FXML
    private BarChart<?, ?> customerWiseIncomeChart;

    @FXML
    private CategoryAxis x;

    @FXML
    private NumberAxis y;

    public void initialize(){
        try {
            setData();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void setData() throws SQLException, ClassNotFoundException {

        List<CustomerChart> details =orderBO.getCustomerAndOrderDetails();

        XYChart.Series set = new XYChart.Series();


        for (CustomerChart temp :details) {
            set.getData().add(new XYChart.Data(temp.getCustomerId(),temp.getCost()));

        }
        customerWiseIncomeChart.getData().addAll(set);

    }

}
