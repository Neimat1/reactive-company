package com.quarkus.service.one.repository;

import com.quarkus.service.one.entity.Department;
import com.quarkus.service.one.entity.Employee;
import io.quarkus.test.Mock;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Mock
@ApplicationScoped
public class MockEmployeeRepository extends EmployeeRepository {


    static List<Employee> employees = new ArrayList<>();
    static List<Department> departments = new ArrayList<>();

    static {
        Department csDepartment = new Department(1L, "CS");
        Department itDepartment = new Department(2L, "IT");
        departments.add(csDepartment);
        departments.add(itDepartment);

        Employee employee1 = new Employee(1L, "Neimat Soliman", "Software Engineering", csDepartment);
        Employee employee2 = new Employee(2L, "Tokka Ahmed", "Backed Engineering", itDepartment);
        Employee employee3 = new Employee(3L, "Alaa Adel", "Frontend Engineering", csDepartment);
        Employee employee4 = new Employee(4L, "Eman Salah", "AI Engineering", itDepartment);

        employees.add(employee1);
        employees.add(employee2);
        employees.add(employee3);
        employees.add(employee4);
    }

    @Override
    public Uni<List<Employee>> getAllEmployees() {
        return Uni.createFrom().item(employees);
    }

    @Override
    public Uni<Response> addEmployee(Employee employee) {
        if (employee == null || employee.getId() != null
                || employee.getDepartment() == null || employee.getDepartment().getId() == null || employee.getDepartment().getDepartmentName() == null)
            return Uni.createFrom().item(getBadRequestStatus());

        Department existingDepartment = departments.stream()
                .filter(department -> employee.getDepartment().getDepartmentName().equals(department.getDepartmentName()))
                .findFirst()
                .orElse(null);

        if (Objects.isNull(existingDepartment))
            return Uni.createFrom().item(getBadRequestStatus());

        employees.add(employee);
        return Uni.createFrom().item(Response.status(Response.Status.CREATED).build());

    }

    @Override
    public Uni<Boolean> removeEmployee(Long employeeId) {
        Employee employeeWillBeRemoved = employees.stream()
                .filter(employee -> employeeId.equals(employee.getId()))
                .findFirst()
                .orElse(null);

        return Objects.isNull(employeeWillBeRemoved) ? Uni.createFrom().item(Boolean.FALSE) : Uni.createFrom().item(employees.remove(employeeWillBeRemoved));
    }

    @Override
    public Uni<Employee> updateEmployeePosition(Long employeeId, String updatedPosition) {
        Employee existingEmployee = employees.stream()
                .filter(employee -> employeeId.equals(employee.getId()))
                .findFirst()
                .orElse(null);


        if (Objects.isNull(existingEmployee))
            return Uni.createFrom().nullItem();

        int indexOfUpdatedEmployee = employees.indexOf(existingEmployee);
        employees.get(indexOfUpdatedEmployee).setPosition(updatedPosition);
        return Uni.createFrom().item(employees.get(indexOfUpdatedEmployee));
    }

    @Override
    public Uni<Employee> getEmployeeById(Long employeeId) {
        Employee existingEmployee = employees.stream()
                .filter(employee -> employeeId.equals(employee.getId()))
                .findFirst()
                .orElse(null);

        return Objects.nonNull(existingEmployee) ? Uni.createFrom().item(existingEmployee) : Uni.createFrom().nullItem();
    }

    private Response getBadRequestStatus() {
        return Response.status(Response.Status.BAD_REQUEST).build();
    }


}
