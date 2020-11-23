package de.tekup.rest.data.services.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressRequestDTO {
	
	@Positive
	private int number;
	@NotBlank
	@Size(min=5)
	private String street;
	@NotBlank
	@Size(min=5)
	private String city;

}
