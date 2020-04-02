package com.springboot.microservice.CurrencyConversionFactor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CurrencyConversionFactorApplication {

	public static void main(String[] args) {
		SpringApplication.run(CurrencyConversionFactorApplication.class, args);
	}
	
	@Autowired
	ConversionFactorJpaRepository repo;
	@Bean
	CommandLineRunner dlr(ApplicationContext ctx) {
		return s -> {

			ConversionFactorBean  value = new ConversionFactorBean(1L,"USD","IND",74.56);
			repo.save(value);
			
			value = new ConversionFactorBean(2L,"CAD", "IND", 53.39);
			repo.save(value);
			
			value = new ConversionFactorBean(3L, "EUR", "IND", 83.68);
			repo.save(value);
			
			value = new ConversionFactorBean(4L, "NZD", "IND", 45.23);
			repo.save(value);
			
			value = new ConversionFactorBean(5L, "SGD", "IND", 52.44);
			repo.save(value);
		};

	}

}
