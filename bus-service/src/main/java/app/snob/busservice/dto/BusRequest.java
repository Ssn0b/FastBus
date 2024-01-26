package app.snob.busservice.dto;

import app.snob.busservice.model.BusStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BusRequest {
    private String brand;
    private int seats;
    private String carNumber;
    private BusStatus busStatus;
    private String photoUrl;
}
