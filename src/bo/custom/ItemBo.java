package bo.custom;

import bo.SuperBO;
import dto.ItemDTO;

import java.sql.SQLException;
import java.util.List;

public interface ItemBo extends SuperBO {
    public boolean addItem(ItemDTO itemDTO) throws SQLException, ClassNotFoundException;

    public ItemDTO getItem(String id) throws SQLException, ClassNotFoundException;

    public boolean updateItem(ItemDTO itemDTO) throws SQLException, ClassNotFoundException;

    public boolean deleteUpdate(String id) throws SQLException, ClassNotFoundException;

    public List<String> getItemIdes() throws SQLException, ClassNotFoundException;
}
