package com.springboot.microservice.ConvertCurrency;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

//@FeignClient(name="CurrencyConversionFactor", url="localhost:9000") 
@FeignClient(name="CurrencyConversionFactor")
@RibbonClient(name="CurrencyConversionFactor")
public interface CurrencyConversionFactorProxy {

@RequestMapping("/MS1/getConversionFactor/{fromCountryCode}") 
public ConvertCurrencyBean retrieveConversionFactor(@PathVariable("fromCountryCode") String fromCountryCode);

}
