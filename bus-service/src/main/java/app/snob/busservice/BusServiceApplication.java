package app.snob.busservice;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@OpenAPIDefinition(info =
@Info(title = "Route API", version = "${springdoc.version}", description = "Documentation Route API v1.0")
)public class BusServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(BusServiceApplication.class, args);
    }
}
