package dto;

import java.util.ArrayList;

public class OrderDTO {
    private String orderID;
    private String orderDate;
    private String custID;
    private double cost;
    private ArrayList<OrderDetailsDTO> items ;

    public OrderDTO() {
    }

    public OrderDTO(String orderID, String orderDate, String custID, double cost, ArrayList<OrderDetailsDTO> items) {
        this.orderID = orderID;
        this.orderDate = orderDate;
        this.custID = custID;
        this.cost = cost;
        this.items = items;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getCustID() {
        return custID;
    }

    public void setCustID(String custID) {
        this.custID = custID;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public ArrayList<OrderDetailsDTO> getItems() {
        return items;
    }

    public void setItems(ArrayList<OrderDetailsDTO> items) {
        this.items = items;
    }
}
