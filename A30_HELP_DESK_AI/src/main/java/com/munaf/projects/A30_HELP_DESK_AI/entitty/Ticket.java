package com.munaf.projects.A30_HELP_DESK_AI.entitty;

import com.munaf.projects.A30_HELP_DESK_AI.entitty.enums.Priority;
import com.munaf.projects.A30_HELP_DESK_AI.entitty.enums.Status;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "ticket")
@ToString
@Getter @Setter
public class Ticket {

    @Id
    private String id;

    @Column(columnDefinition = "TEXT")
    private String summery;

    @Column(columnDefinition = "TEXT")
    private String description;

    private String category;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Enumerated(EnumType.STRING)
    private Priority priority;

    private String email;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @PrePersist
    public void prePersist() {
        this.id = UUID.randomUUID().toString();
    }
}
