package com.bootcamp.microservice.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bootcamp.microservice.product.model.Product;
import com.bootcamp.microservice.product.service.ProductService;

@SpringBootApplication
@RestController
public class ProductController {
	
	@Autowired
	ProductService productService;

	@RequestMapping(value = "/addProduct/{itemId}", method=RequestMethod.POST)
    public ResponseEntity<Object> addProduct(@PathVariable("itemId") Long itemId, @RequestBody Product product) {
		if(!productService.verifyProduct(itemId)){
		    productService.addProduct(product);
            return new ResponseEntity<>("New Product is added successfully", HttpStatus.OK);
		 }
		else{
		    return new ResponseEntity<>("Product already found in database", HttpStatus.FOUND);
		 }
    }
	
	@RequestMapping(value = "/updateProduct/{itemId}", method=RequestMethod.PUT)
    public ResponseEntity<Object> updateExistingConversionFactor(@PathVariable("itemId") Long itemId, @RequestBody Product product) {
		if(!productService.verifyProduct(itemId)) throw new ProductNotFoundException();
		productService.updateProduct(product);
        return new ResponseEntity<>("Product is updated successfully", HttpStatus.OK);
    }

	
	@RequestMapping(value = "/getProducts/", method=RequestMethod.GET) 
	public List<Product> getProducts(){ 
		return productService.getProducts();
	}
	
		
	@RequestMapping(value = "/getProduct/{itemId}", method=RequestMethod.GET)
    public Product getProduct(@PathVariable("itemId") Long itemId) {  
		if(!productService.verifyProduct(itemId)) throw new ProductNotFoundException();
        return productService.getProduct(itemId);
    }
	
	@RequestMapping(value = "/deleteProduct/{Id}", method=RequestMethod.DELETE)
    public void deleteConversionFactorById(@PathVariable("Id") Long id) {  
		productService.delete(id);
    }

}
