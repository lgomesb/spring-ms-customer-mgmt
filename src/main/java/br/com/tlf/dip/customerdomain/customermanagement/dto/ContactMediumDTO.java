package br.com.tlf.dip.customerdomain.customermanagement.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.tlf.dip.customerdomain.customermanagement.model.ContactMedium;
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
	
	public ContactMediumDTO(ContactMedium c) {
		
		this.emailAddress = c.getEmailAddress();
		this.phoneNumber = c.getPhoneNumber();
		this.preferred = c.getPreferred();		
	}

	@JsonIgnore
	public ContactMedium getContact() {
		
		return ContactMedium.builder()
				.emailAddress(this.emailAddress)
				.phoneNumber(this.phoneNumber)
				.preferred(this.preferred)
				.lastUpdate(LocalDateTime.now())
				.build();
	}
}
