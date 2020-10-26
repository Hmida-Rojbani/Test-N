package de.tekup.rest.data.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.tekup.rest.data.models.EmployeeEntity;
import de.tekup.rest.data.repositories.EmployeeRepository;

@Service
public class EmployeeServiceImpl {

	private EmployeeRepository reposEmployee;

	@Autowired
	public EmployeeServiceImpl(EmployeeRepository reposEmployee) {
		super();
		this.reposEmployee = reposEmployee;
	}
	
	public List<EmployeeEntity> getAllEmployeeEntities(){
		return reposEmployee.findAll();
	}
	
	public EmployeeEntity createEmployeeEntity(EmployeeEntity employee) {
		return reposEmployee.save(employee);
	}
	
}
