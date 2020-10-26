package de.tekup.rest.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import de.tekup.rest.data.models.EmployeeEntity;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Integer>{

}
