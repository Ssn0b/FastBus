package app.snob.ticketservice.controller;

import app.snob.ticketservice.dto.TicketRequest;
import app.snob.ticketservice.dto.TicketResponse;
import app.snob.ticketservice.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/ticket")
@RequiredArgsConstructor
public class TicketController {
    private final TicketService ticketService;

    @PostMapping
    public ResponseEntity<String> addTicket(@RequestBody TicketRequest ticket) {
        ticketService.saveTicket(ticket);
        return ResponseEntity.ok("Ticket saved successfully");
    }
    @GetMapping
    public List<TicketResponse> getTickets() {
        return ticketService.getAllTickets();
    }
    @GetMapping("/{id}")
    public TicketResponse getTicket(@PathVariable String id) {
        return ticketService.getTicketById(id);
    }
}
