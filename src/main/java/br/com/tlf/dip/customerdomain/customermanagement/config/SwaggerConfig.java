package br.com.tlf.dip.customerdomain.customermanagement.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Response;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket api() {
		
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("br.com.tlf.dip.customerdomain.customermanagement.controller"))
				.paths(PathSelectors.regex("/customermanagement.*"))				
				.build()
				.globalResponses(HttpMethod.GET, this.getGlobalResponses())
				.apiInfo( metaInfo() );

	}
	
	private ApiInfo metaInfo() { 
		
		return new ApiInfoBuilder()
					.title("Customer Management")
					.description("MS para manter o cadastro de clientes")
					.version("1.0")
					.license("Terms of Service")				
					.licenseUrl("https://www.apache.org/license.html")				
					.contact(new Contact("Vivo", "http://www.vivo.com.br", "vivo@telefonica.com"))
					.build();        
	}	
	
	private List<Response> getGlobalResponses() {
		return Arrays.asList(this.getResponseDocumentation(HttpStatus.BAD_REQUEST),
				this.getResponseDocumentation(HttpStatus.UNAUTHORIZED),
				this.getResponseDocumentation(HttpStatus.FORBIDDEN),
				this.getResponseDocumentation(HttpStatus.NOT_FOUND),
				this.getResponseDocumentation(HttpStatus.INTERNAL_SERVER_ERROR),
				this.getResponseDocumentation(HttpStatus.GATEWAY_TIMEOUT));			
	}

	private Response getResponseDocumentation( HttpStatus httpStatus ) {
		
		return new ResponseBuilder()
			.code(Integer.toString(httpStatus.value()))
			.description(httpStatus.getReasonPhrase())
			.build();
	}
	
}
