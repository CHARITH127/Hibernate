package controller;

import bo.custom.impl.ItemRatesBOImpl;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import dto.ItemQtyRate;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemMoveableFormController {

    public BarChart ItemBarChart;
    public CategoryAxis x;
    public NumberAxis y;

    public void initialize(){


        XYChart.Series set = new XYChart.Series();
        List<ItemQtyRate> rates = new ArrayList<>();
        try {
            rates = new ItemRatesBOImpl().getItemCode();

            for (ItemQtyRate temp :rates) {
                set.getData().add(new XYChart.Data(temp.getItemCode(),temp.getItemQty()));

            }

            ItemBarChart.getData().addAll(set);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
