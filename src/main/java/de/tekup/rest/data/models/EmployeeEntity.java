package de.tekup.rest.data.models;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "Employee")
@Data
public class EmployeeEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int code;
	
	@Column(length = 50, name = "employeename", nullable = false, unique = true)
	private String name;
	
	private LocalDate dateOfBirth;

}
