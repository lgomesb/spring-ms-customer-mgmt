package br.com.tlf.dip.customerdomain.customermanagement.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tlf.dip.customerdomain.customermanagement.model.Address;
import br.com.tlf.dip.customerdomain.customermanagement.model.ContactMedium;
import br.com.tlf.dip.customerdomain.customermanagement.model.Customer;
import br.com.tlf.dip.customerdomain.customermanagement.repository.AddressRepository;
import br.com.tlf.dip.customerdomain.customermanagement.repository.ContactMediumRepository;
import br.com.tlf.dip.customerdomain.customermanagement.repository.CustomerRepository;
import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class CustomerManagementService {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private ContactMediumRepository contactMediumRepository;
	
	@Autowired
	private AddressRepository addressRepository;
	
		
	public List<Customer> findAll() {
		
		return customerRepository.findAll();
	}	
	
	public Customer findById(Integer id) throws ObjectNotFoundException {
		
		return customerRepository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("Cliente com id " + id + " não encontrado"));
	} 
	
	public Customer insert(Customer customer) {		
		customer.setCreationDate(LocalDateTime.now());
		customer.setLastUpdate(LocalDateTime.now());
		customer.getAddress().forEach( a -> {a.setCustomer(customer); a.setCreationDate(LocalDateTime.now());} );
		customer.getContacts().forEach( c -> {c.setCustomer(customer); c.setCreationDate(LocalDateTime.now());} );
		
		return customerRepository.save(customer);
	}
	
	public void update(Integer id, Customer customer) throws Exception {
		Customer newCustomer = this.findById(id);
		newCustomer.setName(customer.getName());
		newCustomer.setBirthdate(customer.getBirthdate());
		newCustomer.setDocumentNumber(customer.getDocumentNumber());
		newCustomer.setDocumentType(customer.getDocumentType());
		newCustomer.setInactive(customer.getInactive());
		
		List<ContactMedium> contacts = newCustomer.getContacts().stream().map(c -> c).collect(Collectors.toList());
		List<Address> address = newCustomer.getAddress().stream().map(a -> a).collect(Collectors.toList());
		
		newCustomer.setAddress(null);
		newCustomer.setContacts(null);
		
		customerRepository.save(newCustomer);
		contactMediumRepository.deleteAll(contacts);
		addressRepository.deleteAll(address);
		
		newCustomer.setAddress(customer.getAddress());
		newCustomer.setContacts(customer.getContacts());
		
		newCustomer.getAddress().forEach( a -> {a.setCustomer(newCustomer); a.setCreationDate(LocalDateTime.now());} );
		newCustomer.getContacts().forEach( c -> {c.setCustomer(newCustomer); c.setCreationDate(LocalDateTime.now());} );
		newCustomer.setLastUpdate(LocalDateTime.now());		

		customerRepository.save(newCustomer);		
		
	}
	
	public void delete(Integer id) throws Exception { 
		
		Customer customer = this.findById(id);
		customerRepository.delete(customer);		
	}
	
}
