package com.employee.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.employee.entity.Employee;
import com.employee.repository.EmployeeRepository;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(long id) {
        return employeeRepository.findById(id).orElseThrow();
    }

    public Employee createEmployee(Employee employee) {
        employeeRepository.save(employee);
        return employee;
    }

    public boolean deleteEmployee(long id) {
        employeeRepository.deleteById(id);
        return true; // Fix the return value
    }

    public Employee updateEmployee(long id, Employee employee) {
        employeeRepository.save(employee);
        return employee;
    }

    public Employee getEmployeeByUsername(String username) {
        return employeeRepository.findByUsername(username);
    }

    public boolean validateEmployee(String username, String password) {
        System.out.println("Validating credentials for username: " + username);
        System.out.println("Password: " + password);

        Employee employee = employeeRepository.findByUsernameAndPassword(username.trim(), password.trim());

        if (employee != null) {
            System.out.println("Validation successful for employee with ID: " + employee.getId());
            return true;
        } else {
            System.out.println("Validation failed. No employee found for the provided credentials.");
            return false;
        }
    }
}
