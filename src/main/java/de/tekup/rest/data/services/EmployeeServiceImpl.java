package de.tekup.rest.data.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.tekup.rest.data.models.AddressEntity;
import de.tekup.rest.data.models.EmployeeEntity;
import de.tekup.rest.data.models.PhoneNumberEntity;
import de.tekup.rest.data.repositories.AddressRepository;
import de.tekup.rest.data.repositories.EmployeeRepository;
import de.tekup.rest.data.repositories.PhoneNumberRepository;

@Service
public class EmployeeServiceImpl {

	private EmployeeRepository reposEmployee;
	private AddressRepository reposAddress;
	private PhoneNumberRepository reposPhone;

	@Autowired
	public EmployeeServiceImpl(EmployeeRepository reposEmployee, AddressRepository reposAddress,
			PhoneNumberRepository reposPhone) {
		super();
		this.reposEmployee = reposEmployee;
		this.reposAddress = reposAddress;
		this.reposPhone = reposPhone;
	}

	public List<EmployeeEntity> getAllEmployeeEntities(){
		return reposEmployee.findAll();
	}
	
	// update that consider phones
	public EmployeeEntity createEmployeeEntity(EmployeeEntity employee) {
		AddressEntity address = employee.getAddress();
		AddressEntity addressInBase = reposAddress.save(address);
		 System.err.println(addressInBase);
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
		System.err.println(employee.getAddress());
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
		EmployeeEntity employee = getEmployeeEntityById(id);
		reposEmployee.deleteById(id);
		return employee;
	}
	
}
