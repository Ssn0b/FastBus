package app.snob.routeservice.controller;

import app.snob.routeservice.dto.StopRequest;
import app.snob.routeservice.dto.StopResponse;
import app.snob.routeservice.service.StopService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/stop")
@RequiredArgsConstructor
public class StopController {
    private final StopService stopService;
    @PostMapping
    public ResponseEntity<String> addStop(@RequestBody StopRequest stop) {
        stopService.saveStop(stop);
        return ResponseEntity.ok("Stop added successfully");
    }
    @GetMapping
    public List<StopResponse> getStops() {
        return stopService.getAllStops();
    }
    @GetMapping("/{id}")
    public StopResponse getStop(@PathVariable Long id) {
        return stopService.getStopById(id);
    }
    @DeleteMapping("/{id}")
    public void deleteStop(@PathVariable Long id) {
        stopService.deleteStopById(id);
    }
}
