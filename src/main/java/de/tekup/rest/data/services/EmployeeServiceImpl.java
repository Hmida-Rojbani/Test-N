package de.tekup.rest.data.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

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
	
	public EmployeeEntity getEmployeeEntityById(int id) {
		Optional<EmployeeEntity> opt = reposEmployee.findById(id);
		EmployeeEntity employee;
		if (opt.isPresent()) {
			employee = opt.get();
		}else {
			throw new NoSuchElementException("Employee with this id is not found");
		}
		return employee;	
	}
	
	//update 
	public EmployeeEntity modifyEmployeeEntity(int id, EmployeeEntity newEmployee) {
		EmployeeEntity employee = getEmployeeEntityById(id);
		if(newEmployee.getName() != null)
			employee.setName(newEmployee.getName());
		if(newEmployee.getDateOfBirth() != null)
			employee.setDateOfBirth(newEmployee.getDateOfBirth());
		return reposEmployee.save(employee);
	}
	
	
	// delete by Id
	public EmployeeEntity deleteEmployeeEntity(int id) {
		return null;
	}
	
}
