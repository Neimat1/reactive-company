package com.quarkus.service.one;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Info;

@ApplicationPath("/")
@OpenAPIDefinition(
        info = @Info(title = "Company Microservice",
                description = "Service-One which has CRUD operations for Employee",
                version = "1.0"
        )
)
public class CompanyMicroservice extends Application {
}
