package br.com.tlf.dip.customerdomain.customermanagement.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
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
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@RestController
@RequestMapping( value = "/customerdomain/customermanagement/v1")
@Api( tags = "Customer")
public class CustomerManagementController {
	
	@Autowired
	private CustomerManagementService service;
	
	@Autowired
	private ModelMapper mapper;
	
	
	@GetMapping(value = "/customers")
	@ApiOperation( "Lista todos os clientes cadastrados" )
	@ApiResponses( value = { @ApiResponse( code = 200, message = "Sucesso.", response = CustomerOutDTO.class) , } )
	public ResponseEntity<List<CustomerOutDTO>> findAll() {
		List<Customer> customers = service.findAll();
		List<CustomerOutDTO> customerOutDTOs = customers.stream().map(c -> mapper.map(c, CustomerOutDTO.class)).collect(Collectors.toList());
		return ResponseEntity.ok(customerOutDTOs);
		
	}
	
	@GetMapping(value = "/customers/{id}")
	@ApiOperation( "Consultar cliente por ID" )
	@ApiResponses( value = { @ApiResponse( code = 200, message = "Sucesso.", response = CustomerOutDTO.class) } )	
	public ResponseEntity<CustomerOutDTO> findById( @PathVariable Integer id ) throws Exception {		
		Customer customer = service.findById(id);
		return ResponseEntity.ok(mapper.map(customer, CustomerOutDTO.class));
		
	}
	
	
	@PostMapping(value = "/customers")
	@ApiOperation( "Cadastrar novo cliente" )
	@ApiResponses( value = { @ApiResponse( code = 201, message = "Cliente cadastrado com sucesso.", response = Void.class) } )
	public ResponseEntity<Void> insert( @RequestBody CustomerInDTO customerDTO) throws Exception {		
		
		Customer customer = service.insert(mapper.map(customerDTO, Customer.class));
		
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(customer.getId())
				.toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@PatchMapping( value = "/customers/{id}")
	@ApiOperation( "Atualiza parcialmente um cliente" )
	@ApiResponses( value = { @ApiResponse( code = 204, message = "Cliente atualizado com sucesso.", response = Void.class) } )
	public ResponseEntity<Void> update(@PathVariable Integer id,  @RequestBody(required = true) CustomerInDTO customerDTO) throws Exception {		
		service.update(id, mapper.map(customerDTO, Customer.class));
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping( value = "/customers/{id}")
	@ApiOperation( "Deleta um cliente" )
	@ApiResponses( value = { @ApiResponse( code = 204, message = "Cliente excluido com sucesso.", response = Void.class) } )
	public ResponseEntity<Void> delete( @PathVariable Integer id) throws Exception {
		service.delete(id);		
		return ResponseEntity.noContent().build();
	}

}
