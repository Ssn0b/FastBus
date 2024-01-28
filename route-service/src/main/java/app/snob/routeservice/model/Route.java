package app.snob.routeservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity(name = "Route")
@Table(name = "_route")
public class Route {
    @Id
    @GeneratedValue
    private UUID id;
    private String name;
    private int routeNumber;
    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "Route_Stop",
            joinColumns = { @JoinColumn(name = "route_id") },
            inverseJoinColumns = { @JoinColumn(name = "stop_id") }
    )
    private Set<Stop> stops = new HashSet<>();
    private double distance;
    private Duration duration;
    private UUID busId;
}
