package com.springboot.microservice.ConvertCurrency;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.springboot.microservice.ConvertCurrency"))
                .paths(regex("/MS2.*"))
                .build()
                .apiInfo(metaData());
    }
    
    
    @SuppressWarnings("deprecation")
	private ApiInfo metaData() {
    	ApiInfo apiInfo = new ApiInfoBuilder()
    			.title("Convert Currency - Spring Boot REST API")
    			.description("Spring Boot REST API for Convert Currency having multiple Endpoints like Get, exchange currency, etc..")
    			.termsOfServiceUrl("None")
    			.contact("Rambabu, Attuluri")
    			.version("1.0")
    			.build();
    	return apiInfo;
    }
}
