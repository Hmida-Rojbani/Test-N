package de.tekup.rest.data.services.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartementRequestDTO {
	private String name;
	private String codeDepart;
}
