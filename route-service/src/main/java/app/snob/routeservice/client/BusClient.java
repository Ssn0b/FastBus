package app.snob.routeservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(value = "bus-service")
public interface BusClient {
    @GetMapping("/api/v1/bus/exist/{id}")
    boolean checkIfBusExists(@PathVariable("id") UUID id);
}
