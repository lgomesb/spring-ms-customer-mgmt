package br.com.tlf.dip.customerdomain.customermanagement.dto;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CustomerDTO {

	@JsonProperty(access = Access.READ_ONLY)
	private Integer id;	
	
	private String name;
	private LocalDate birthdate;
	private String documentNumber;
	private String documentType;	
	private LocalDate inactive;
	private List<AddressDTO> address;
	private List<ContactMediumDTO> contacts;
	
}
