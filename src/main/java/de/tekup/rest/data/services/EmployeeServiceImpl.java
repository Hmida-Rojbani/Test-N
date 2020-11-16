package de.tekup.rest.data.services;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.tekup.rest.data.models.AddressEntity;
import de.tekup.rest.data.models.DepartementEntity;
import de.tekup.rest.data.models.EmployeeEntity;
import de.tekup.rest.data.models.PhoneNumberEntity;
import de.tekup.rest.data.repositories.AddressRepository;
import de.tekup.rest.data.repositories.DepartementRepository;
import de.tekup.rest.data.repositories.EmployeeRepository;
import de.tekup.rest.data.repositories.PhoneNumberRepository;

@Service
public class EmployeeServiceImpl {

	private EmployeeRepository reposEmployee;
	private AddressRepository reposAddress;
	private PhoneNumberRepository reposPhone;
	private DepartementRepository reposDepart;

	@Autowired
	public EmployeeServiceImpl(EmployeeRepository reposEmployee, AddressRepository reposAddress,
			PhoneNumberRepository reposPhone, DepartementRepository reposDepart) {
		super();
		this.reposEmployee = reposEmployee;
		this.reposAddress = reposAddress;
		this.reposPhone = reposPhone;
		this.reposDepart = reposDepart;
	}

	public List<EmployeeEntity> getAllEmployeeEntities() {
		return reposEmployee.findAll();
	}

	// update that consider depatrs
	public EmployeeEntity createEmployeeEntity(EmployeeEntity employee) {
		// save Address
		AddressEntity address = employee.getAddress();
		AddressEntity addressInBase = reposAddress.save(address);
		System.err.println(addressInBase);
		// save Employee
		EmployeeEntity employeeInBase = reposEmployee.save(employee);
		// save phones
		List<PhoneNumberEntity> phones = employee.getPhones();
		/*
		 * for (PhoneNumberEntity phone : phones) { phone.setEmployee(employeeInBase);
		 * reposPhone.save(phone); }
		 */

		phones.forEach(phone -> phone.setEmployee(employeeInBase));
		reposPhone.saveAll(phones);
		// save Departement
		List<DepartementEntity> requestDepartement = employee.getDeparts();
		List<DepartementEntity> inBaseDepartment = reposDepart.findAll();

		for (DepartementEntity reqDepartement : requestDepartement) {
			if (inBaseDepartment.contains(reqDepartement)) {
				DepartementEntity inBaseDept = inBaseDepartment.get(inBaseDepartment.indexOf(reqDepartement));

				inBaseDept.getEmployees().add(employeeInBase);

				reposDepart.save(inBaseDept);
			} else {
				// List<EmployeeEntity> listEmp = new ArrayList<>();
				// listEmp.add(employeeInBase);
				reqDepartement.setEmployees(Arrays.asList(employeeInBase));
				reposDepart.save(reqDepartement);
			}

		}

		return employeeInBase;
	}

	public EmployeeEntity getEmployeeEntityById(int id) {
		Optional<EmployeeEntity> opt = reposEmployee.findById(id);
		EmployeeEntity employee;
		if (opt.isPresent()) {
			employee = opt.get();
		} else {
			throw new NoSuchElementException("Employee with this id is not found");
		}
		System.err.println(employee.getAddress());
		return employee;
	}

	// update
	public EmployeeEntity modifyEmployeeEntity(int id, EmployeeEntity newEmployee) {
		// there is a better way ? (3 points DS Bonus)
		EmployeeEntity oldEmployee = getEmployeeEntityById(id);
		if (newEmployee.getName() != null)
			oldEmployee.setName(newEmployee.getName());
		if (newEmployee.getDateOfBirth() != null)
			oldEmployee.setDateOfBirth(newEmployee.getDateOfBirth());
		// update address
		AddressEntity newAddress = newEmployee.getAddress();
		AddressEntity oldAddress = oldEmployee.getAddress();
		if (newAddress != null) {
			if (newAddress.getNumber() != 0)
				oldAddress.setNumber(newAddress.getNumber());
			if (newAddress.getStreet() != null)
				oldAddress.setStreet(newAddress.getStreet());
			if (newAddress.getCity() != null)
				oldAddress.setCity(newAddress.getCity());
		}
		// update phones
		List<PhoneNumberEntity> oldPhones = oldEmployee.getPhones();
		List<PhoneNumberEntity> newPhones = newEmployee.getPhones();
		if (newPhones != null) {
			for (PhoneNumberEntity newPhone : newPhones) {
				for (PhoneNumberEntity oldPhone : oldPhones) {
					if (oldPhone.getId() == newPhone.getId()) {
						if (newPhone.getNumber() != null)
							oldPhone.setNumber(newPhone.getNumber());
						if (newPhone.getOperator() != null)
							oldPhone.setOperator(newPhone.getOperator());
						// break over Old loop
						break;
					}
				}
			}
		}

		// update departement

		List<DepartementEntity> oldDeparts = oldEmployee.getDeparts();
		List<DepartementEntity> newDeparts = newEmployee.getDeparts();
		if (newDeparts != null) {
			for (DepartementEntity newDepart : newDeparts) {
				for (DepartementEntity oldDepart : oldDeparts) {
					if (oldDepart.getId() == newDepart.getId()) {
						if (newDepart.getCodeDepart() != null)
							oldDepart.setCodeDepart(newDepart.getCodeDepart());
						if (newDepart.getName() != null)
							oldDepart.setName(newDepart.getName());
					}
				}
			}
		}

		return reposEmployee.save(oldEmployee);
	}

