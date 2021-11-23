package br.com.tlf.dip.customerdomain.customermanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddressDTO {
	
	private String city;
	private String country;
	private String state;
	private String neighborhood;
	private String street;
	private String streetNumber;

}
