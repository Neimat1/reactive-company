package com.quarkus.service.one.resource;

import com.quarkus.service.one.entity.Department;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

@QuarkusTest
public class DepartmentResourceTest {




    @Test
    public void testGetAllDepartmentsSuccessfully() {
        Department csDepartment = new Department(1L, "CS");
        Department itDepartment = new Department(2L, "IT");

       List<Department> departments = new ArrayList<>();
       departments.add(csDepartment);
       departments.add(itDepartment);

        given()
                .when().get("/departments")
                .then().statusCode(200).body("size()", is(departments.size()));
    }


    @Test
    public void testFindDepartmentByNameSuccessfully() {
        String departmentName = "CS";

        given()
                .pathParam("department_name", departmentName)
                .when().get("/departments/{department_name}")
                .then().statusCode(200).body("departmentName", is(departmentName));
    }

    @Test
    public void testFindDepartmentByNameNoContentScenario() {
        String departmentName = "Mock Data";

        given()
                .pathParam("department_name", departmentName)
                .when().get("/departments/{department_name}")
                .then().statusCode(204);
    }


}
