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
	
	public List<Employee>getAllEmployees(){
		return employeeRepository.findAll();
	}
	
	public Employee getEmployeeById(long id){
		return employeeRepository.findById(id).orElseThrow();
	}
	
	public Employee createEmployee(Employee employee) {
		employeeRepository.save(employee);
		return employee;
	}
	

//	public Employee updateEmployee(Employee employee) {
//		employeeRepository.save(employee);
//		return employee;
//	}
	

	public boolean deleteEmployee(long id) {
		employeeRepository.deleteById(id);
		return false;
	}

	public Employee updateEmployee(long id, Employee employee) {		
	employeeRepository.save(employee);
	return employee;
	}
	
}
