package app.snob.routeservice.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity(name = "Stop")
@Table(name = "_stop")
public class Stop {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    @JoinColumn(name = "city_id", nullable = false)
    @ToString.Exclude
    private City city;
    private String street;
    @ManyToMany(mappedBy = "stops")
    @ToString.Exclude
    private Set<Route> routeStops = new HashSet<>();
}
