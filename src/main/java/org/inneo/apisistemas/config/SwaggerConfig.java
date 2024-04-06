package org.inneo.apisistemas.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.License;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
	@Bean
	public OpenAPI openApi() {
		return new OpenAPI()
			.info(new Info().title("Services 1.0")
			.version("1.0")
			.description("API SIG SERVICES")					
			.contact(new Contact().name("inneo")
					.url("https://inneo.org")
					.email("suporte@inneo.org"))
			.license(new License()
					.name("Apache License Version 2.0")
					.url("https://www.apache.org/licenses/LICENSE-2.0.txt"))
			);
	}
}
