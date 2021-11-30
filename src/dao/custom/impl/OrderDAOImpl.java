package dao.custom.impl;

import dao.custom.OrderDAO;
import db.FactoryConfiguration;
import entity.Orders;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDAOImpl implements OrderDAO {
    @Override
    public boolean add(Orders orders) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.save(orders);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(String code) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Orders orders = session.get(Orders.class, code);
        session.delete(orders);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Orders orders) throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not Supported Yet");
    }

    @Override
    public Orders search(String s) throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not Supported Yet");
    }

    @Override
    public ArrayList<Orders> getAll() throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not Supported Yet");
    }

    @Override
    public String genarateOrderId() throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        NativeQuery sqlQuery = session.createSQLQuery("SELECT * FROM Orders ORDER BY orderID DESC LIMIT 1");
        sqlQuery.addEntity(Orders.class);
        List<Orders> result = sqlQuery.list();
        transaction.commit();
        session.close();
        int temp = 0;
        if (result != null) {
            for (Orders orders : result) {
                temp = Integer.parseInt(orders.getOrderID().split("-")[1]);
            }
            temp = temp + 1;
            if (temp <= 9) {
                return "O-00" + temp;
            } else if (temp < 99) {
                return "O-0" + temp;
            } else {
                return "O-" + temp;
            }
        } else {
            return "O-001";
        }
    }

    @Override
    public List<String> getOrderIdes(String custId) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        String sql = "SELECT * FROM Orders";
        NativeQuery sqlQuery = session.createSQLQuery(sql);
        sqlQuery.addEntity(Orders.class);

        List<Orders> list = sqlQuery.list();
        List<String> orderIdes = new ArrayList<>();

        for (Orders orders : list) {
            orderIdes.add(orders.getOrderID());
        }

        transaction.commit();
        session.close();

        return orderIdes;
    }
}
