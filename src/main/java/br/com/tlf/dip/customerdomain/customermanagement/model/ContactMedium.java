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
public class ContactMedium {

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private Integer id;
	
	private String emailAddress;
	private String phoneNumber;
	private Boolean preferred;
	private LocalDateTime creationDate;
	private LocalDateTime lastUpdate;
	
	
	@ManyToOne
	@JoinColumn( name = "customer_id")
	private Customer customer;
		
}
