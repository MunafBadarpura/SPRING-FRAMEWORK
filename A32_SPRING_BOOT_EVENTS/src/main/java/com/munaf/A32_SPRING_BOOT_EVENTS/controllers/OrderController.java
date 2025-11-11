package com.munaf.A32_SPRING_BOOT_EVENTS.controllers;

import com.munaf.A32_SPRING_BOOT_EVENTS.services.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/create/{orderId}")
    public String createOrder(@PathVariable Long orderId) {
        orderService.createOrder(orderId);
        return "Order created with order id " + orderId;
    }

}
