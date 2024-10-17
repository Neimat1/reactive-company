package com.quarkus.service.two.rest;


import com.quarkus.service.one.entity.Employee;
import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import java.util.List;

@RegisterRestClient(configKey = "company.client")
@Path("/employees")
public interface CompanyClient {


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    Uni<List<Employee>> getAllEmployees();

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    Uni<Employee> getEmployeeById(@PathParam("id") Long id);

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    Uni<Response> addEmployee(@RequestBody Employee employee);

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    Uni<Employee> updateEmployeePosition(@PathParam("id") Long id, @RequestBody String updatedPosition);

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    Uni<Boolean> removeEmployee(@PathParam("id") Long employeeId);


}

