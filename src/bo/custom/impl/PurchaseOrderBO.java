package bo.custom.impl;

import dao.DAOFactory;
import dao.custom.ItemDAO;
import dao.custom.OrderDAO;
import dao.custom.OrderDetailsDAO;
import dao.custom.impl.ItemDAOImpl;
import db.FactoryConfiguration;
import dto.CustomerChart;
import dto.CustomerDTO;
import dto.OrderDTO;
import dto.OrderDetailsDTO;
import entity.Customer;
import entity.Item;
import entity.OrderDetails;
import entity.Orders;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import view.tm.ManageOrderTm;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PurchaseOrderBO implements bo.custom.PurchaseOrderBO {
    OrderDetailsDAO orderDetailsDAO = (OrderDetailsDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDERDETAILS);
    ItemDAO itemDAO = (ItemDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ITEM);
    OrderDAO orderDAO = (OrderDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDER);

    @Override
    public String orderId() throws SQLException, ClassNotFoundException {
        return orderDAO.genarateOrderId();
    }

    @Override
    public boolean placeOder(OrderDTO orderDTO, CustomerDTO customerDTO) throws SQLException, ClassNotFoundException {
        Customer customer = new Customer();
        customer.setCustomerID(customerDTO.getCustomerID());
        customer.setCutTitle(customerDTO.getCutTitle());
        customer.setCustomerName(customerDTO.getCustomerName());
        customer.setCustomerAddress(customerDTO.getCustomerAddress());
        customer.setCustomerCity(customerDTO.getCustomerCity());
        customer.setCustomerProvince(customerDTO.getCustomerProvince());
        customer.setCustomerPostalCode(customerDTO.getCustomerPostalCode());

        Orders orders = new Orders();
        orders.setOrderID(orderDTO.getOrderID());
        orders.setCost(orderDTO.getCost());
        orders.setOrderDate(orderDTO.getOrderDate());
        customer.getOrders().add(orders);
        orders.setCustomer(customer);

        orderDAO.add(orders);
        saveOrderDetails(customer,orders,orderDTO.getItems());
        return true;
    }

    @Override
    public boolean saveOrderDetails(Customer customer,Orders orders, ArrayList<OrderDetailsDTO> items) throws SQLException, ClassNotFoundException {
        for (OrderDetailsDTO item : items) {
            OrderDetails details = new OrderDetails();
            Item search = new ItemDAOImpl().search(item.getItemCode());
            details.setDiscount(item.getDiscount());
            details.setOrderQty(item.getOrderQty());
            details.setItem(search);
            details.setOrders(orders);
            orderDetailsDAO.add(details);
            upadateQty(item.getItemCode(),item.getOrderQty());
        }
        return true;
    }

    @Override
    public boolean upadateQty(String itemCode, int qty) throws SQLException, ClassNotFoundException {
        return itemDAO.upadateQty(itemCode, qty);
    }

    @Override
    public List<String> getOrderIdes(String custId) throws SQLException, ClassNotFoundException {
        return orderDAO.getOrderIdes(custId);

    }

    @Override
    public List<CustomerChart> getCustomerAndOrderDetails() throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        NativeQuery sqlQuery = session.createSQLQuery("SELECT * FROM Orders");
        sqlQuery.addEntity(Orders.class);
        List<CustomerChart> list = sqlQuery.list();
        transaction.commit();
        session.close();

        ObservableList<ManageOrderTm> itemsDetails = FXCollections.observableArrayList();
        List<CustomerChart> cutomerIncome = new ArrayList<>();
        for (CustomerChart customerChart : list) {
            cutomerIncome.add(new CustomerChart(customerChart.getCustomerId(),customerChart.getCost()));
        }
        return cutomerIncome;

    }
}
