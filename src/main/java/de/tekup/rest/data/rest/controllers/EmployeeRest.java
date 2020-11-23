package de.tekup.rest.data.rest.controllers;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import de.tekup.rest.data.models.EmployeeEntity;
import de.tekup.rest.data.services.EmployeeServiceImpl;
import de.tekup.rest.data.services.dto.EmployeeDTO;
import de.tekup.rest.data.services.dto.EmployeeRequestDTO;

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
	public EmployeeDTO getById(@PathVariable("id") int code){
		return service.getEmployeeEntityById(code);
	}
	
	@PostMapping(path = "/api/employees")
	public EmployeeDTO createEmloyee(@Valid @RequestBody EmployeeRequestDTO employee) {
		return service.createEmployeeEntity(employee);
	}
	
	@PutMapping(path = "/api/employees/{id}")
	public EmployeeEntity modifyEmloyee(@PathVariable("id") int code,@RequestBody EmployeeEntity newEmployee) {
		return service.modifyEmployeeEntity(code, newEmployee);
	}
	
	@DeleteMapping(path = "/api/employees/{id}")
	public EmployeeEntity deleteEmployee(@PathVariable("id") int code) {
		return service.deleteEmployeeEntity(code);
	}
	
	@GetMapping(path="/api/employees/operator/{operator}")
	public List<EmployeeEntity> getAllByOperator(@PathVariable("operator") String operator){
		return service.getAllByOperator(operator);
	}
	
	@GetMapping(path="/api/employees/average/age")
	public double getEmployeeAverageAge(){
		return service.getAverageAges();
	}
	
	@GetMapping(path="/api/employees/count/operator")
	public Map<String, Long> getPhoneOperatorAndCount(){
		 return service.getOperatorsAndCount();
	}
	@GetMapping(path="/api/employees/name/{name}")
	public EmployeeEntity getEmployeeByName(@PathVariable("name") String name){
		 return service.getByName(name);
	}
	
	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<String> handleNoSuchElementException(NoSuchElementException e) {
		return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
		 // To Return 1 validation error
		//return new ResponseEntity<String>(e.getBindingResult().getAllErrors().get(0).getDefaultMessage(), HttpStatus.BAD_REQUEST);
		StringBuilder errors = new StringBuilder();
		for (FieldError error : e.getBindingResult().getFieldErrors()) {
			errors.append(error.getField() + ": "+ error.getDefaultMessage()+".\n");
		}
		return new ResponseEntity<String>(errors.toString(), HttpStatus.BAD_REQUEST);
	}

}
