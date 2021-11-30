package dao.custom.impl;

import dao.custom.CustomerDAO;
import db.FactoryConfiguration;
import entity.Customer;
import entity.Orders;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl  implements CustomerDAO {
    @Override
    public boolean add(Customer customer) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.save(customer);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not Supported Yet");
    }

    @Override
    public boolean update(Customer customer) throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not Supported Yet");
    }

    @Override
    public Customer search(String code) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Customer customer = session.get(Customer.class, code);
        transaction.commit();
        session.close();
        return customer;
    }

    @Override
    public ArrayList<Customer> getAll() throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not Supported Yet");
    }

    @Override
    public List<String> getCustomerIds() throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        String sql = "SELECT * FROM Orders";
        NativeQuery sqlQuery = session.createSQLQuery(sql);
        sqlQuery.addEntity(Orders.class);

        List<Orders> list = sqlQuery.list();
        List<String> custIdes = new ArrayList<>();

        for (Orders orders : list) {
            custIdes.add(orders.getCustomer().getCustomerID());
        }

        transaction.commit();
        session.close();

        return custIdes;
    }
}
