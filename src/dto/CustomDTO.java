package dto;

import java.util.Date;

public class CustomDTO {
    private String orderID;
    private Date orderDate;
    private String custId;
    private String itemCode;
    private int orderQty;
    private double discount;

    public CustomDTO() {
    }

    public CustomDTO(String orderID, Date orderDate, String custId, String itemCode, int orderQty, double discount) {
        this.setOrderID(orderID);
        this.setOrderDate(orderDate);
        this.setCustId(custId);
        this.setItemCode(itemCode);
        this.setOrderQty(orderQty);
        this.setDiscount(discount);
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public int getOrderQty() {
        return orderQty;
    }

    public void setOrderQty(int orderQty) {
        this.orderQty = orderQty;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }
}
