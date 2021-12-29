package com.pagination.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pagination.entity.Employee;
import com.pagination.repository.EmployeeRepository;

@RestController
public class EmployeeController {
	
	@Autowired
	private EmployeeRepository employeeRepository;

	@PostMapping
	public ResponseEntity<?> insert(@RequestBody Employee employee){
		Optional<Employee> optonalEmployee = employeeRepository.findById(employee.getId());
		if(optonalEmployee.isPresent()) {
			return ResponseEntity.unprocessableEntity().body("Id already exist");
		}
		else {
			Employee insertEmployee = new Employee();
			insertEmployee.setId(employee.getId());
			insertEmployee.setCity(employee.getCity());
			insertEmployee.setName(employee.getName());
			employeeRepository.save(insertEmployee);
			return ResponseEntity.ok().body("Inserted Successfully");
		}
	}
	
	@GetMapping
	public ResponseEntity<?> get(){
		return ResponseEntity.ok().body(employeeRepository.find());
	}
	
	@GetMapping("/get")
	public ResponseEntity<?> getByName(@RequestParam(required = false) Optional<String> search,
									   @RequestParam(required = false,defaultValue = "0") int pageNumber){
		Pageable visiblePage = PageRequest.of(pageNumber, 10);
		Page<Employee> pagination; 
		if(search.isPresent()) {
			pagination = employeeRepository.findBySearch(search.get(),visiblePage);
			return ResponseEntity.ok(pagination);
		}
		pagination = employeeRepository.findAll(visiblePage);
		return ResponseEntity.ok(pagination);
	}
}
