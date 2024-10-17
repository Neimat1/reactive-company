package com.quarkus.service.two.service;

import com.quarkus.service.one.entity.Department;
import com.quarkus.service.one.entity.Employee;
import com.quarkus.service.two.rest.CompanyClient;
import io.quarkus.test.Mock;
import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Mock
@RestClient
public class MockRestClientService implements CompanyClient {


    @Override
    public Uni<List<Employee>> getAllEmployees() {
        Department csDepartment = new Department(1L, "CS");
        Department itDepartment = new Department(2L, "IT");

        Employee employee1 = new Employee(1L, "Neimat Soliman", "Software Engineering", csDepartment);
        Employee employee2 = new Employee(2L, "Tokka Ahmed", "Backed Engineering", itDepartment);
        Employee employee3 = new Employee(3L, "Alaa Adel", "Frontend Engineering", csDepartment);
        Employee employee4 = new Employee(4L, "Eman Salah", "AI Engineering", itDepartment);

        List<Employee> employees = new ArrayList<>();
        employees.add(employee1);
        employees.add(employee2);
        employees.add(employee3);
        employees.add(employee4);

        return Uni.createFrom().item(employees);

    }

    @Override
    public Uni<Employee> getEmployeeById(Long id) {
        Employee mockEmployee = new Employee();
        mockEmployee.setId(id);
        mockEmployee.setPosition("Mock Data");
        mockEmployee.setDepartment(new Department(1L, "CS"));
        mockEmployee.setName("Mock Data");

        return Uni.createFrom().item(mockEmployee);
    }

    @Override
    public Uni<Response> addEmployee(Employee employee) {
        return Objects.nonNull(employee) ?
                Uni.createFrom().item(Response.status(Response.Status.CREATED).build()) :
                Uni.createFrom().item(Response.status(Response.Status.BAD_REQUEST).build());
    }

    @Override
    public Uni<Employee> updateEmployeePosition(Long id, String updatedPosition) {
        Employee mockEmployee = new Employee();
        mockEmployee.setId(id);
        mockEmployee.setPosition(updatedPosition);
        mockEmployee.setDepartment(new Department(1L, "CS"));
        mockEmployee.setName("Mock Data");

        return Uni.createFrom().item(mockEmployee);
    }

    @Override
    public Uni<Boolean> removeEmployee(Long employeeId) {
        boolean isDeleted = false;
        return Uni.createFrom().item(isDeleted);
    }
}
