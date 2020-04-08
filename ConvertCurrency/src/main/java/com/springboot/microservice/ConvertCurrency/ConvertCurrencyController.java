package com.springboot.microservice.ConvertCurrency;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.swagger.annotations.ApiOperation;

@SpringBootApplication
@RestController
@RequestMapping(value="/ConvertCurrency")
public class ConvertCurrencyController {
	
	private static Map<String, ConvertCurrencyBean> repo = new HashMap<>();
	static {
	     ConvertCurrencyBean bean = new ConvertCurrencyBean();
	     
	     bean.setId(1L);
	     bean.setFromCountryCode("USD");
	     bean.setToCountryCode("IND");
	     bean.setConversionFactor(74.78);
	     bean.setConvertAmount(1);
	     bean.setConvertedAmount(74.78);
	     
	     repo.put(bean.getFromCountryCode(), bean);
	     
	     ConvertCurrencyBean bean2 = new ConvertCurrencyBean();
	     
	     bean2.setId(2L);
	     bean2.setFromCountryCode("CAD");
	     bean2.setToCountryCode("IND");
	     bean2.setConversionFactor(54.45);
	     bean2.setConvertAmount(1);
	     bean2.setConvertedAmount(54.45);
	     
	     repo.put(bean2.getFromCountryCode(), bean2);
	     
	     ConvertCurrencyBean bean3 = new ConvertCurrencyBean();
	     
	     bean3.setId(3L);
	     bean3.setFromCountryCode("EUR");
	     bean3.setToCountryCode("IND");
	     bean3.setConversionFactor(87.33);
	     bean3.setConvertAmount(1);
	     bean3.setConvertedAmount(87.33);
	     
	     repo.put(bean3.getFromCountryCode(), bean3);
	  
	   }
	
	@ApiOperation(value = "Get Converted Amount", response = Iterable.class)
	
	@RequestMapping(value="/convertCurrency/from/{fromCountryCode}/amount/{convertAmount}",method=RequestMethod.GET)
    public ResponseEntity<Object> convertCurrency(@PathVariable("fromCountryCode") String fromCountryCode, @PathVariable("convertAmount") double convertAmount) {
        System.out.println("fromCountryCode: " + fromCountryCode);
        System.out.println("convertAmont: " + convertAmount);
        if(repo.containsKey(fromCountryCode)) {
         ConvertCurrencyBean value = repo.get(fromCountryCode);
         value.setConvertedAmount(value.getConversionFactor()*convertAmount);
         return new ResponseEntity<>("Converted Amount: " + value.getConvertedAmount() , HttpStatus.OK);
        }
        else { 
        return new ResponseEntity<>("Input CountryCode is not Found " , HttpStatus.FOUND);   
	    }
	}
	
	//Using Rest Template 
	@ApiOperation(value = "Get Converted Amount using Rest Template", response = Iterable.class)
	@RequestMapping(value="/convertCurrency/v2/from/{fromCountryCode}/amount/{convertAmount}", method=RequestMethod.GET)
    public ConvertCurrencyBean convertCurrencyv2(@PathVariable("fromCountryCode") String fromCountryCode, @PathVariable("convertAmount") double convertAmount) {
		Map<String, String>uriVariables=new HashMap<>();  
		uriVariables.put("fromCountryCode", fromCountryCode);  
		ResponseEntity<ConvertCurrencyBean>responseEntity=new RestTemplate().getForEntity("http://localhost:9000/getConversionFactor/fromCountryCode/{fromCountryCode}", ConvertCurrencyBean.class, uriVariables);  
		ConvertCurrencyBean response=responseEntity.getBody();	
		return new ConvertCurrencyBean(response.getId(), response.getFromCountryCode(),response.getToCountryCode(),response.getConversionFactor(),convertAmount,convertAmount*(response.getConversionFactor()));
	}

	//Using Feign Client 
	@Autowired 
	CurrencyConversionFactorProxy proxy;
	@RequestMapping(value="/convertCurrency/v3/from/{fromCountryCode}/amount/{convertAmount}", method=RequestMethod.GET)
    public ConvertCurrencyBean convertCurrencyFeign(@PathVariable("fromCountryCode") String fromCountryCode, @PathVariable("convertAmount") double convertAmount) {
		ConvertCurrencyBean response=proxy.retrieveConversionFactor(fromCountryCode);	
		return new ConvertCurrencyBean(response.getId(), response.getFromCountryCode(),response.getToCountryCode(),response.getConversionFactor(),convertAmount,convertAmount*(response.getConversionFactor()));
	}

	//Using Hytrix Circuit Breaker & Hytrix Dashboard  
	@Autowired 
	ConvertCurrencyService service;
	@RequestMapping(value="/convertCurrency/v4/from/{fromCountryCode}/amount/{convertAmount}", method=RequestMethod.GET)
    public ConvertCurrencyBean convertCurrencyHytrix(@PathVariable("fromCountryCode") String fromCountryCode, @PathVariable("convertAmount") double convertAmount) {
		ConvertCurrencyBean response=service.retrieveConversionFactor(fromCountryCode);	
		return new ConvertCurrencyBean(response.getId(), fromCountryCode,response.getToCountryCode(),convertAmount,response.getConversionFactor(),convertAmount*(response.getConversionFactor()));
	}
	
}
