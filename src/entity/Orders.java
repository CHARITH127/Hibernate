package entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Orders {
    @Id
    private String orderID;
    private String orderDate;
    private double cost;
    @ManyToOne
    private Customer customer;

    @OneToMany(mappedBy = "orders")
    private List<OrderDetails> orderList = new ArrayList<>();

    public Orders() {
    }

    public Orders(String orderID, String orderDate, double cost, Customer customer, List<OrderDetails> orderList) {
        this.orderID = orderID;
        this.orderDate = orderDate;
        this.cost = cost;
        this.customer = customer;
        this.orderList = orderList;
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

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<OrderDetails> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<OrderDetails> orderList) {
        this.orderList = orderList;
    }
}
