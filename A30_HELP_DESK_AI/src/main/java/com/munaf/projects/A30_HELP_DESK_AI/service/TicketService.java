package com.munaf.projects.A30_HELP_DESK_AI.service;

import com.munaf.projects.A30_HELP_DESK_AI.entitty.Ticket;
import com.munaf.projects.A30_HELP_DESK_AI.repository.TicketRepository;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;
    private final ChatClient vertexChatClient;

    public TicketService(TicketRepository ticketRepository, @Qualifier("vertexChatClient") ChatClient vertexChatClient) {
        this.ticketRepository = ticketRepository;
        this.vertexChatClient = vertexChatClient;
    }

    // create ticket
    public Ticket createTicket(Ticket ticket) {
        ticket.setId(null);
        return ticketRepository.save(ticket);
    }

    // update ticket
    public Ticket updateTicket(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    // get ticket by id
    public Ticket getTicket(String id) {
        return ticketRepository.findById(id).orElse(null);
    }


    // get ticket by email
    public List<Ticket> getTicketByEmail(String email) {
        return ticketRepository.findByEmail(email);
    }


}
