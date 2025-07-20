package com.munaf.publisher_service.services;

import com.munaf.publisher_service.dto.OrderDTO;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final MessagePublisher messagePublisher;

    public OrderService(MessagePublisher messagePublisher) {
        this.messagePublisher = messagePublisher;
    }

    public String placeOrder() {
        OrderDTO orderDTO = new OrderDTO(1L, "New Order");
        messagePublisher.sendEmail(orderDTO);
        return "Order placed";
    }

}
