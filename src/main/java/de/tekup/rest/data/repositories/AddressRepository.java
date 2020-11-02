package de.tekup.rest.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import de.tekup.rest.data.models.AddressEntity;

public interface AddressRepository extends JpaRepository<AddressEntity, Integer>{

}
