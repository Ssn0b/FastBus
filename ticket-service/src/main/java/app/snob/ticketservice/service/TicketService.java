package app.snob.ticketservice.service;

import app.snob.ticketservice.dto.TicketRequest;
import app.snob.ticketservice.dto.TicketResponse;
import app.snob.ticketservice.model.Ticket;
import app.snob.ticketservice.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class TicketService {
    private final TicketRepository ticketRepository;

    public void saveTicket(TicketRequest ticketRequest){
        Ticket ticket = Ticket.builder()
                .routeNumber(ticketRequest.getRouteNumber())
                .userId(ticketRequest.getUserId())
                .status(ticketRequest.getStatus())
                .timeOfValidation(ticketRequest.getTimeOfValidation())
                .build();
        ticketRepository.save(ticket);
    }

    public List<TicketResponse> getAllTickets() {
        return ticketRepository.findAll()
                .stream()
                .map(this::mapToTicketResponse)
                .collect(Collectors.toList());
    }

    public TicketResponse getTicketById(String id) {
        return ticketRepository.findById(id)
                .map(this::mapToTicketResponse)
                .orElseThrow(() -> new RuntimeException("Ticket with ID " + id + " not found"));
    }

    private TicketResponse mapToTicketResponse(Ticket ticket){
        return TicketResponse.builder()
                .id(ticket.getId())
                .timeOfValidation(ticket.getTimeOfValidation())
                .userId(ticket.getUserId())
                .routeNumber(ticket.getRouteNumber())
                .status(ticket.getStatus())
                .build();
    }
}
