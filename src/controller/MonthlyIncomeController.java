package controller;

import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;

public class MonthlyIncomeController {

    public ComboBox cmbYearComboBox;
    public BarChart monthlyIncomeChart;
    public CategoryAxis x;
    public NumberAxis y;

    public void initialize(){
        cmbYearComboBox.getItems().add("2018");
        cmbYearComboBox.getItems().add("2019");
        cmbYearComboBox.getItems().add("2020");

       /* XYChart.Series set = new XYChart.Series();
        set.getData().add(Integer.parseInt("January"),1200000);
        cmbYearComboBox.getItems().addAll(set);*/
    }
}
