package app.snob.busservice.controller;

import app.snob.busservice.dto.BusResponse;
import app.snob.busservice.service.BusService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/bus")
@RequiredArgsConstructor
public class BusController {
    private final BusService busService;
    @PostMapping
    public ResponseEntity<String> addBus(@RequestParam("file") MultipartFile file,
                                         @RequestParam("bus") String busData) {
        busService.saveBus(file, busData);
        return ResponseEntity.ok("Bus added successfully");
    }
    @PutMapping("/{id}")
    public ResponseEntity<String> updateBus(@PathVariable("id") UUID id, @RequestParam("file") MultipartFile file,
                                            @RequestParam("bus") String updatedFields){
        busService.updateBus(id,file,updatedFields);
        return ResponseEntity.status(HttpStatus.OK)
                .body("Bus updated");
    }
    @GetMapping
    public List<BusResponse> getBuses() {
        return busService.getAllBuses();
    }
    @GetMapping("/{id}")
    public BusResponse getBus(@PathVariable UUID id){
        return busService.getBusById(id);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBus(@PathVariable UUID id){
        busService.deleteBusById(id);
        return ResponseEntity.ok("Bus deleted successfully.");
    }
    @GetMapping("/exist/{id}")
    public boolean checkIfBusExists(@PathVariable UUID id) {
        return busService.doesBusExist(id);
    }
}
