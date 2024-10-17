package com.quarkus.service.two;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Info;

@ApplicationPath("/")
@OpenAPIDefinition(
        info = @Info(title = "Rest-Client Microservice",
                description = "Service-Two which consumes Service-One",
                version = "1.0"
        )
)
public class RestClientMicroservice extends Application {
}
