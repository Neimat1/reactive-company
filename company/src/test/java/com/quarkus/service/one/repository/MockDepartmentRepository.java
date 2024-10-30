package com.quarkus.service.one.repository;

import com.quarkus.service.one.entity.Department;
import io.quarkus.test.Mock;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Mock
@ApplicationScoped
public class MockDepartmentRepository extends DepartmentRepository {


    @Override
    public Uni<List<Department>> getAllDepartments() {

        List<Department> departments = new ArrayList<>();
        Department csDepartment = new Department(1L, "CS");
        Department itDepartment = new Department(2L, "IT");

        departments.add(csDepartment);
        departments.add(itDepartment);


        return Uni.createFrom().item(departments);
    }

    @Override
    public Uni<Department> findDepartmentByName(String departmentName) {
        List<Department> departments = new ArrayList<>();
        Department csDepartment = new Department(1L, "CS");
        Department itDepartment = new Department(2L, "IT");

        departments.add(csDepartment);
        departments.add(itDepartment);
        Department result = departments.stream()
                .filter(department -> departmentName.equals(department.getDepartmentName()))
                .findFirst()
                .orElse(null);

        return Objects.nonNull(result) ? Uni.createFrom().item(result) : Uni.createFrom().nullItem();
    }

}
