package com.munaf.order_service.controllers;

import com.munaf.order_service.dtos.OrderRequestDto;
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
    public String check() {
        return "This is Order Service";
    }


    @GetMapping("/{orderId}")
    public ResponseEntity<OrderRequestDto> getOrderById(@PathVariable Long orderId) {
        return ResponseEntity.ok(orderService.getOrderById(orderId));
    }

    @GetMapping()
    public ResponseEntity<List<OrderRequestDto>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @PostMapping("create-order")
    public ResponseEntity<OrderRequestDto> createOrder(@RequestBody OrderRequestDto orderRequestDto) {
        return ResponseEntity.ok(orderService.createOrder(orderRequestDto));
    }


}
