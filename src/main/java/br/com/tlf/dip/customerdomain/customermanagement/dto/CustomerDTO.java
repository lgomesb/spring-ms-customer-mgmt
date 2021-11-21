package br.com.tlf.dip.customerdomain.customermanagement.dto;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
