package dao.custom;

import dao.CrudDAO;
import entity.OrderDetails;
import javafx.collections.ObservableList;
import dto.OrderDetailsDTO;
import view.tm.ManageOrderTm;

import java.sql.SQLException;
import java.util.List;

public interface OrderDetailsDAO extends CrudDAO<OrderDetails, String> {
    public ObservableList<ManageOrderTm> getItems(String orderId) throws SQLException, ClassNotFoundException;

    public List<String> getAllItemCodesOfOrderDetail() throws SQLException, ClassNotFoundException;

    public int getItemQuantity(String itemCode) throws SQLException, ClassNotFoundException;
}
