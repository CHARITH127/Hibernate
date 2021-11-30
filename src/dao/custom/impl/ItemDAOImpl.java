package dao.custom.impl;

import dao.custom.ItemDAO;
import db.FactoryConfiguration;
import entity.Item;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemDAOImpl implements ItemDAO {
    @Override
    public boolean add(Item item) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.save(item);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(String code) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Item item = session.get(Item.class, code);
        session.delete(item);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Item item) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.update(item);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public Item search(String code) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Item item = session.get(Item.class, code);
        transaction.commit();
        session.close();
        return item;
    }

    @Override
    public ArrayList<Item> getAll() throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not Supported Yet");
    }

    @Override
    public List<String> getItemIdeas() throws SQLException, ClassNotFoundException {

        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        String sql = "SELECT * FROM Item";
        NativeQuery sqlQuery = session.createSQLQuery(sql);
        sqlQuery.addEntity(Item.class);

        List<Item> list = sqlQuery.list();
        List<String> itemIdes = new ArrayList<>();

        for (Item item : list) {
            itemIdes.add(item.getItemCode());
        }

        transaction.commit();
        session.close();
        return itemIdes;
    }

    @Override
    public boolean upadateQty(String itemCode, int qty) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("UPDATE Item SET QtyOnHand = (QtyOnHand- :qtys) WHERE ItemCode = :itemCodes");
        query.setParameter("qtys",qty);
        query.setParameter("itemCodes",itemCode);
        int i = query.executeUpdate();
        if (i>0) {
            transaction.commit();
            session.close();
            return true;
        }else {
            return false;
        }
    }
}
