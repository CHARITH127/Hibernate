package bo;

import bo.custom.OrderDetailsControllerBO;
import bo.custom.OrderManagementBO;
import bo.custom.impl.*;
import dao.custom.impl.OrderDetailsDAOImpl;


public class BoFactory {
    private static BoFactory boFactory;

    private BoFactory() {
    }

    public static BoFactory getBoFactory() {
        if (boFactory == null) {
            boFactory = new BoFactory();
        }
        return boFactory;
    }

    public SuperBO getBo(BoTypes boTypes) {
        switch (boTypes) {
            case Item:
                return new ItemBOImpl();
            case Customer:
                return new CustomerBOImpl();
            case ItemRates:
                return new ItemRatesBOImpl();
            case OrderDetails:
                return new OrderDetailControllerBOImpl();
            case PurchaseOrder:
                return new PurchaseOrderBO();
            case OrderManagemnt:
                return new OrderManagemntControllerBOImpl();
            default:
                return null;
        }
    }

    public enum BoTypes {
        Customer, Item, ItemRates, OrderDetails, OrderManagemnt, PurchaseOrder
    }
}
