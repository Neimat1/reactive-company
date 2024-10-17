package com.quarkus.service.one.resource;


import com.quarkus.service.one.entity.Employee;
import com.quarkus.service.one.repository.EmployeeRepository;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Path("/employees")
@Tag(name = "Employee CRUD Endpoints")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EmployeeResource {



    @Inject
    EmployeeRepository repository;


    @GET
    @Operation(
            summary = "Get All Employees",
            description = "Return all employees ")
    public Uni<List<Employee>> getAllEmployees() {
        return repository.getAllEmployees();
    }

    @GET
    @Path("/{id}")
    @Operation(
            summary = "Get Employees By Id",
            description = "Return employee by it's id ")
    public Uni<Employee> getEmployeeById(@PathParam("id") Long id) {
        return repository.getEmployeeById(id);
    }

    @POST
    @Operation(
            summary = "Add Employee",
            description = "Return created response for added new employee")
    public Uni<Response> addEmployee(Employee employee) {
        return repository.addEmployee(employee);
    }

    @PUT
    @Path("/{id}")
    @Operation(
            summary = "Update Employee position by it's id",
            description = "Return all employees ")
    public Uni<Employee> updateEmployeePosition(@PathParam("id") Long id, String updatedPosition) {
        return repository.updateEmployeePosition(id, updatedPosition);
    }

    @DELETE
    @Path("/{id}")
    @Operation(
            summary = "Remove Employee",
            description = "Return boolean response")
    public Uni<Boolean> removeEmployee(@PathParam("id") Long employeeId) {
        return repository.removeEmployee(employeeId);
    }


}
