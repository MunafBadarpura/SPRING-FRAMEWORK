package com.munaf.A32_SPRING_BOOT_EVENTS.listners;

import com.munaf.A32_SPRING_BOOT_EVENTS.events.OrderCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class OrderEmailListener {

    @EventListener
    public void onOrderCreatedEvent(OrderCreatedEvent event) {
        log.info("Sending email for order created event {}", event);
    }

}
