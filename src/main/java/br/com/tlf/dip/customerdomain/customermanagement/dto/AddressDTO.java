package br.com.tlf.dip.customerdomain.customermanagement.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressDTO {
	
	private String city;
	private String country;
	private String state;
	private String neighborhood;
	private String street;
	private String streetNumber;

}
