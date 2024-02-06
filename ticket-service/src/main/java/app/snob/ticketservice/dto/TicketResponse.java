package app.snob.ticketservice.dto;

import app.snob.ticketservice.model.TicketStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TicketResponse {
    private String id;
    private LocalDateTime timeOfValidation;
    private String routeNumber;
    private UUID userId;
    private TicketStatus status;
}
