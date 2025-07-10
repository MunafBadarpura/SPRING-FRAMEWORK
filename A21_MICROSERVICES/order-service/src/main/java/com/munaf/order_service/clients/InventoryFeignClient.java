package com.munaf.order_service.clients;

import com.munaf.order_service.dtos.OrderRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "INVENTORY-SERVICE", path = "inventory/products")
public interface InventoryFeignClient {

    @PutMapping("reduce-stock")
    Double reduceStock(@RequestBody OrderRequestDto orderRequestDto);

}
