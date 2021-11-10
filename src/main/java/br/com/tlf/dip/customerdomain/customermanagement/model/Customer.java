package br.com.tlf.dip.customerdomain.customermanagement.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table
public class Customer {
	
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private Integer id;	
	private String name;
	private LocalDate birthdate;
	private String documentNumber;
	private String documentType;
	private LocalDateTime creationDate;
	private LocalDateTime lastUpdate;
	private LocalDate inactive;
	
	@OneToMany( mappedBy = "customer", cascade = CascadeType.ALL)
	private List<Address> address;
	
	@OneToMany( mappedBy = "customer", cascade = CascadeType.ALL )
	private List<ContactMedium> contacts;

}
