package de.tekup.rest.data.rest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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

}
