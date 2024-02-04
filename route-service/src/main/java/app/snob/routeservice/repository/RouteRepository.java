package app.snob.routeservice.repository;

import app.snob.routeservice.model.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RouteRepository extends JpaRepository<Route, UUID> {
    boolean existsRouteByBusId(UUID id);
}
