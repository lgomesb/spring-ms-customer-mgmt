package br.com.tlf.dip.customerdomain.customermanagement.service;

import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
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
		strartCustomer();
	}

	@Test
	void testFindAll() {
		fail("Not yet implemented");
	}

	@Test
	void whenFindByIdThenReturnACustomerInstance() {
		Mockito.when(customerRepository.findById(Mockito.anyInt())).thenReturn(optCustomer);
		Customer response;
		try {
			response = service.findById(ID);
			Assertions.assertEquals(Customer.class, response.getClass());
		} catch (ObjectNotFoundException e) {		
			Assertions.assertThrows(ObjectNotFoundException.class, () -> new ObjectNotFoundException(e.getMessage()));
		}
	}

	@Test
	void testInsert() {
		fail("Not yet implemented");
	}

	@Test
	void testUpdate() {
		fail("Not yet implemented");
	}

	@Test
	void testDelete() {
		fail("Not yet implemented");
	}

	private void strartCustomer() {

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