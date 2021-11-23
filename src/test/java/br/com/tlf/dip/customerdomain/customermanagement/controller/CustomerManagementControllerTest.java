package br.com.tlf.dip.customerdomain.customermanagement.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.tlf.dip.customerdomain.customermanagement.dto.in.CustomerInDTO;
import br.com.tlf.dip.customerdomain.customermanagement.dto.out.CustomerOutDTO;
import br.com.tlf.dip.customerdomain.customermanagement.model.Address;
import br.com.tlf.dip.customerdomain.customermanagement.model.ContactMedium;
import br.com.tlf.dip.customerdomain.customermanagement.model.Customer;
import br.com.tlf.dip.customerdomain.customermanagement.service.CustomerManagementService;

@SpringBootTest
class CustomerManagementControllerTest {
	
	private final Integer ID = 1;

	@InjectMocks
	private CustomerManagementController controller;

	@Mock
	private CustomerManagementService service;
	
	@Mock
	private ModelMapper mapper;

	private Customer customer;
	private CustomerInDTO customerInDTO;
	private CustomerOutDTO customerOutDTO;
	
	
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
		this.startCustomer();
	}

	@Test
	void whenFindAllThenReturnSuccess() {
		when(service.findAll()).thenReturn(List.of(customer));
		when(mapper.map(any(), any())).thenReturn(customerOutDTO);
		ResponseEntity<List<CustomerOutDTO>> response = controller.findAll();
		assertNotNull(response);
		assertNotNull(response.getBody());
		assertEquals(1, response.getBody().size());
	}

	@Test
	void whenFindByIdThenReturnSuccess() throws Exception {
		when(service.findById(anyInt())).thenReturn(customer);
		when(mapper.map(any(), any())).thenReturn(customerOutDTO);
		ResponseEntity<CustomerOutDTO> response = controller.findById(ID);
		assertNotNull(response);
		assertNotNull(response.getBody());
		assertEquals(CustomerOutDTO.class, response.getBody().getClass());
	}

	@Test
	void whenInsertThenReturnSuccess() throws Exception {
		when(service.insert(any())).thenReturn(customer);
		when(mapper.map(any(), any())).thenReturn(customer);
		ResponseEntity<Void> response = controller.insert(customerInDTO);
		assertNotNull(response);
		assertNotNull(response.getStatusCode());
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		
	}

	@Test
	void whenUpdateThenReturnSuccess() throws Exception {
		doNothing().when(service).update(anyInt(), any());
		ResponseEntity<Void> response = controller.update(ID, customerInDTO);
		assertNotNull(response);
		assertNotNull(response.getStatusCode());
		assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
	}

	@Test
	void whenDeleteThenReturnSuccess() throws Exception {
		doNothing().when(service).delete(anyInt());
		ResponseEntity<Void> response = controller.delete(ID);
		assertNotNull(response);
		assertNotNull(response.getStatusCode());
		assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
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
		
		ModelMapper modelMapper = new ModelMapper();
		this.customerInDTO = modelMapper.map(customer, CustomerInDTO.class);
		this.customerOutDTO =  modelMapper.map(customer, CustomerOutDTO.class);
		
	}
	
}
