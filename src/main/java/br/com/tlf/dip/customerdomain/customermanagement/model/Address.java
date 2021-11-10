package br.com.tlf.dip.customerdomain.customermanagement.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Address {

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private Integer id;	
	private String city;
	private String country;
	private String state;
	private String neighborhood;
	private String street;
	private String streetNumber;
	private LocalDateTime creationDate;
	private LocalDateTime lastDate;
	
	@ManyToOne
	@JoinColumn( name = "customer_id")
	private Customer customer;
	
}
