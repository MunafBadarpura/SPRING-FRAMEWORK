package com.munaf.projects.A30_HELP_DESK_AI.tool;

import com.munaf.projects.A30_HELP_DESK_AI.entitty.Ticket;
import com.munaf.projects.A30_HELP_DESK_AI.service.TicketService;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class TicketDatabaseTool {

    private final TicketService ticketService;

    public TicketDatabaseTool(TicketService ticketService) {
        this.ticketService = ticketService;
    }


    @Tool(name = "create_ticket_tool", description = "this tool is used to create a new ticket for user")
    public Ticket createTicket(@ToolParam(description = "ticket object") Ticket ticket) {
        System.out.println("CREATING TICKET TOOL CALLED WITH TICKET: "+ ticket);
        return ticketService.createTicket(ticket);
    }

    @Tool(name = "update_ticket_tool", description = "this tool is used to update a ticket")
    public Ticket updateTicket(@ToolParam(description = "ticket object") Ticket ticket) {
        System.out.println("UPDATING TICKET TOOL CALLED WITH TICKET: "+ ticket);
        return ticketService.updateTicket(ticket);
    }

    @Tool(name = "get_tickets_by_email_tool", description = "this tool is used to get tickets by email")
    public List<Ticket> getTicket(@ToolParam(description = "email") String email) {
        System.out.println("GETTING TICKETS BY EMAIL TOOL CALLED WITH EMAIL: "+ email);
        return ticketService.getTicketByEmail(email);
    }

    @Tool(name = "get_ticket_by_id_tool", description = "this tool is used to get ticket by id")
    public Ticket getTicketById(@ToolParam(description = "ticket id") String id) {
        System.out.println("GETTING TICKET BY ID TOOL CALLED WITH ID: "+ id);
        return ticketService.getTicket(id);
    }

    @Tool(name = "get_current_time", description = "this tool is used to get current time")
    public String getCurrentTime() {
        System.out.println("GETTING CURRENT TIME TOOL CALLED TIME" + LocalDateTime.now().toString());
        return LocalDateTime.now().toString();
    }

}
