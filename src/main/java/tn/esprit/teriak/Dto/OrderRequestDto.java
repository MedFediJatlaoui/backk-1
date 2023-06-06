package tn.esprit.teriak.Dto;

import tn.esprit.teriak.Entities.OrderDetail;
import java.util.List;

public class OrderRequestDto {

    private String customerName;
    private List<OrderDetail> orderDetails;

    // Getters and setters

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }
}
