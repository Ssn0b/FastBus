package app.snob.routeservice;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@OpenAPIDefinition(info =
@Info(title = "Route API", version = "${springdoc.version}", description = "Documentation Route API v1.0")
)
public class RouteServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(RouteServiceApplication.class, args);
    }
}
