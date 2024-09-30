package com.quarkus.service.two.service;

import com.quarkus.service.one.entity.Employee;
import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.core.Response;

import java.util.List;

public interface RestClientService {

    Uni<List<Employee>> getAllEmployees();

    Uni<Employee> getEmployeeById(Long id);

    Uni<Response> addEmployee(Employee employee);

    Uni<Employee> updateEmployeePosition(Long id, String updatedPosition);

    Uni<Boolean> removeEmployee(Long employeeId);


}
