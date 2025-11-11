package com.munaf.A32_SPRING_BOOT_EVENTS.listners;

import com.munaf.A32_SPRING_BOOT_EVENTS.events.OrderCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ConditionalListener {

    @EventListener(condition = "#event.orderId == 1")
    @Async // event errors only handle in non asysnc methhods, to handle we need to create asysnc config
    public void firstOrder(OrderCreatedEvent event) {
        log.info("Congratulations! This is your first order!");
        throw new RuntimeException("TEST EXCEPTION");
    }


}
