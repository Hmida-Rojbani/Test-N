package de.tekup.rest.data.rest.controllers;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import de.tekup.rest.data.models.EmployeeEntity;
import de.tekup.rest.data.services.EmployeeServiceImpl;

@RestController
public class EmployeeRest {
	
	private EmployeeServiceImpl service;
	
	@Autowired	
	public EmployeeRest(EmployeeServiceImpl service) {
		super();
		this.service = service;
	}


	@GetMapping(path="/api/employees")
	public List<EmployeeEntity> getAll(){
		return service.getAllEmployeeEntities();
	}
	
	@GetMapping(path="/api/employees/{id}")
	public EmployeeEntity getById(@PathVariable("id") int code){
		return service.getEmployeeEntityById(code);
	}
	
	@PostMapping(path = "/api/employees")
	public EmployeeEntity createEmloyee(@RequestBody EmployeeEntity employee) {
		return service.createEmployeeEntity(employee);
	}
	
	@PutMapping(path = "/api/employees/{id}")
	public EmployeeEntity modifyEmloyee(@PathVariable("id") int code,@RequestBody EmployeeEntity newEmployee) {
		return service.modifyEmployeeEntity(code, newEmployee);
	}
	
	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<String> handleNoSuchElementException(NoSuchElementException e) {
		return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
	}

}
