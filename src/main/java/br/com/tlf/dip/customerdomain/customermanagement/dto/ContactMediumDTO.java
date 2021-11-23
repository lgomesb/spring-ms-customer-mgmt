package br.com.tlf.dip.customerdomain.customermanagement.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContactMediumDTO {

	private String emailAddress;
	private String phoneNumber;
	private Boolean preferred;

}
