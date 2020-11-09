package de.tekup.rest.data.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(of = {"codeDepart"})
public class DepartementEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;
	@Column(unique = true, nullable = false)
	private String codeDepart;
	
	@ManyToMany
	@JoinTable(name = "WorkedIn")
	@JsonIgnore
	private List<EmployeeEntity> employees;


	
}
