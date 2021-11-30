package dao.custom;

import dao.CrudDAO;
import entity.Orders;

import java.sql.SQLException;
import java.util.List;

public interface OrderDAO extends CrudDAO<Orders,String> {
    String genarateOrderId() throws SQLException, ClassNotFoundException;
    List<String> getOrderIdes(String custId) throws SQLException, ClassNotFoundException;
}
