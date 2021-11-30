package controller;

import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

public class AnnualIncomeReporFormController {

    public BarChart AnnualIncomeChart;
    public CategoryAxis x;
    public NumberAxis y;

    public void initialize(){

        XYChart.Series set = new XYChart.Series();
        set.getData().add(new XYChart.Data("2017",1200000));
        set.getData().add(new XYChart.Data("2018",1400000));
        set.getData().add(new XYChart.Data("2019",1000000));
        set.getData().add(new XYChart.Data("2020",400000));

        AnnualIncomeChart.getData().addAll(set);
    }

}
