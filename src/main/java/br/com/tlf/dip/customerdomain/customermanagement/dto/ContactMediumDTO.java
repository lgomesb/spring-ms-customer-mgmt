package br.com.tlf.dip.customerdomain.customermanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContactMediumDTO {

	private String emailAddress;
	private String phoneNumber;
	private Boolean preferred;

}
