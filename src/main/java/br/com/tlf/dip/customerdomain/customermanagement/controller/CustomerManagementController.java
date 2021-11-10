package br.com.tlf.dip.customerdomain.customermanagement.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.tlf.dip.customerdomain.customermanagement.dto.in.CustomerInDTO;
import br.com.tlf.dip.customerdomain.customermanagement.dto.out.CustomerOutDTO;
import br.com.tlf.dip.customerdomain.customermanagement.model.Customer;
import br.com.tlf.dip.customerdomain.customermanagement.service.CustomerManagementService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@RestController
@RequestMapping( value = "/customermanagement")
public class CustomerManagementController {
	
	@Autowired
	private CustomerManagementService service;
	
	@GetMapping
	public ResponseEntity<List<CustomerOutDTO>> findAll() {
		
		List<Customer> customers = service.findAll();
		List<CustomerOutDTO> customerOutDTOs = customers.stream().map(c -> new CustomerOutDTO(c)).collect(Collectors.toList());
		
		return ResponseEntity.ok(customerOutDTOs);
		
	}
	
	@GetMapping(value = "/customer/{id}")
	public ResponseEntity<CustomerOutDTO> findById( @PathVariable Integer id ) throws Exception {		
		CustomerOutDTO outDTO = new CustomerOutDTO(service.findById(id));		
		return ResponseEntity.ok(outDTO);
		
	}
	
	
	@PostMapping
	@ApiOperation( "Cadastrar novo cliente" )
	@ApiResponses( value = { @ApiResponse( code = 201, message = "Cliente cadastrado com sucesso.", response = Void.class) } )
	public ResponseEntity<Void> insert( @RequestBody CustomerInDTO dto) throws Exception {		
		Customer customer = service.insert(dto.getCustomer());
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(customer.getId())
				.toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@PatchMapping( value = "/customer/{id}")
	public ResponseEntity<Void> update(@PathVariable Integer id,  @RequestBody(required = true) CustomerInDTO dto) throws Exception {		
		service.update(id, dto.getCustomer());
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping( value = "/{id}")
	public ResponseEntity<Void> delete( @PathVariable Integer id) throws Exception {
		service.delete(id);		
		return ResponseEntity.noContent().build();
	}

}
