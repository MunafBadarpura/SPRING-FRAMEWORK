package com.munaf.order_service.controllers;

import com.munaf.order_service.dtos.OrderDto;
import com.munaf.order_service.services.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/core")
public class OrderController {

    private final OrderService orderService;


    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/check")
    public String check(@RequestHeader("X-User-Id") Long userId) {
        return "This is Order Service, User id : "+ userId;
    }


    @GetMapping("/{orderId}")
    public ResponseEntity<OrderDto> getOrderById(@PathVariable Long orderId) {
        return ResponseEntity.ok(orderService.getOrderById(orderId));
    }

    @GetMapping()
    public ResponseEntity<List<OrderDto>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @PostMapping("create-order")
    public ResponseEntity<OrderDto> createOrder(@RequestBody OrderDto orderDto) {
        return ResponseEntity.ok(orderService.createOrder(orderDto));
    }


    @PostMapping("shipping-order/{orderId}")
    public ResponseEntity<OrderDto> shippingOrder(@PathVariable Long orderId) {
        return ResponseEntity.ok(orderService.shippingOrder(orderId));
    }

}
