# Springboot_RestfulApi   
Employee REST API
This project is a simple Spring Boot application that provides CRUD operations for managing employee records.

Table of Contents
Prerequisite
Endpoints
Testing
Dependencies
Contributing
Prerequisites
Before running the application, ensure you have the following:

Java Development Kit (JDK) installed (version 17 or later)
Maven installed
MySQL or another relational database installed and running
An integrated development environment (IDE) such as IntelliJ IDEA or Eclipse
Navigate to the project directory:
 employee-rest-api
Configure the database settings in src/main/resources/application.properties.

Build the project:
mvn clean install
Run the application:
mvn spring-boot:run
The application should be accessible at http://localhost:8080.

Endpoints
The following endpoints are available:

GET /employee: Retrieve all employees.

GET /employee/{id}: Retrieve an employee by ID.

POST /employee: Create a new employee.

PUT /employee/{id}: Update an existing employee.

DELETE /employee/{id}: Delete an employee by ID.

Testing
Use tools like Postman or curl to test the CRUD operations. Example:

curl -X GET http://localhost:8080/employee
Dependencies
Spring Boot
Spring Data JPA
MySQL Connector
JWT (JSON Web Token)
Contributing
Fe
