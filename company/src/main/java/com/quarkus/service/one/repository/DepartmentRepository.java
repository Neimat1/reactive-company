package com.quarkus.service.one.repository;

import com.quarkus.service.one.entity.Department;
import io.quarkus.hibernate.reactive.panache.PanacheRepository;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class DepartmentRepository implements PanacheRepository<Department> {


    public Uni<List<Department>> getAllDepartments() {
        return listAll();
    }

    public Uni<Department> findDepartmentByName(String departmentName) {
        return find("departmentName", departmentName).firstResult();
    }
}
