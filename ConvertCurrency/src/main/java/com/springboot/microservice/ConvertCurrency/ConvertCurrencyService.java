package com.springboot.microservice.ConvertCurrency;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Component
public class ConvertCurrencyService {
	
	@Autowired 
	RestTemplate template;
	
	@Autowired 
	CurrencyConversionFactorProxy proxy;
	
	@HystrixCommand(commandKey = "CurrencyConversionFactor", fallbackMethod = "CurrencyConversionFactorFallback")
	public ConvertCurrencyBean retrieveConversionFactor(String fromCountryCode) {
		ResponseEntity<ConvertCurrencyBean>responseEntity=template.getForEntity("http://104.197.76.172:9080/MS1/getConversionFactor/{fromCountryCode}", ConvertCurrencyBean.class, fromCountryCode);  
		ConvertCurrencyBean response=responseEntity.getBody();	
		return new ConvertCurrencyBean(response.getId(), response.getFromCountryCode(),response.getToCountryCode(),response.getConvertAmount(),response.getConversionFactor(),response.getConvertedAmount());
	}
	
	public ConvertCurrencyBean CurrencyConversionFactorFallback(String fromCountryCode) {
		System.out.println("CurrencyConversionFactor Service is down!!! fallback route enabled..."); 
       // return "CIRCUIT BREAKER ENABLED!!! No Response From CurrencyConversionFactor Service at this moment. " + " Service will be back shortly - " + new Date();
	    return new ConvertCurrencyBean(0L,"None","None", 0.0, 0.0, 0.0);
	}
	
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
