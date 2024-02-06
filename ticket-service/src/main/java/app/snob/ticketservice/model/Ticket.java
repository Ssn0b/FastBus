package app.snob.ticketservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Document(collection = "ticket")
public class Ticket {
    @Id
    private String id;
    private LocalDateTime timeOfValidation;
    private UUID userId;
    private String routeNumber;
    private TicketStatus status;
}
