package app.snob.routeservice.controller;

import app.snob.routeservice.dto.RouteRequest;
import app.snob.routeservice.dto.RouteResponse;
import app.snob.routeservice.service.RouteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/route")
@RequiredArgsConstructor
public class RouteController {
    private final RouteService routeService;
    @PostMapping
    public ResponseEntity<String> addRoute(@RequestBody RouteRequest route) {
        routeService.saveRoute(route);
        return ResponseEntity.ok("Route added successfully");
    }
    @GetMapping
    public List<RouteResponse> getRoutes() {
        return routeService.getAllRoutes();
    }
    @GetMapping("/{id}")
    public RouteResponse getRoute(@PathVariable UUID id) {
        return routeService.getRouteById(id);
    }
    @DeleteMapping("/{id}")
    public void deleteRoute(@PathVariable UUID id) {
        routeService.deleteRouteById(id);
    }
}
