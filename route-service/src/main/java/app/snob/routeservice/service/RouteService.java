package app.snob.routeservice.service;

import app.snob.routeservice.client.BusClient;
import app.snob.routeservice.dto.CityResponse;
import app.snob.routeservice.dto.RouteRequest;
import app.snob.routeservice.dto.RouteResponse;
import app.snob.routeservice.model.Route;
import app.snob.routeservice.model.Stop;
import app.snob.routeservice.repository.RouteRepository;
import app.snob.routeservice.repository.StopRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RouteService {

    private final RouteRepository routeRepository;
    private final StopRepository stopRepository;
    private final BusClient busClient;

    public void saveRoute(RouteRequest routeRequest) {
        UUID busId = routeRequest.getBusId();
        if (!busClient.checkIfBusExists(busId) || routeRepository.existsRouteByBusId(busId)) {
            throw new EntityNotFoundException(
                    "Bus with ID " + busId + " is not found or already associated with an active route");
        } else {
            for (Stop stop : routeRequest.getStops()) {
                if (!stopRepository.existsById(stop.getId())) {
                    throw new EntityNotFoundException("Stop with ID " + stop.getId() + " not found");
                }
            }
            Route route = Route.builder()
                    .name(routeRequest.getName())
                    .routeNumber(routeRequest.getRouteNumber())
                    .busId(routeRequest.getBusId())
                    .distance(routeRequest.getDistance())
                    .duration(routeRequest.getDuration())
                    .build();
            routeRepository.save(route);
        }
    }

    public List<RouteResponse> getAllRoutes() {
        return routeRepository.findAll()
                .stream()
                .map(this::mapToRouteResponse)
                .collect(Collectors.toList());
    }

    public RouteResponse getRouteById(UUID id) {
        return routeRepository.findById(id)
                .map(this::mapToRouteResponse)
                .orElseThrow(() -> new EntityNotFoundException("Route with ID " + id + " not found"));
    }

    public void deleteRouteById(UUID id) {
        if (routeRepository.existsById(id)) {
            routeRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Route with ID " + id + " not found");
        }
    }

    private RouteResponse mapToRouteResponse(Route route){
        return RouteResponse.builder()
                .id(route.getId())
                .routeNumber(route.getRouteNumber())
                .name(route.getName())
                .stops(route.getStops())
                .distance(route.getDistance())
                .duration(route.getDuration())
                .busId(route.getBusId())
                .build();
    }
}
