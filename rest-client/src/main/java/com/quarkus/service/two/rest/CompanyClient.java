package com.quarkus.service.two.rest;


import com.quarkus.service.one.entity.Employee;
import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import java.util.List;

@RegisterRestClient(configKey = "company.client")
@Path("/api/employees")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface CompanyClient {


    @GET
    Uni<List<Employee>> getAllEmployees();

    @GET
    @Path("/{id}")
    Uni<Employee> getEmployeeById(@PathParam("id") Long id);

    @POST
    Uni<Response> addEmployee(Employee employee);

    @PUT
    @Path("/{id}")
    Uni<Employee> updateEmployeePosition(@PathParam("id") Long id, String updatedPosition);

    @DELETE
    @Path("/{id}")
    Uni<Boolean> removeEmployee(@PathParam("id") Long employeeId);


}