	// delete by Id
	public EmployeeEntity deleteEmployeeEntity(int id) {
		EmployeeEntity employee = getEmployeeEntityById(id);
		reposEmployee.deleteById(id);
		return employee;
	}

	// All Employees with a given operator
	public List<EmployeeEntity> getAllByOperator(String operator) {
		// version 1
		/*
		 * List<EmployeeEntity> employees = reposEmployee.findAll();
		 * List<EmployeeEntity> results = new ArrayList<>(); for (EmployeeEntity
		 * employee : employees) { List<PhoneNumberEntity> phones =
		 * employee.getPhones(); for (PhoneNumberEntity phone : phones) {
		 * if(phone.getOperator().equalsIgnoreCase(operator)) { results.add(employee);
		 * break; }
		 * 
		 * } }
		 */

		// version 2
		/*
		 * Set<EmployeeEntity> results = new HashSet<>(); List<PhoneNumberEntity> phones
		 * = reposPhone.findAll(); for (PhoneNumberEntity phone : phones) {
		 * if(phone.getOperator().equalsIgnoreCase(operator)) {
		 * results.add(phone.getEmployee()); } } return new ArrayList<>(results);
		 */

		// version 3 in Java 8
		/*
		List<EmployeeEntity> results = reposPhone.findAll()// list of Data
				.stream()//

				.filter(phone -> phone.getOperator().equalsIgnoreCase(operator))
				// Stream Contains only phone with given operator

				.map(phone -> phone.getEmployee())
				// Stream of employee have phones with given operator

				.distinct()
				// Stream without duplication

				// collect Stream elements in a list
				.collect(Collectors.toList());

		return results;
		*/
		// version with Query
		return reposPhone.getEmpWithOpt(operator);
	}

	// Average age of all Employees
	public double getAverageAges() {
		/*
		 * Version 1 List<EmployeeEntity> employees = reposEmployee.findAll(); double
		 * sum = 0; for (EmployeeEntity employee : employees) { sum+=employee.getAge();
		 * } return sum / employees.size();
		 */
		// Version Java8

		return reposEmployee.findAll().stream()
				.mapToInt(emp -> emp.getAge())
				.average().orElse(0);
	}

	// Returns Phones operators and numbers of phone for each operators
	public Map<String, Long> getOperatorsAndCount() {
		List<PhoneNumberEntity> phones = reposPhone.findAll();
		Map<String,Long> map2 = new HashMap<>();
		// version 1
		for (PhoneNumberEntity phone : phones) {
			if(map2.containsKey(phone.getOperator())) {
				map2.put(phone.getOperator(), map2.get(phone.getOperator())+1);
			} else {
				map2.put(phone.getOperator(), 1L);
			}
		}
		
		// version 2
		Map<String,Long> map=  phones.stream()
			  .collect(Collectors.groupingBy(phone-> phone.getOperator(),Collectors.counting()));
	
		return map2;
	}

	// return an employee by name
	public EmployeeEntity getByName(String name) {
		List<EmployeeEntity> employees = reposEmployee.findAll();
		// version 1
		/*for (EmployeeEntity employee : employees) {
			if(employee.getName().equalsIgnoreCase(name))
				return employee;
		}
		
		throw new NoSuchElementException("Employee with this name is not found");
		*/
		// version 2
		/*
		return employees.stream()
				 .filter(employee -> employee.getName().equalsIgnoreCase(name))
				 .findFirst()
				 .orElseThrow(()->new NoSuchElementException("Employee with this name is not found"));
		*/
		return reposEmployee.findByNameIgnoreCase(name)
				.orElseThrow(()->new NoSuchElementException("Employee with this name is not found"));
	}

}
