package com.quarkus.service.two.resource;

import com.quarkus.service.one.entity.Department;
import com.quarkus.service.one.entity.Employee;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

@QuarkusTest
public class RestClientResourceTest {


    @Test
    public void testGetAllEmployees() {
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

        given()
                .when().get("/rest-client")
                .then().statusCode(200).body("size()", is(employees.size()));
    }


    @Test
    public void testGetEmployeeById() {
        Long employeeId = 1L;
        String employeeName = "Mock Data";

        given()
                .pathParam("id", employeeId)
                .when().get("/rest-client/{id}")
                .then().statusCode(200).body("name", is(employeeName));
    }


    @Test
    public void testAddEmployee() {
        Employee mockEmployee = new Employee();
        mockEmployee.setId(1L);
        mockEmployee.setPosition("Mock Data");
        mockEmployee.setDepartment(new Department(1L, "CS"));
        mockEmployee.setName("Mock Data");

        given()
                .header("Content-Type", "application/json")
                .body(mockEmployee)
                .when().post("/rest-client/")
                .then().statusCode(201);

    }


    @Test
    public void testUpdateEmployeePosition() {
        Long employeeId = 1L;
        String updatedPosition = "Mock Data";
        String employeeName = "Mock Data";

        given()
                .header("Content-Type", "application/json")
                .pathParam("id", employeeId)
                .queryParam("position", updatedPosition)
                .when().get("/rest-client/{id}")
                .then().statusCode(200).body("name", is(employeeName))
                .body("position", is(updatedPosition));

    }

    @Test
    public void testRemoveEmployee() {
        Long employeeId = 500L;
        boolean isDeleted = false;

        given()
                .pathParam("id", employeeId)
                .when().delete("/rest-client/{id}")
                .then().statusCode(200).equals(isDeleted);

    }


}
