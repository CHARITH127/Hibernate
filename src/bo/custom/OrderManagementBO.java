package bo.custom;

import bo.SuperBO;
import javafx.collections.ObservableList;
import view.tm.ManageOrderTm;

import java.sql.SQLException;
import java.util.List;

public interface OrderManagementBO  extends SuperBO {
    public List<String> getCustomerIds() throws SQLException, ClassNotFoundException;

    public ObservableList<ManageOrderTm> getItems(String orderId) throws SQLException, ClassNotFoundException;

    public boolean deleteOrderTable(String orderId) throws SQLException, ClassNotFoundException;

    public boolean deleteOrderDetailTable(String orderrId) throws SQLException, ClassNotFoundException;

    public List<String> getAllItemCodesOfOrderDetail() throws SQLException, ClassNotFoundException;
}
