package dao.custom.impl;

import dao.custom.OrderDetailsDAO;
import db.FactoryConfiguration;
import entity.OrderDetails;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import view.tm.ManageOrderTm;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailsDAOImpl implements OrderDetailsDAO {
    int quantity;
    @Override
    public boolean add(OrderDetails orderDetailsDTO) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.save(orderDetailsDTO);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(String code) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        OrderDetails orderDetails = session.get(OrderDetails.class, code);
        session.delete(orderDetails);
        transaction.commit();
        session.close();
        return true;

    }

    @Override
    public boolean update(OrderDetails orderDetailsDTO) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.update(orderDetailsDTO);
        transaction.commit();
        session.close();
        return false;
    }

    @Override
    public OrderDetails search(String s) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        OrderDetails orderDetails = session.get(OrderDetails.class, s);
        transaction.commit();
        session.close();
        return null;
    }

    @Override
    public ArrayList<OrderDetails> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ObservableList<ManageOrderTm> getItems(String orderId) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        NativeQuery sqlQuery = session.createSQLQuery("SELECT * FROM `OrderDetails` WHERE orders_orderID=:orderIds");
        sqlQuery.addEntity(OrderDetails.class);
        sqlQuery.setParameter("orderIds",orderId);
        List<OrderDetails> list = sqlQuery.list();
        transaction.commit();
        session.close();

        ObservableList<ManageOrderTm> itemsDetails = FXCollections.observableArrayList();
        for (OrderDetails orderDetails : list) {
            itemsDetails.add(new ManageOrderTm(orderDetails.getItem().getItemCode(),orderDetails.getOrderQty(),orderDetails.getDiscount()));
        }
        return itemsDetails;
    }

    @Override
    public List<String> getAllItemCodesOfOrderDetail() throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        NativeQuery sqlQuery = session.createSQLQuery("SELECT * FROM `OrderDetails`");
        sqlQuery.addEntity(OrderDetails.class);
        List<OrderDetails> list = sqlQuery.list();
        transaction.commit();
        session.close();

        List<String> itemsDetails = new ArrayList<>();
        for (OrderDetails orderDetails : list) {
            itemsDetails.add(orderDetails.getItem().getItemCode());
        }
        return itemsDetails;
    }

    public int getItemQuantity(String itemCode) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        NativeQuery sqlQuery = session.createSQLQuery("SELECT * FROM `OrderDetails` WHERE ItemCode=:ItemCodes");
        sqlQuery.addEntity(OrderDetails.class);
        sqlQuery.setParameter("ItemCodes",itemCode);
        List<OrderDetails> list = sqlQuery.list();
        transaction.commit();
        session.close();
        for (OrderDetails orderDetails : list) {
            quantity+=orderDetails.getOrderQty();
        }
        return quantity;
    }

}
