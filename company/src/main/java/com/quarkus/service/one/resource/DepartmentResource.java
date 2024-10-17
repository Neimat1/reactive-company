package com.quarkus.service.one.resource;


import com.quarkus.service.one.entity.Department;
import com.quarkus.service.one.repository.DepartmentRepository;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;


import java.util.List;

@Path("/departments")
@Tag(name = "Department  Endpoints")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class DepartmentResource {


    @Inject
    DepartmentRepository repository;


    @GET
    @Operation(
            summary = "Get All Departments",
            description = "Return all departments ")
    public Uni<List<Department>> getAllDepartments() {
        return repository.getAllDepartments();
    }

    @GET
    @Path("/{department_name}")
    @Operation(
            summary = "Find Department By Name",
            description = "Return a department by it's name")
    public Uni<Department> findDepartmentByName(@PathParam("department_name") String departmentName) {
        return repository.findDepartmentByName(departmentName);
    }


}
