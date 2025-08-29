package com.munaf.order_service.clients;

import com.munaf.order_service.dtos.OrderDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "SHIPPING-SERVICE", path = "/shipping")
public interface ShippingFeignClient {

    @PostMapping("order/{orderId}")
    OrderDto shippingOrder(@PathVariable Long orderId);


}
