package de.tekup.rest.data.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import de.tekup.rest.data.models.EmployeeEntity;
import de.tekup.rest.data.models.PhoneNumberEntity;

public interface PhoneNumberRepository extends JpaRepository<PhoneNumberEntity, Integer>{
	//JPQL
	@Query("select distinct(p.employee) from PhoneNumberEntity p "
			+ "where lower(p.operator) = lower(:opt)")
	List<EmployeeEntity> getEmpWithOpt(@Param("opt") String operator);
}
