package com.example.customerapi.demo.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Customer API",
                version = "1.0",
                description = "Spring Boot REST API for managing customers",
                contact = @Contact(name = "Elif Fırıncı", email = "elif@example.com"),
                license = @License(name = "MIT", url = "https://opensource.org/licenses/MIT")
        ),
        servers = @Server(url = "http://localhost:8080", description = "Local server")
)
public class OpenApiConfig {
}
