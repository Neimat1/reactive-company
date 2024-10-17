package com.quarkus.service.two.resource;

import com.quarkus.service.one.entity.Employee;
import com.quarkus.service.two.service.RestClientService;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.openapi.annotations.Operation;

import java.util.List;

@Path("/rest-client")
public class RestClientResource {


    RestClientService restClientService;

    @Inject
    public RestClientResource(RestClientService restClientService) {
        this.restClientService = restClientService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(
            summary = "Get All Employees",
            description = "Return all employees ")
    public Uni<List<Employee>> getAllEmployees() {
        return restClientService.getAllEmployees();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(
            summary = "Get Employees By Id",
            description = "Return employee by it's id ")
    public Uni<Employee> getEmployeeById(@PathParam("id") Long id) {
        return restClientService.getEmployeeById(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(
            summary = "Add Employee",
            description = "Return created response for added new employee")
    public Uni<Response> addEmployee(Employee employee) {
        return restClientService.addEmployee(employee);
    }

    @PUT
    @Path("/{id}")
    @Operation(
            summary = "Update Employee position by it's id",
            description = "Return all employees ")
    public Uni<Employee> updateEmployeePosition(@PathParam("id") Long id, String updatedPosition) {
        return restClientService.updateEmployeePosition(id, updatedPosition);
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(
            summary = "Remove Employee",
            description = "Return boolean response")
    public Uni<Boolean> removeEmployee(@PathParam("id") Long employeeId) {
        return restClientService.removeEmployee(employeeId);
    }



}
