package br.com.tlf.dip.customerdomain.customermanagement.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javassist.tools.rmi.ObjectNotFoundException;

@ControllerAdvice
public class CustomermanagementExceptionHandler {

	@ExceptionHandler( ObjectNotFoundException.class )
	public ResponseEntity<CustomException> objectNotFound( ObjectNotFoundException e, HttpServletRequest request) { 
		CustomException err = new CustomException(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(),  "Cliente nao encontrado",  e.getMessage(), request.getRequestURI()); 
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err); 
	}
	
}
