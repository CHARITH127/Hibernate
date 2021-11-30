package bo.custom.impl;

import bo.BoFactory;
import bo.custom.ItemBo;
import bo.custom.ItemRatesBO;
import bo.custom.OrderDetailsControllerBO;
import bo.custom.OrderManagementBO;
import dto.ItemQtyRate;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemRatesBOImpl implements ItemRatesBO {
    public ItemBo itemBo = (ItemBo) BoFactory.getBoFactory().getBo(BoFactory.BoTypes.Item);
    OrderManagementBO managementBO = (OrderManagementBO) BoFactory.getBoFactory().getBo(BoFactory.BoTypes.OrderManagemnt);
    OrderDetailsControllerBO controllerBO = (OrderDetailsControllerBO) BoFactory.getBoFactory().getBo(BoFactory.BoTypes.OrderDetails);
    int quantity;

    public List<ItemQtyRate> getItemCode() throws SQLException, ClassNotFoundException {
        List<String> itemIdes = new ArrayList<>();
        itemIdes = itemBo.getItemIdes();
        List<String> itemCodes = new ArrayList<>();
        itemCodes = managementBO.getAllItemCodesOfOrderDetail();
        List<ItemQtyRate> ItemCodeInItemDetails = new ArrayList<>();

        for (int i = 0; i < itemIdes.size(); i++) {
            for (int j = 0; j < itemCodes.size(); j++) {
                if (itemIdes.get(i).equals(itemCodes.get(j))) {
                    quantity = 0;
                    System.out.println();
                    getItemQuantity(itemCodes.get(j));
                    System.out.println(itemCodes.get(j) + " " + quantity);
                    ItemCodeInItemDetails.add(new ItemQtyRate(itemCodes.get(j), quantity));
                    break;
                } else {

                }

            }
        }

        return ItemCodeInItemDetails;
    }

    public int getItemQuantity(String itemCode) throws SQLException, ClassNotFoundException {
        return controllerBO.getItemQuantity(itemCode);

    }

}


