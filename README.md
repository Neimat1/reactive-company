# reactive-company

This project is consisting of 2 microservices:

1. ### **service-one**:

    _Microservice, that does:_
      * Get All Employees - GET
      * Add new Employee - POST
      * Update Employee's position - PUT
      * Remove Employee - DELETE
      
     _UML Diagram:_

    ```mermaid
    classDiagram
        class Employee {
             - name: string
             - position: string
             - department: Department
             - id: Long
        }
         
         
         class Department {
            - departmentName: string
            - id: Long
         }
         
         Department "1" --> "*" Employee
    ```

2. ### **service-two**:
   _Microservice, that consumes service-one:_
     * Get All Employees - GET
     * Add new Employee - POST
     * Update Employee's position - PUT
     * Remove Employee - DELETE

### To Run the project
* Run Docker Engine
* clone the repo
* Once you are in the project repo
* Open the terminal then write the commands
  * cd .\rest-client\
  * ./mvnw compile quarkus:dev
  * Open another terminal page to run service-one
    * cd .\company\
    * ./mvnw compile quarkus:dev
* Now you have
    * Rest-Client (Service-two) running on 8090
    * Company (Service-one) running on 8080

> **<span style="color:#2F5EBD;">NOTE</span>**
> * you can use postman collection / http requests in each repo
    > There's a Readme file for each service

> **<span style="color:#2F5EBD;">NOTE: Service-one</span>**
> * now you can Swagger-Ui at <http://localhost:8080/q/swagger-ui/>.
> * or generate Openapi by <http://localhost:8080/q/openapi/>.


> **<span style="color:#2F5EBD;">NOTE: Service-Two</span>**
> * now you can Swagger-Ui at <http://localhost:8090/q/swagger-ui/>.
> * or generate Openapi by <http://localhost:8090/q/openapi/>.


### Versions:
* java-11
* Apache Maven 3.9.3
* Postgresql
* Docker Engine - Mandatory
* Quarkus 3.2.12.FINAL
