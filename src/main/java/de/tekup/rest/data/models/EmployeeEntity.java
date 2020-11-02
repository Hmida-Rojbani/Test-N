package de.tekup.rest.data.models;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.ToString;

@Entity
@Table(name = "Employee")
@Data
@ToString(exclude = "address")
public class EmployeeEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int code;
	
	@Column(length = 50, name = "employeename", nullable = false, unique = true)
	private String name;
	
	private LocalDate dateOfBirth;
	
	@OneToOne
	@JoinColumn(name = "address")
	private AddressEntity address;

}
