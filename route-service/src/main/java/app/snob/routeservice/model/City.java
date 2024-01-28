package app.snob.routeservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity(name = "City")
@Table(name = "_city")
public class City {
    @Id
    @GeneratedValue
    private UUID id;
    private String name;
    private double latitude;
    private double longitude;
}
