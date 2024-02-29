package com.nandu.employee.services;

import com.nandu.employee.model.Employee;

import java.util.List;

public interface EmployeeService {

Employee createEmployee(Employee employee);

List<Employee> getEmployees();

boolean deleteEmployee(Long id);

Employee getEmployeeById(Long id);

Employee updateEmployee(Long id, Employee request);
}
