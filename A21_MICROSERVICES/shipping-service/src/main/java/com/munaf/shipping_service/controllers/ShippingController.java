package com.munaf.shipping_service.controllers;

import com.munaf.shipping_service.dtos.OrderDto;
import com.munaf.shipping_service.services.ShippingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ShippingController {

    private final ShippingService shippingService;


    public ShippingController(ShippingService shippingService) {
        this.shippingService = shippingService;
    }


    @PostMapping("/order/{orderId}")
    public ResponseEntity<OrderDto> shippingOrder(@PathVariable Long orderId) {
        return ResponseEntity.ok(shippingService.shippingOrder(orderId));
    }
}
