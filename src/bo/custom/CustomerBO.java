package bo.custom;

import bo.SuperBO;
import dto.CustomerDTO;

import java.sql.SQLException;

public interface CustomerBO extends SuperBO {
    public boolean addCustomer(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException;

    public CustomerDTO getCustomer(String id) throws SQLException, ClassNotFoundException;

}
