# rest-client (Service-Two)

_Microservice, that consumes service-one:_
* Get All Employees - GET
* Add new Employee - POST
* Update Employee's position - PUT
* Remove Employee - DELETE


## Running the application in dev mode

You can run your application in dev mode that enables live coding using:

```shell script
./mvnw compile quarkus:dev
```

## Testing the endpoints
* you can test the endpoints by go to the below folder ,and you can find the http requests.
    ```
    /src/main/http
    ```
* Run postman collection service-two

> **_NOTE: Service-Two_**
> * Now you can open Swagger-Ui by <http://localhost:8090/q/swagger-ui/>.
> * Or generate Openapi by <http://localhost:8090/q/openapi/>.
> * Run postman collection service-two