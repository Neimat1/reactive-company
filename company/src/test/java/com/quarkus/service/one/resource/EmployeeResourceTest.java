package com.quarkus.service.one.resource;

import com.quarkus.service.one.entity.Department;
import com.quarkus.service.one.entity.Employee;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

@QuarkusTest
public class EmployeeResourceTest {


    @Test
    public void testGetAllEmployees() {
        given()
                .when().get("/employees")
                .then()
                .statusCode(200)
                .body("size()", is(4));
    }


    @Test
    public void testGetEmployeeByIdSuccessScenario() {
        Long employeeId = 1L;
        String employeeName = "Neimat Soliman";

        given()
                .pathParam("id", employeeId)
                .when().get("/employees/{id}")
                .then()
                .statusCode(200)
                .body("name", is(employeeName));
    }

    @Test
    public void testGetEmployeeByIdFailureScenario_IDNotExist() {
        Long employeeId = 10000L;

        given()
                .pathParam("id", employeeId)
                .when().get("/employees/{id}")
                .then()
                .statusCode(204);
    }


    @Test
    public void testAddEmployeeSuccessScenario() {
        Employee mockEmployee = new Employee();
        mockEmployee.setPosition("Mock Data");
        mockEmployee.setDepartment(new Department(1L, "CS"));
        mockEmployee.setName("Mock Data");

        given()
                .header("Content-Type", "application/json")
                .body(mockEmployee)
                .when().post("/employees/")
                .then().statusCode(201);

    }

    @Test
    public void testAddEmployeeFailureScenario_passingEmployeeID() {
        Department itDepartment = new Department(2L, "IT");
        Employee existemployee = new Employee(4L, "Eman Salah", "AI Engineering", itDepartment);


        given()
                .header("Content-Type", "application/json")
                .body(existemployee)
                .when().post("/employees/")
                .then()
                .statusCode(400);

    }

    @Test
    public void testAddEmployeeFailureScenario_missingDepartment() {
        Employee existemployee = new Employee(4L, "Eman Salah", "AI Engineering", null);


        given()
                .header("Content-Type", "application/json")
                .body(existemployee)
                .when().post("/employees/")
                .then()
                .statusCode(400);

    }

    @Test
    public void testAddEmployeeFailureScenario_departmentNotExist() {
        Department isDepartment = new Department(3L, "IS");
        Employee existemployee = new Employee(4L, "Eman Salah", "AI Engineering", isDepartment);


        given()
                .header("Content-Type", "application/json")
                .body(existemployee)
                .when().post("/employees/")
                .then()
                .statusCode(400);

    }

    @Test
    public void testAddEmployeeFailureScenario_missingDepartmentName() {
        Department isDepartment = new Department(3L, null);
        Employee existemployee = new Employee(4L, "Eman Salah", "AI Engineering", isDepartment);


        given()
                .header("Content-Type", "application/json")
                .body(existemployee)
                .when().post("/employees/")
                .then()
                .statusCode(400);

    }


    @Test
    public void testUpdateEmployeePositionSuccessScenario() {
        Long employeeId = 2L;
        String updatedPosition = "Sr. Software Engineering";
        String employeeName = "Tokka Ahmed";

        given()
                .header("Content-Type", "application/json")
                .pathParam("id", employeeId)
                .body(updatedPosition)
                .when().put("/employees/{id}")
                .then()
                .statusCode(200);


        given()
                .pathParam("id", employeeId)
                .when().get("/employees/{id}")
                .then()
                .statusCode(200)
                .body("name", is(employeeName))
                .body("position", is(updatedPosition));
    }

    @Test
    public void testUpdateEmployeePositionFailureScenario_EmployeeNotExist() {
        Long employeeId = 2000L;
        String updatedPosition = "Sr. Software Engineering";

        given()
                .header("Content-Type", "application/json")
                .pathParam("id", employeeId)
                .body(updatedPosition)
                .when().put("/employees/{id}")
                .then()
                .statusCode(204);
    }


    @Test
    public void testRemoveEmployeeSuccessScenario() {
        Long employeeId = 1L;
        boolean isDeleted = true;

        given()
                .pathParam("id", employeeId)
                .when().delete("/employees/{id}")
                .then()
                .statusCode(200)
                .equals(isDeleted);

    }

    @Test
    public void testRemoveEmployeeFailureScenario_employeeIDNotExist() {
        Long employeeId = 10000L;
        boolean isDeleted = false;

        given()
                .pathParam("id", employeeId)
                .when().delete("/employees/{id}")
                .then()
                .statusCode(200)
                .equals(isDeleted);

    }


}
