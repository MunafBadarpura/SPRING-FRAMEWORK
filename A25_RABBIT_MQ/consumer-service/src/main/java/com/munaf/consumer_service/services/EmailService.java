package com.munaf.consumer_service.services;

import com.munaf.consumer_service.dto.OrderDTO;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    // send email logic here
    public void sendEmail(OrderDTO orderDTO) {
        System.out.println("Email sent: " + orderDTO);
    }

}
