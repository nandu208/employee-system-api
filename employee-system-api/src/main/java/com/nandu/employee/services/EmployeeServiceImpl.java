package com.nandu.employee.services;

import com.nandu.employee.entities.EmployeeEntity;
import com.nandu.employee.model.Employee;
import com.nandu.employee.repository.EmployeeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

@Autowired
private EmployeeRepository employeeRepository;

@Override
public Employee createEmployee(Employee employee) {
	EmployeeEntity employeeEntity = new EmployeeEntity();
	BeanUtils.copyProperties(employee, employeeEntity);
	employeeRepository.save(employeeEntity);
	return employee;
}

@Override
public List<Employee> getEmployees() {
	List<EmployeeEntity> employeeEntities = employeeRepository.findAll();
	List<Employee> employees = employeeEntities.stream().map(emp -> new Employee(emp.getId(), emp.getFirstName(), emp.getLastName(), emp.getEmailId())).collect(Collectors.toList());
	return employees;
}

@Override
public boolean deleteEmployee(Long id) {
EmployeeEntity employeeEntity = employeeRepository.findById(id).get();
employeeRepository.delete(employeeEntity);
return  true;
}

@Override
public Employee getEmployeeById(Long id) {
	EmployeeEntity employeeEntity = employeeRepository.findById(id).get();
	Employee employee = new Employee();
	BeanUtils.copyProperties(employeeEntity,employee);
	return employee;
}

@Override
public Employee updateEmployee(Long id, Employee request) {
	EmployeeEntity employeeEntity = employeeRepository.findById(id).get();
	employeeEntity.setFirstName(request.getFirstName());
	employeeEntity.setLastName(request.getLastName());
	employeeEntity.setEmailId(request.getEmailId());
	employeeRepository.save(employeeEntity);
	return  request;
}


}
