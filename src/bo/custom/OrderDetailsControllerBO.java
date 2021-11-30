package bo.custom;

import bo.SuperBO;

import java.sql.SQLException;

public interface OrderDetailsControllerBO extends SuperBO {
    public int getItemQuantity(String itemCode) throws SQLException, ClassNotFoundException;
}
