package com.springboot.microservice.CurrencyConversionFactor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConversionFactorService {
	
	@Autowired
	private ConversionFactorJpaRepository repo;
		
	List<ConversionFactorBean> getAll()
	{
		return repo.findAll();
	}
	
    public ConversionFactorBean getConversionFactorById(Long id) {
        return repo.findById(id).get();
    }
    
    public ConversionFactorBean getConversionFactorByFromCountryCode(String fromCountryCode) {
        return repo.findByFromCountryCode(fromCountryCode);
    }
    
    public void addConversionFactor(ConversionFactorBean value) {
        repo.save(toConversionFactor(value));
    }
    
    public ConversionFactorBean toConversionFactor(ConversionFactorBean value) {
    	ConversionFactorBean entity = new ConversionFactorBean();
        entity.setFromCountryCode(value.getFromCountryCode());
        entity.setToCountryCode(value.getToCountryCode());
        entity.setConversionFactor(value.getConversionFactor());
        return entity;
    }
    
    public void updateConversionFactor(ConversionFactorBean value) {
    	ConversionFactorBean entity = repo.findByFromCountryCode(value.getFromCountryCode());
    	entity.setFromCountryCode(value.getFromCountryCode());
        entity.setConversionFactor(value.getConversionFactor());
        repo.save(entity);
    }
    
    public void delete(Long id) {
        repo.deleteById(id);
    }
	
}
