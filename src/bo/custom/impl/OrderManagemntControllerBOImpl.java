package bo.custom.impl;

import bo.custom.OrderManagementBO;
import dao.DAOFactory;
import dao.custom.CustomerDAO;
import dao.custom.OrderDAO;
import dao.custom.OrderDetailsDAO;
import javafx.collections.ObservableList;
import view.tm.ManageOrderTm;

import java.sql.SQLException;
import java.util.List;

public class OrderManagemntControllerBOImpl implements OrderManagementBO {
    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);
    OrderDAO orderDAO = (OrderDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDER);
    OrderDetailsDAO detailsDAO = (OrderDetailsDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDERDETAILS);

    @Override
    public List<String> getCustomerIds() throws SQLException, ClassNotFoundException {
        return customerDAO.getCustomerIds();
    }

    @Override
    public ObservableList<ManageOrderTm> getItems(String orderId) throws SQLException, ClassNotFoundException {
        return detailsDAO.getItems(orderId);
    }

    @Override
    public boolean deleteOrderTable(String orderId) throws SQLException, ClassNotFoundException {
        return orderDAO.delete(orderId);
    }

    @Override
    public boolean deleteOrderDetailTable(String orderrId) throws SQLException, ClassNotFoundException {
        return detailsDAO.delete(orderrId);
    }

    @Override
    public List<String> getAllItemCodesOfOrderDetail() throws SQLException, ClassNotFoundException {
        return detailsDAO.getAllItemCodesOfOrderDetail();
    }


}
