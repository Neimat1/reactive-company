package com.quarkus.service.two.service;

import com.quarkus.service.one.entity.Employee;
import com.quarkus.service.two.rest.CompanyClient;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.List;

@ApplicationScoped
public class RestClientServiceImpl implements RestClientService {

    @Inject
    @RestClient
    CompanyClient companyClient;

    @Override
    public Uni<List<Employee>> getAllEmployees() {
        return companyClient.getAllEmployees().onFailure()
                .recoverWithNull();
    }

    @Override
    public Uni<Employee> getEmployeeById(Long id) {
        return companyClient.getEmployeeById(id).onFailure().recoverWithNull();
    }

    @Override
    public Uni<Response> addEmployee(Employee employee) {
        return companyClient.addEmployee(employee).onFailure().recoverWithItem(CallBackFailure());
    }

    @Override
    public Uni<Employee> updateEmployeePosition(Long id, String updatedPosition) {
        return companyClient.updateEmployeePosition(id, updatedPosition).onFailure().recoverWithNull();
    }

    @Override
    public Uni<Boolean> removeEmployee(Long employeeId) {
        return companyClient.removeEmployee(employeeId).onFailure().recoverWithNull();
    }

    private Response CallBackFailure() {
        return Response.ok().entity("Down Time!!!").build();
    }

}
