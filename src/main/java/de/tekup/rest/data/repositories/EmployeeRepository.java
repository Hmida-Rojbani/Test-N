package de.tekup.rest.data.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import de.tekup.rest.data.models.EmployeeEntity;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Integer>{

	Optional<EmployeeEntity> findByName(String name);
	Optional<EmployeeEntity> findByNameIgnoreCase(String name);
}
