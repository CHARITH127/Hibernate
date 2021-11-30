package bo.custom.impl;

import bo.custom.CustomerBO;
import dao.DAOFactory;
import dao.custom.CustomerDAO;
import dto.CustomerDTO;
import entity.Customer;

import java.sql.SQLException;

public class CustomerBOImpl implements CustomerBO {
    private final CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);

    @Override
    public boolean addCustomer(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException {
        return customerDAO.add(new Customer(customerDTO.getCustomerID(), customerDTO.getCutTitle(), customerDTO.getCustomerName(), customerDTO.getCustomerAddress(), customerDTO.getCustomerCity(), customerDTO.getCustomerProvince(), customerDTO.getCustomerPostalCode()));
    }

    @Override
    public CustomerDTO getCustomer(String id) throws SQLException, ClassNotFoundException {
        Customer customer = customerDAO.search(id);
        if (customer==null) {
            return null;
        }
        return new CustomerDTO(customer.getCustomerID(), customer.getCutTitle(), customer.getCustomerName(), customer.getCustomerAddress(), customer.getCustomerCity(), customer.getCustomerProvince(), customer.getCustomerPostalCode());
    }
}
