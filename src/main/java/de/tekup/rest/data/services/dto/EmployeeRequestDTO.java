package de.tekup.rest.data.services.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.modelmapper.ModelMapper;

import de.tekup.rest.data.models.AddressEntity;
import de.tekup.rest.data.models.DepartementEntity;
import de.tekup.rest.data.models.PhoneNumberEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeRequestDTO {
	
	private static final ModelMapper mapper = new ModelMapper();
	
	@NotBlank(message = "Name Requered to have characters")
	@Size(min = 5, max = 50)
	private String name;
	@Past
	private LocalDate dateOfBirth;
	@Valid
	private AddressRequestDTO address;
	
	private List<PhoneRequestDTO> phones;
	
	private List<DepartementRequestDTO> departs;
	
	public AddressEntity getAddress() {
		return mapper.map(address, AddressEntity.class);
	}
	
	public List<PhoneNumberEntity> getPhones(){
			return phones.stream()
					.map(p -> mapper.map(p,PhoneNumberEntity.class))
					.collect(Collectors.toList());
	}
	
	public List<DepartementEntity> getDeparts(){
		return departs.stream()
				.map(d -> mapper.map(d, DepartementEntity.class))
				.collect(Collectors.toList());
	}	

}
