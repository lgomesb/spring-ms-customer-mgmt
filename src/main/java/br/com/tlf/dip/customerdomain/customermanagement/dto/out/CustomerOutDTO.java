package br.com.tlf.dip.customerdomain.customermanagement.dto.out;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import br.com.tlf.dip.customerdomain.customermanagement.dto.AddressDTO;
import br.com.tlf.dip.customerdomain.customermanagement.dto.ContactMediumDTO;
import br.com.tlf.dip.customerdomain.customermanagement.model.Customer;
import lombok.Data;

@Data
public class CustomerOutDTO {

	private Integer id;	
	private String name;
	private LocalDate birthdate;
	private String documentNumber;
	private String documentType;	
	private LocalDate inactive;
	private List<AddressDTO> address;
	private List<ContactMediumDTO> contacts;
	
	public CustomerOutDTO(Customer customer) {

		this.id = customer.getId();
		this.name = customer.getName();
		this.birthdate = customer.getBirthdate();
		this.documentNumber = customer.getDocumentNumber();
		this.documentType = customer.getDocumentType();
		this.inactive = customer.getInactive();
		this.address = customer.getAddress().stream().map( a -> new AddressDTO(a)).collect(Collectors.toList());
		this.contacts = customer.getContacts().stream().map( c -> new ContactMediumDTO(c)).collect(Collectors.toList());
	}

}
