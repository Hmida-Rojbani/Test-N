package de.tekup.rest.data.services.dto;

import java.time.LocalDate;

import de.tekup.rest.data.models.AddressEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {

	private int code;

	private String name;

	private int age;
	
	private String fullAddress;
}
