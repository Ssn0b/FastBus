package app.snob.busservice.service;

import app.snob.busservice.dto.BusRequest;
import app.snob.busservice.dto.BusResponse;
import app.snob.busservice.model.Bus;
import app.snob.busservice.repository.BusRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class BusService {

    private final BusRepository busRepository;
    private final ObjectMapper objectMapper;

    public List<BusResponse> getAllBuses(){
        return busRepository.findAll()
                .stream()
                .map(this::mapToBusResponse)
                .collect(Collectors.toList());
    }

    public BusResponse getBusById(UUID id){
        return busRepository.findById(id)
                .map(this::mapToBusResponse)
                .orElseThrow(() -> new EntityNotFoundException("Bus with ID " + id + " not found"));
    }

    public void saveBus(MultipartFile file, String busData){
        try{
            Bus bus = objectMapper.readValue(busData, Bus.class);
//            UUID driverId = bus.getDriverId();
//            User driver = userRepository.findById(driverId)
//                    .orElseThrow(() -> new EntityNotFoundException("Driver with ID " +
//                            driverId + " not found."));
//            if (busRepository.existsBusByDriverId(driverId)) {
//                throw new DriverAlreadyHasBusException("Driver with ID " +
//                        driverId + " is already associated with an active bus.");
//            }else if(driver.getRole() != Role.DRIVER){
//                throw new UserIsNotDriverException(
//                        "User with ID " + driverId +
//                                " is not working as driver.");
//            }
//            else {
//                String photoUrl = s3Service.uploadBusImage(file);
//                bus.setPhotoUrl(photoUrl);
                busRepository.save(bus);
                log.info("Product {} is saved", bus.getId());
            //        }
        } catch (Exception e) {
            log.warn("Bus saving error");
        }
    }

    public ResponseEntity<String> updateBus(UUID id, MultipartFile file, String updatedFields){
        try {
            Optional<Bus> optionalBus = busRepository.findById(id);
            if (optionalBus.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Bus not found.");
            }
            Bus existingBus = optionalBus.get();
//            if (file != null && !file.isEmpty()) {
//                s3Service.deleteFile(existingBus.getPhotoUrl());
//                String photoUrl = s3Service.uploadBusImage(file);
//                existingBus.setPhotoUrl(photoUrl);
//            }
            if (updatedFields != null && !updatedFields.isEmpty()) {
                Bus updatedBus = objectMapper.readValue(updatedFields, Bus.class);
                if (updatedBus.getSeats() != 0) {
                    existingBus.setSeats(updatedBus.getSeats());
                }
                if (updatedBus.getCarNumber() != null) {
                    existingBus.setCarNumber(updatedBus.getCarNumber());
                }
//                if (updatedBus.getDriverId() != null) {
//                    UUID updatedDriverId = updatedBus.getDriverId();
//                    User updatedDriver = userRepository.findById(updatedDriverId)
//                            .orElseThrow(() -> new EntityNotFoundException("Driver with ID " + updatedDriverId + " not found."));
//                    existingBus.setDriver(updatedDriver);
//                }
                if (updatedBus.getBusStatus() != null) {
                    existingBus.setBusStatus(updatedBus.getBusStatus());
                }
                busRepository.save(existingBus);
            }
            return ResponseEntity.ok("Bus updated successfully.");

        } catch (Exception  e) {
            log.warn("Bus update error");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update bus.");
        }
    }

    public void deleteBusById(UUID id){
        try {
//            Bus bus = busRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Bus with ID " +
//                    id + " not found."));
//            s3Service.deleteFile(bus.getPhotoUrl());
            busRepository.deleteById(id);
        }catch (Exception e) {
            log.warn("Bus deletion error");
        }
    }

    private BusResponse mapToBusResponse(Bus bus){
        return BusResponse.builder()
                .id(bus.getId())
                .seats(bus.getSeats())
                .brand(bus.getBrand())
                .busStatus(bus.getBusStatus())
                .photoUrl(bus.getPhotoUrl())
                .carNumber(bus.getCarNumber())
                .build();
    }
}
