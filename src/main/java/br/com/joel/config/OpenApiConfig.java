package br.com.joel.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenApiConfig {
	
	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI()
				.info(new Info()
						.title("BOOKS STORE")
						.version("v1")
						.description("Book store where the admin user inserts, updates and deletes books.")
						.termsOfService("https://www.linkedin.com/in/joelmaciel-java-backend/")
						.license(new License()
								.name("Apache 2.0")
								.url("https://www.linkedin.com/in/joelmaciel-java-backend/")));
	}

}
