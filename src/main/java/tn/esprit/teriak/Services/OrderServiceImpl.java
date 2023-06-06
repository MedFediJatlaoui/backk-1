package tn.esprit.teriak.Services;

import org.springframework.stereotype.Service;
import tn.esprit.teriak.Dto.OrderRequestDto;
import tn.esprit.teriak.Entities.Order;
import tn.esprit.teriak.Entities.OrderDetail;
import tn.esprit.teriak.IServices.OrderService;
import tn.esprit.teriak.Repositories.OrderDetailRepository;
import tn.esprit.teriak.Repositories.OrderRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;

    public OrderServiceImpl(OrderRepository orderRepository, OrderDetailRepository orderDetailRepository) {
        this.orderRepository = orderRepository;
        this.orderDetailRepository = orderDetailRepository;
    }

    @Override
    public void saveOrder(OrderRequestDto orderRequest) {
        String customerName = orderRequest.getCustomerName();

        // Generate a unique order number
        String orderNumber = generateOrderNumber();

        // Create an Order object
        Order order = new Order();
        order.setOrderNumber(orderNumber);
        order.setCustomerName(customerName);
        order.setOrderDate(LocalDate.now()); // Save the current date

        // Save the Order object to the database
        Order savedOrder = orderRepository.save(order);

        // Save the OrderDetails to the database
        List<OrderDetail> orderDetails = orderRequest.getOrderDetails();
        for (OrderDetail orderDetail : orderDetails) {
            orderDetail.setOrder(savedOrder);
            orderDetailRepository.save(orderDetail);
        }
    }

    private String generateOrderNumber() {
        // Implement your logic to generate a unique order number
        // Example: You can generate a random UUID or use a sequential number with a prefix
        return "CMD-" + UUID.randomUUID().toString();
    }
}
