package com.buri.restfulservice.common.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

	@Bean
	public OpenAPI openAPI() {
		final Contact DEFAULT_CONTACT = new Contact().name("kimbuhee")
													 .email("kimbuhui@naver.com");

		Info info = new Info().title("Spring Boot Restful API service")
							  .version("1.0")
							  .contact(DEFAULT_CONTACT)
							  .description("스프링 부터 Restful API 테스트 입니다.");

		return new OpenAPI()
			.components(new Components())
			.info(info);
	}
}
