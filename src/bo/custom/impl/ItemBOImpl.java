package bo.custom.impl;

import bo.custom.ItemBo;
import dao.DAOFactory;
import dao.custom.ItemDAO;
import dto.ItemDTO;
import entity.Item;

import java.sql.SQLException;
import java.util.List;

public class ItemBOImpl implements ItemBo {
    ItemDAO itemDAO = (ItemDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ITEM);

    @Override
    public boolean addItem(ItemDTO itemDTO) throws SQLException, ClassNotFoundException {
        return itemDAO.add(new Item(itemDTO.getItemCode(), itemDTO.getDescription(), itemDTO.getPackSize(), itemDTO.getUnitPrice(), itemDTO.getQtyOnHand(), itemDTO.getDiscount()));
    }

    @Override
    public ItemDTO getItem(String id) throws SQLException, ClassNotFoundException {
        Item search = itemDAO.search(id);
        return new ItemDTO(search.getItemCode(), search.getDescription(), search.getPackSize(), search.getUnitPrice(), search.getQtyOnHand(), search.getDiscount());
    }

    @Override
    public boolean updateItem(ItemDTO itemDTO) throws SQLException, ClassNotFoundException {
        return itemDAO.update(new Item(itemDTO.getItemCode(), itemDTO.getDescription(), itemDTO.getPackSize(), itemDTO.getUnitPrice(), itemDTO.getQtyOnHand(), itemDTO.getDiscount()));
    }

    @Override
    public boolean deleteUpdate(String id) throws SQLException, ClassNotFoundException {
        return itemDAO.delete(id);
    }

    @Override
    public List<String> getItemIdes() throws SQLException, ClassNotFoundException {
        return itemDAO.getItemIdeas();
    }
}
