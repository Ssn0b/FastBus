package app.snob.busservice.dto;

import app.snob.busservice.model.BusStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BusResponse {
    private UUID id;
    private String brand;
    private int seats;
    private String carNumber;
    private BusStatus busStatus;
    private String photoUrl;
}
