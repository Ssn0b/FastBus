package app.snob.routeservice.controller;

import app.snob.routeservice.dto.CityRequest;
import app.snob.routeservice.dto.CityResponse;
import app.snob.routeservice.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/city")
@RequiredArgsConstructor
public class CityController {
    private final CityService cityService;
    @PostMapping
    public ResponseEntity<String> addCity(@RequestBody CityRequest city) {
        cityService.saveCity(city);
        return ResponseEntity.ok("City added successfully");
    }
    @GetMapping
    public List<CityResponse> getCities() {
        return cityService.getAllCities();
    }
    @GetMapping("/{id}")
    public CityResponse getCity(@PathVariable UUID id) {
        return cityService.findCityById(id);
    }
}
