package br.com.tlf.dip.customerdomain.customermanagement.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.tlf.dip.customerdomain.customermanagement.model.Address;
import br.com.tlf.dip.customerdomain.customermanagement.model.ContactMedium;
import br.com.tlf.dip.customerdomain.customermanagement.model.Customer;
import br.com.tlf.dip.customerdomain.customermanagement.repository.AddressRepository;
import br.com.tlf.dip.customerdomain.customermanagement.repository.ContactMediumRepository;
import br.com.tlf.dip.customerdomain.customermanagement.repository.CustomerRepository;
import javassist.tools.rmi.ObjectNotFoundException;

@SpringBootTest
class CustomerManagementServiceTest {

	private final int ID = 1;

	@InjectMocks
	private CustomerManagementService service;
	
	@Mock
	private CustomerRepository customerRepository;
	
	@Mock
	private ContactMediumRepository contactMediumRepository;
	
	@Mock
	private AddressRepository addressRepository;
	
	private Customer customer;
	private Optional<Customer> optCustomer;
	
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);		
		startCustomer();
	}

	@Test
	void whenFindByIdThenReturnAListOfCustomers() {
		when(customerRepository.findAll()).thenReturn(List.of(customer));
		List<Customer> response = service.findAll();
		assertNotNull(response);
		assertEquals(1, response.size());
		assertEquals(Customer.class, response.get(0).getClass());
	}

	@Test
	void whenFindByIdThenReturnACustomerInstance() throws Exception {
		when(customerRepository.findById(anyInt())).thenReturn(optCustomer);
		Customer response = service.findById(ID);
		Assertions.assertEquals(Customer.class, response.getClass());
	}
	
	@Test
	void whenFindByIdThenReturnAExection()  {
		when(customerRepository.findById(anyInt())).thenReturn(Optional.empty());
		try {
			service.findById(ID);
		} catch (ObjectNotFoundException e) {
			Assertions.assertEquals(ObjectNotFoundException.class, e.getClass());
		}
		
	}

	@Test
	void whenInsertThenRetornSuccess() {
		when(customerRepository.save(any())).thenReturn(customer);
		Customer response = service.insert(customer);
		assertNotNull(response);
		assertEquals(Customer.class, response.getClass());
	}

	@Test
	void whenUpdateThenRetornSuccess() throws Exception {
		when(customerRepository.findById(anyInt())).thenReturn(optCustomer);
		when(customerRepository.save(any())).thenReturn(customer);
		service.update(ID, customer);
		verify(customerRepository, times(2)).save(any());
	}

	@Test
	void deleteWhithSuccess() throws Exception {
		when(customerRepository.findById(anyInt())).thenReturn(optCustomer);
		doNothing().when(customerRepository).delete(any());
		service.delete(ID);
		verify(customerRepository, times(1)).delete(any());		
	}

	private void startCustomer() {

		this.customer = Customer.builder()
							.id(ID)
							.name("Fulano de Tal")
							.birthdate(LocalDate.of(1980, 8, 12))
							.documentNumber("444444444444")
							.documentType("CPF")
							.address(Arrays.asList(Address.builder()
									.id(ID)
									.city("Vitoria")
									.country("BRA")
									.state("ES")
									.neighborhood("Jardim da Penha").street("Rua Sete de Setembro")
									.streetNumber("54")
									.creationDate(LocalDateTime.now())
									.lastDate(LocalDateTime.now())
									.customer(customer)
									.build()))
							.contacts(Arrays.asList(ContactMedium.builder()
									.id(ID)
									.customer(customer)
									.emailAddress("fulano@gmail.com")
									.phoneNumber("29332145687")
									.preferred(true)
									.build()))
							.build();
		
		optCustomer = Optional.of(customer);
	}
	
}