package app.snob.routeservice.dto;

import app.snob.routeservice.model.City;
import app.snob.routeservice.model.Route;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StopRequest {
    private City city;
    private String street;
    private Set<Route> routeStops = new HashSet<>();
}
