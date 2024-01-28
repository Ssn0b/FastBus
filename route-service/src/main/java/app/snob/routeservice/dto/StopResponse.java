package app.snob.routeservice.dto;

import app.snob.routeservice.model.City;
import app.snob.routeservice.model.Route;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StopResponse {
    private Long id;
    private City city;
    private String street;
    private Set<Route> routeStops = new HashSet<>();
}
