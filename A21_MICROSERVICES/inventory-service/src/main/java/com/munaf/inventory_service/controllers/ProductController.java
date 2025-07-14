package com.munaf.inventory_service.controllers;

import com.munaf.inventory_service.clients.OrdersFeignClient;
import com.munaf.inventory_service.dtos.OrderDto;
import com.munaf.inventory_service.dtos.ProductDTO;
import com.munaf.inventory_service.services.ProductServices;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClient;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductServices productServices;
    private final DiscoveryClient discoveryClient;
    private final RestClient restClient;
    private final OrdersFeignClient ordersFeignClient;

    public ProductController(ProductServices productServices, DiscoveryClient discoveryClient, RestClient restClient, OrdersFeignClient ordersFeignClient) {
        this.productServices = productServices;
        this.discoveryClient = discoveryClient;
        this.restClient = restClient;
        this.ordersFeignClient = ordersFeignClient;
    }

    @GetMapping("order-service-from-product")
    public String orderServiceFromProductService() {
//        ServiceInstance OrderServiceInstance = discoveryClient.getInstances("order-service").getFirst();
//
//        return restClient.get()
//                .uri(OrderServiceInstance.getUri() + "/orders/core/check")
//                .retrieve()
//                .body(String.class);

        return ordersFeignClient.helloOrderService();
    }


    @GetMapping("/{productId}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Long productId) {
        return ResponseEntity.ok(productServices.getProductById(productId));
    }

    @GetMapping()
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        return ResponseEntity.ok(productServices.getAllProducts());
    }


    @PutMapping("reduce-stock")
    public ResponseEntity<Double> reduceStock(@RequestBody OrderDto orderDto) {
        return ResponseEntity.ok(productServices.reduceStock(orderDto));
    }

}
