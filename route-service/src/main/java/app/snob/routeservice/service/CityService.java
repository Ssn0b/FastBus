package app.snob.routeservice.service;

import app.snob.routeservice.dto.CityRequest;
import app.snob.routeservice.dto.CityResponse;
import app.snob.routeservice.model.City;
import app.snob.routeservice.repository.CityRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CityService {

    private final CityRepository cityRepository;
    public void saveCity(CityRequest cityRequest){
        City city = City.builder()
                .name(cityRequest.getName())
                .latitude(cityRequest.getLatitude())
                .longitude(cityRequest.getLongitude())
                .build();
        cityRepository.save(city);
    }
    public CityResponse findCityById(UUID id){
        return cityRepository.findById(id)
                .map(this::mapToCityResponse)
                .orElseThrow(() -> new EntityNotFoundException("City with ID " + id + " not found"));
    }
    public List<CityResponse> getAllCities(){
        return cityRepository.findAll()
                .stream()
                .map(this::mapToCityResponse)
                .collect(Collectors.toList());
    }
    private CityResponse mapToCityResponse(City city){
        return CityResponse.builder()
                .id(city.getId())
                .name(city.getName())
                .latitude(city.getLatitude())
                .longitude(city.getLongitude())
                .build();
    }
}
