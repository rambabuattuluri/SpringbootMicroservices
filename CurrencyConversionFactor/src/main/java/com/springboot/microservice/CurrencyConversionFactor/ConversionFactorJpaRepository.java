package com.springboot.microservice.CurrencyConversionFactor;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ConversionFactorJpaRepository extends JpaRepository<ConversionFactorBean, Long>{

	public ConversionFactorBean findByFromCountryCode(String fromCountryCode);
	
	public boolean existsByFromCountryCode(String fromCountryCode);

}


