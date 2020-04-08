package com.springboot.microservice.CurrencyConversionFactor;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@SpringBootApplication
@RestController
//@RequestMapping(value="/ConversionFactor")
public class CurrencyConversionController {
	
	@Autowired
	ConversionFactorService conversionFactorService;
	@Autowired
	private ConversionFactorJpaRepository repo2;
	
	@RequestMapping(value = "/addConversionFactor/{fromCountryCode}", method=RequestMethod.POST)
    public ResponseEntity<Object> addNewConversionFactor(@PathVariable("fromCountryCode") String fromCountryCode, @RequestBody ConversionFactorBean value) {
		if(!repo2.existsByFromCountryCode(fromCountryCode)){
		    conversionFactorService.addConversionFactor(value);
            return new ResponseEntity<>("New ConversionFactor is added successfully", HttpStatus.OK);
		 }
		else{
		    return new ResponseEntity<>("ConversionFactor already found in database", HttpStatus.FOUND);
		 }
    }
	
	@RequestMapping(value = "/updateConversionFactor/{fromCountryCode}", method=RequestMethod.PUT)
    public ResponseEntity<Object> updateExistingConversionFactor(@PathVariable("fromCountryCode") String fromCountryCode, @RequestBody ConversionFactorBean value) {
		if(!repo2.existsByFromCountryCode(fromCountryCode)) throw new CountryNotFoundException();
		conversionFactorService.updateConversionFactor(value);
        return new ResponseEntity<>("Conversion Factor is updated successfully", HttpStatus.OK);
    }

	@RequestMapping(value = "/getAllConversionFactors/", method=RequestMethod.GET)
	public List<ConversionFactorBean> getAllConversionFactors()
	{
		return conversionFactorService.getAll();
	}
		
	@RequestMapping(value = "/getConversionFactor/fromCountryCode/{fromCountryCode}", method=RequestMethod.GET)
    public ConversionFactorBean findConversionFactorByFromCountryCode(@PathVariable("fromCountryCode") String fromCountryCode) {  
        return conversionFactorService.getConversionFactorByFromCountryCode(fromCountryCode);
    }
	
	@RequestMapping(value = "/getConversionFactor/id/{Id}", method=RequestMethod.DELETE)
    public void deleteConversionFactorById(@PathVariable("Id") Long id) {  
        conversionFactorService.delete(id);
    }
	/*
	 * @RequestMapping(path = "/getConversionFactor/id/{Id}") 
	 * public ConversionFactorValue findConversionFactorById(@PathVariable("Id") Long id) {
	 * return conversionFactorService.getConversionFactorById(id); }
	 */
	@RequestMapping(value = "/getConversionFactor/id/{Id}", method=RequestMethod.GET)
    public double findConversionFactorById(@PathVariable("Id") Long id) {  
        return conversionFactorService.getConversionFactorById(id).getConversionFactor();
    }

}
