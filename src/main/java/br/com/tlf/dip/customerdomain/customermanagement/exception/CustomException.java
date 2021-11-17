package br.com.tlf.dip.customerdomain.customermanagement.exception;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class CustomException implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long timestamp; 
	private Integer status;
	private String error; 
	private String messege; 
	private String path;
	
	public CustomException(Long timestamp, Integer status, String error, String messege, String path) {
		super();
		this.timestamp = timestamp;
		this.status = status;
		this.error = error;
		this.messege = messege;
		this.path = path;
	}
	
	
	
}
