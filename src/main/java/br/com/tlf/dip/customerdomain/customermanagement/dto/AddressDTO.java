package br.com.tlf.dip.customerdomain.customermanagement.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.tlf.dip.customerdomain.customermanagement.model.Address;
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
	
	public AddressDTO(Address a) {
		this.city = a.getCity();
		this.country = a.getCountry();
		this.neighborhood = a.getNeighborhood();
		this.state = a.getState();
		this.street = a.getStreet();
		this.streetNumber = a.getStreetNumber();
	}

	@JsonIgnore
	public Address getAddress() {
					
		return Address.builder()
				.city(this.city)
				.country(this.country)
				.state(this.state)
				.neighborhood(this.neighborhood)
				.street(this.street)
				.streetNumber(this.streetNumber)
				.lastDate(LocalDateTime.now())
				.build();
	}
}
