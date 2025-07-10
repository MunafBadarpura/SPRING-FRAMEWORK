package com.munaf.order_service.services;

import com.munaf.order_service.clients.InventoryFeignClient;
import com.munaf.order_service.dtos.OrderItemRequestDto;
import com.munaf.order_service.dtos.OrderRequestDto;
import com.munaf.order_service.entities.Order;
import com.munaf.order_service.entities.OrderItem;
import com.munaf.order_service.entities.enums.OrderStatus;
import com.munaf.order_service.exceptions.ResourceNotFoundException;
import com.munaf.order_service.repositories.OrderRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final InventoryFeignClient inventoryFeignClient;
    private final ModelMapper modelMapper;

    public OrderService(OrderRepository orderRepository, InventoryFeignClient inventoryFeignClient, ModelMapper modelMapper) {
        this.orderRepository = orderRepository;
        this.inventoryFeignClient = inventoryFeignClient;
        this.modelMapper = modelMapper;
    }

    public List<OrderRequestDto> getAllOrders() {
        List<Order> orders = orderRepository.findAll();

        List<OrderRequestDto> orderRequestDtoList = new ArrayList<>();
        for (Order order : orders) {
            List<OrderItem> orderItems = order.getOrderItems();
            List<OrderItemRequestDto> orderItemRequestDtos = orderItems.stream()
                    .map(orderItem -> modelMapper.map(orderItem, OrderItemRequestDto.class))
                    .toList();

            OrderRequestDto orderRequestDto = modelMapper.map(order, OrderRequestDto.class);
            orderRequestDto.setOrderItemRequestDtos(orderItemRequestDtos);
            orderRequestDtoList.add(orderRequestDto);
        }

        return orderRequestDtoList;
    }

    public OrderRequestDto getOrderById(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id : " + orderId));

        List<OrderItem> orderItems = order.getOrderItems();
        List<OrderItemRequestDto> orderItemRequestDtos = orderItems.stream()
                .map(orderItem -> modelMapper.map(orderItem, OrderItemRequestDto.class))
                .toList();

        OrderRequestDto orderRequestDto = modelMapper.map(order, OrderRequestDto.class);
        orderRequestDto.setOrderItemRequestDtos(orderItemRequestDtos);
        return orderRequestDto;

    }

    public OrderRequestDto createOrder(OrderRequestDto orderRequestDto) {
        Double totalPrice = inventoryFeignClient.reduceStock(orderRequestDto);
        Order order = modelMapper.map(orderRequestDto, Order.class);

        for (OrderItem orderItem : order.getOrderItems()) {
            orderItem.setOrder(order);
        }
        order.setTotalPrice(totalPrice);
        order.setOrderStatus(OrderStatus.CONFIRMED);
        order = orderRepository.save(order);

        return modelMapper.map(order, OrderRequestDto.class);
    }
}
