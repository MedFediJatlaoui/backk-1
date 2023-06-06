package tn.esprit.teriak.Controllers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.teriak.Dto.OrderRequestDto;
import tn.esprit.teriak.IServices.OrderService;

import org.springframework.http.HttpStatus;
@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<String> createOrder(@RequestBody OrderRequestDto orderRequest) {
        try {
            orderService.saveOrder(orderRequest);
            HttpHeaders headers = new HttpHeaders();
            headers.add("Access-Control-Allow-Origin", "http://127.0.0.1:4200");
            return new ResponseEntity<>("Order created successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
