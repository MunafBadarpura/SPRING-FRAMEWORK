package com.munaf.A32_SPRING_BOOT_EVENTS.services;

import com.munaf.A32_SPRING_BOOT_EVENTS.events.OrderCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OrderService {

    private final ApplicationEventPublisher applicationEventPublisher;

    public OrderService(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void createOrder(Long orderId) {
        // create order
        log.info("ORDER CREATED WITH ORDER ID {}", orderId);

        // publish event
        applicationEventPublisher.publishEvent(new OrderCreatedEvent(orderId));

        // log info
        log.info("ORDER CREATED EVENT PUBLISHED");
    }

}
