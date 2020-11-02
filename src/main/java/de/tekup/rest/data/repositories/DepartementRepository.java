package de.tekup.rest.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import de.tekup.rest.data.models.DepartementEntity;

public interface DepartementRepository extends JpaRepository<DepartementEntity, Integer>{

}
