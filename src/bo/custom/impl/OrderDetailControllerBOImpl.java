package bo.custom.impl;

import bo.custom.OrderDetailsControllerBO;
import dao.DAOFactory;
import dao.custom.OrderDetailsDAO;

import java.sql.SQLException;

public class OrderDetailControllerBOImpl implements OrderDetailsControllerBO {

    int quantity;
    OrderDetailsDAO detailsDAO = (OrderDetailsDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDERDETAILS);

    public int getItemQuantity(String itemCode) throws SQLException, ClassNotFoundException {
        return quantity = detailsDAO.getItemQuantity(itemCode);
    }


}
