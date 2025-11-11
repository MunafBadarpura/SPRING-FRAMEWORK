package com.munaf.A32_SPRING_BOOT_EVENTS.listners;

import com.munaf.A32_SPRING_BOOT_EVENTS.events.OrderCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class OrderEmailListener {

    @EventListener
    // @Order(1) // for ordering
    @Async // for async
    public void onOrderCreatedEvent(OrderCreatedEvent event) {
        log.info("Sending email for order created event {}", event);
    }

}
