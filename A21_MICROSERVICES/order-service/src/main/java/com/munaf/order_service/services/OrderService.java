package com.munaf.order_service.services;

import com.munaf.order_service.clients.InventoryFeignClient;
import com.munaf.order_service.clients.ShippingFeignClient;
import com.munaf.order_service.dtos.OrderItemDto;
import com.munaf.order_service.dtos.OrderDto;
import com.munaf.order_service.entities.Order;
import com.munaf.order_service.entities.OrderItem;
import com.munaf.order_service.entities.enums.OrderStatus;
import com.munaf.order_service.exceptions.ResourceNotFoundException;
import com.munaf.order_service.repositories.OrderRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;
    private final InventoryFeignClient inventoryFeignClient;
    private final ShippingFeignClient shippingFeignClient;
    private final ModelMapper modelMapper;

    public OrderService(OrderRepository orderRepository, InventoryFeignClient inventoryFeignClient, ShippingFeignClient shippingFeignClient, ModelMapper modelMapper) {
        this.orderRepository = orderRepository;
        this.inventoryFeignClient = inventoryFeignClient;
        this.shippingFeignClient = shippingFeignClient;
        this.modelMapper = modelMapper;
    }


    public List<OrderDto> getAllOrders() {
        List<Order> orders = orderRepository.findAll();

        List<OrderDto> orderDtoList = new ArrayList<>();
        for (Order order : orders) {
            List<OrderItem> orderItems = order.getOrderItems();
            List<OrderItemDto> orderItemDtos = orderItems.stream()
                    .map(orderItem -> modelMapper.map(orderItem, OrderItemDto.class))
                    .toList();

            OrderDto orderDto = modelMapper.map(order, OrderDto.class);
            orderDto.setOrderItems(orderItemDtos);
            orderDtoList.add(orderDto);
        }

        return orderDtoList;
    }

    public OrderDto getOrderById(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id : " + orderId));

        List<OrderItem> orderItems = order.getOrderItems();
        List<OrderItemDto> orderItemDtos = orderItems.stream()
                .map(orderItem -> modelMapper.map(orderItem, OrderItemDto.class))
                .toList();

        OrderDto orderDto = modelMapper.map(order, OrderDto.class);
        orderDto.setOrderItems(orderItemDtos);
        return orderDto;

    }


//    @Retry(name = "inventoryRetry", fallbackMethod = "createOrderFallback")
//    @RateLimiter(name = "inventoryRateLimiter", fallbackMethod = "createOrderFallback")
    @CircuitBreaker(name = "inventoryCircuitBreaker", fallbackMethod = "createOrderFallback")
    public OrderDto createOrder(OrderDto orderDto) {
        log.info("calling create order method");
        Double totalPrice = inventoryFeignClient.reduceStock(orderDto);
        Order order = modelMapper.map(orderDto, Order.class);

        for (OrderItem orderItem : order.getOrderItems()) {
            orderItem.setOrder(order);
        }
        order.setTotalPrice(totalPrice);
        order.setOrderStatus(OrderStatus.CONFIRMED);
        order = orderRepository.save(order);

        return modelMapper.map(order, OrderDto.class);
    }


    public OrderDto createOrderFallback(OrderDto orderDto, Throwable throwable) {
        log.info("Retry occured : {}", throwable.getMessage());
        return new OrderDto();
    }



    @Retry(name = "shippingOrderRetry", fallbackMethod = "shippingOrderFallback")
    public OrderDto shippingOrder(Long orderId) {
        return shippingFeignClient.shippingOrder(orderId);
    }


    public OrderDto shippingOrderFallback(Long orderId, Throwable throwable) {
        log.info("Retry occured : {}", throwable.getMessage());
        return new OrderDto();
    }
}
