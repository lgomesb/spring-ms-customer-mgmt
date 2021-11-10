package br.com.tlf.dip.customerdomain.customermanagement.dto.in;

import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.tlf.dip.customerdomain.customermanagement.dto.CustomerDTO;
import br.com.tlf.dip.customerdomain.customermanagement.model.Address;
import br.com.tlf.dip.customerdomain.customermanagement.model.ContactMedium;
import br.com.tlf.dip.customerdomain.customermanagement.model.Customer;


public class CustomerInDTO extends CustomerDTO {

	@JsonIgnore
	public Customer getCustomer() {
		
		List<Address> address = this.getAddress().stream().map( a -> a.getAddress() ).collect(Collectors.toList());
		List<ContactMedium> contacts = this.getContacts().stream().map( c -> c.getContact()).collect(Collectors.toList());
		
		Customer customer = Customer.builder()
				.name(this.getName())
				.birthdate(this.getBirthdate())
				.documentNumber(this.getDocumentNumber())
				.documentType(this.getDocumentType())
				.address(address)
				.contacts(contacts)
				.inactive(this.getInactive()).build();
		
		return customer;
				
	}
	
	
}
