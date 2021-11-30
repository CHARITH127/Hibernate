package dto;

public class CustomerChart {
    private String customerId;
    private double cost;

    public CustomerChart(String customerId, double cost) {
        this.customerId = customerId;
        this.cost = cost;
    }

    public CustomerChart() {
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
}
