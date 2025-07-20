package com.munaf.order_service.controllers;

import com.munaf.order_service.dtos.OrderDto;
import com.munaf.order_service.services.OrderService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/core")
@RefreshScope
public class OrderController {

    @Value("${my.feature}")
    private String myFeature;

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/check-variable")
    public String checkVariable(Long userId) {
        if (myFeature.equals("true")) return "This Feature is enable, variable : "+ myFeature;
        else return "This Feature is disabled, variable : "+ myFeature;
    }

    @GetMapping("/check")
    public String check(@RequestHeader("X-User-Id") Long userId) {
        return "This is Order Service, User id : "+ userId;
    }


    @GetMapping("/check-roles")
    public String checkRoles(@RequestHeader("X-User-Roles") String userRoles) {
        List<String> roles = Arrays.asList(userRoles.split(","));
        return "This is Order Service, User roles: " + roles;
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
