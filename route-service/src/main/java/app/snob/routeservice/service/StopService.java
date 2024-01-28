package app.snob.routeservice.service;

import app.snob.routeservice.dto.CityResponse;
import app.snob.routeservice.dto.StopRequest;
import app.snob.routeservice.dto.StopResponse;
import app.snob.routeservice.model.City;
import app.snob.routeservice.model.Stop;
import app.snob.routeservice.repository.CityRepository;
import app.snob.routeservice.repository.StopRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StopService {
    private final StopRepository stopRepository;
    private final CityRepository cityRepository;

    public void saveStop(StopRequest stopRequest) {
        City city =
                cityRepository
                        .findById(stopRequest.getCity().getId())
                        .orElseThrow(
                                () -> new EntityNotFoundException(
                                                "City with ID " + stopRequest.getCity().getId() + " not found"));
        Stop newStop = Stop.builder()
                .city(city)
                .street(stopRequest.getStreet())
                .build();
        stopRepository.save(newStop);
    }

    public List<StopResponse> getAllStops() {
        return stopRepository.findAll()
                .stream()
                .map(this::mapToStopResponse)
                .collect(Collectors.toList());
    }

    public StopResponse getStopById(Long id) {
        return stopRepository.findById(id)
                .map(this::mapToStopResponse)
                .orElseThrow(() -> new EntityNotFoundException("Stop with ID " + id + " not found"));
    }

    public void deleteStopById(Long id) {
        if (stopRepository.existsById(id)) {
            stopRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Stop with ID " + id + " not found");
        }
    }

    private StopResponse mapToStopResponse(Stop stop){
        return StopResponse.builder()
                .id(stop.getId())
                .street(stop.getStreet())
                .city(stop.getCity())
                .routeStops(stop.getRouteStops())
                .build();
    }
}
