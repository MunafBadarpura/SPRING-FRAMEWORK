package com.munaf.projects.A30_HELP_DESK_AI.repository;

import com.munaf.projects.A30_HELP_DESK_AI.entitty.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, String> {
    List<Ticket> findByEmail(String email);
}
