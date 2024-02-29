package com.nandu.employee.controller;

import com.nandu.employee.model.Employee;
import com.nandu.employee.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/employees")
    public Employee createEmployee(@RequestBody  Employee employee){
        return employeeService.createEmployee(employee);
    }

    @GetMapping("/employees")
    public List<Employee> getAllEmployee(){
     return employeeService.getEmployees();
    }
	
	@DeleteMapping("/employees/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable Long id){
		boolean deleted = false;
		deleted = employeeService.deleteEmployee(id);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", deleted);
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee> getEmployeeById( @PathVariable Long id){
		Employee employee = null;
		employee = employeeService.getEmployeeById(id);
		return ResponseEntity.ok(employee);
	}
	
	@PutMapping("/employees/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee request){
		Employee employee = employeeService.updateEmployee(id, request);
		return ResponseEntity.ok(employee);
	}

}
