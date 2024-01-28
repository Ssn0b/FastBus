package app.snob.routeservice.dto;

import app.snob.routeservice.model.Stop;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RouteRequest {
    private String name;
    private int routeNumber;
    private Set<Stop> stops = new HashSet<>();
    private double distance;
    private Duration duration;
    private UUID busId;
}
