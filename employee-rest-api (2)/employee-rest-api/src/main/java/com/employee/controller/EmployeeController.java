package com.employee.controller;
import java.util.List;
import java.util.Map;
//import java.util.Optional;
import java.util.stream.Collectors;

//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.bind.annotation.GetMapping;

import com.employee.entity.Employee;
//import com.employee.entity.LoginForm;
import com.employee.entity.TokenGenerator;
import com.employee.service.EmployeeService;

//import ch.qos.logback.core.model.Model;
//import org.springframework.ui.Model;


@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeService;


    EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    

    @GetMapping
    public List<Employee> getEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable long id) {
        Employee employee = employeeService.getEmployeeById(id);
        if (employee != null) {
            return new ResponseEntity<>(employee, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    

    @PostMapping
    public ResponseEntity<Object> saveEmployee(@RequestBody Employee employee, BindingResult result) {
        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors().stream()
                    .map(error -> error.getField() + ": " + error.getDefaultMessage())
                    .collect(Collectors.toList());
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }

        Employee createdEmployee = employeeService.createEmployee(employee);

        // Generate a token for the created employee
        String token = TokenGenerator.generateToken(String.valueOf(createdEmployee.getId()), 3600000); // 1 hour expiration

        // Print the token to the console
        System.out.println("Generated Token: " + token);

        // Return the token along with the created employee
        return new ResponseEntity<>(Map.of("employee", createdEmployee, "token", token), HttpStatus.CREATED);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateEmployee(@PathVariable long id, @Validated @RequestBody Employee updatedEmployee,
                                                 BindingResult result) {
        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors().stream()
                    .map(error -> error.getField() + ": " + error.getDefaultMessage())
                    .collect(Collectors.toList());
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }

        Employee existingEmployee = employeeService.getEmployeeById(id);

        if (existingEmployee != null) {
            // Update the existing employee with the values from the updatedEmployee
            existingEmployee.setFirstName(updatedEmployee.getFirstName());
            existingEmployee.setLastName(updatedEmployee.getLastName());
            existingEmployee.setAge(updatedEmployee.getAge());

            Employee savedEmployee = employeeService.updateEmployee(id, existingEmployee);
            return new ResponseEntity<>(savedEmployee, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Employee not found", HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable long id) {
        System.out.println("Deleting employee with ID: " + id);

        boolean deleted = employeeService.deleteEmployee(id);

        if (deleted) {
            return new ResponseEntity<>("Deleted", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Employee not found", HttpStatus.NOT_FOUND);
        }
    }

}





