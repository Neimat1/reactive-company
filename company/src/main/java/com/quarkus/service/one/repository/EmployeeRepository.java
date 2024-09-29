package com.quarkus.service.one.repository;

import com.quarkus.service.one.entity.Employee;
import io.quarkus.hibernate.reactive.panache.Panache;
import io.quarkus.hibernate.reactive.panache.PanacheRepository;
import io.quarkus.panache.common.Sort;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.util.Collections;
import java.util.List;


@ApplicationScoped
public class EmployeeRepository implements PanacheRepository<Employee> {
    private static final Logger LOG = LoggerFactory.getLogger(EmployeeRepository.class);

    public Uni<List<Employee>> getAllEmployees() {
        return listAll(Sort.by("id"))
                .onFailure()
                .recoverWithNull();
    }

    public Uni<Response> addEmployee(Employee employee) {
        return Panache.withTransaction(() -> persist(employee))
                .onItem().transform(insertedEmployee ->
                        Response.created(URI.create("/employees/" + insertedEmployee.getId())).build()
                ).onFailure().recoverWithItem(CallBackFailure());
    }


    public Uni<Boolean> removeEmployee(Long employeeId) {
        return Panache.withTransaction(() -> deleteById(employeeId));
    }


    public Uni<Employee> updateEmployeePosition(Long employeeId, String updatedPosition) {
        return Panache.withTransaction(() ->
                getEmployeeById(employeeId)
                        .onItem().ifNotNull()
                        .transform(employee -> {
                            employee.setPosition(updatedPosition);
                            return employee;
                        })
        ).onFailure().recoverWithNull();
    }


    public Uni<Employee> getEmployeeById(Long employeeId) {
        return find("id", employeeId).firstResult().onFailure().recoverWithNull();
    }


    private Response CallBackFailure() {
        return Response.ok().entity("Down Time!!!").build();
    }

}
