package app.snob.busservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity(name = "Bus")
@Table(name = "_bus")
public class Bus {
    @Id
    @GeneratedValue
    private UUID id;
    private String brand;
    private int seats;
    private String carNumber;
//    @Enumerated(EnumType.STRING)
//    private BusStatus busStatus;
    private String photoUrl;
//    @OneToOne
//    @JoinColumn(name = "driver_id")
//    private User driver;
}
