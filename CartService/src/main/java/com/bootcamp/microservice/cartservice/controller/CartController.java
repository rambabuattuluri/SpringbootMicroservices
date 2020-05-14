package com.bootcamp.microservice.cartservice.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import com.bootcamp.microservice.cartservice.model.Cart;
import com.bootcamp.microservice.cartservice.model.Product;
import com.bootcamp.microservice.cartservice.service.CartService;
import com.bootcamp.microservice.cartservice.service.ProductService;

@SpringBootApplication
@RestController
public class CartController {
	
	private Logger logger=LoggerFactory.getLogger(this.getClass());
	
	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
	
	@Autowired
	RestTemplate restTemplate = new RestTemplate();
	
	@Autowired
	CartService cartService;
	
	@Autowired
	ProductService productService;
	
	@RequestMapping(value="/getCart/{authToken}", method=RequestMethod.GET)
	public List<Cart> getCart(@PathVariable("authToken") String authToken){
	  try {	
		Map<String, String>uriVariable=new HashMap<>();  
		uriVariable.put("authToken", authToken);
		ResponseEntity<Object>responseEntity=restTemplate.getForEntity("http://localhost:9999/oauth/check_token?token={authToken}", Object.class, uriVariable);  
		Object response=responseEntity.getStatusCode();
		System.out.println("authTokenValidatyResponse: "+response);
		logger.info("{}", response); 
		
		return cartService.getCartList();
		
	   } catch (HttpStatusCodeException ex) {
		    System.out.println(ex.getRawStatusCode());
		    System.out.println(ex.getStatusCode().toString());
		    System.out.println(ex.getResponseBodyAsString());
		    HttpHeaders headers = ex.getResponseHeaders();
		    System.out.println(headers.get("Content-Type"));
		    System.out.println(headers.get("Server"));
		    
		    return Collections.<Cart>emptyList();
	  }
	}
	
	@RequestMapping(value="/addItemToCart/{itemId}/{quantity}/{authToken}", method=RequestMethod.GET)
	public String AddItemToCart(@PathVariable("itemId") Long itemId,@PathVariable("quantity") int quantity, @PathVariable("authToken") String authToken) {
		Map<String, Long>uriVariables=new HashMap<>();  
		uriVariables.put("itemId", itemId);  
		//Get Product Details from product-service API Endpoint
		ResponseEntity<Product>responseEntity=restTemplate.getForEntity("http://localhost:9200/getProduct/{itemId}", Product.class, uriVariables);  
		Product response=responseEntity.getBody();	
		logger.info("{}", response); 
		
		productService.addProduct(response,authToken);
	try {
		Map<String, String>uriVariable=new HashMap<>();  
		uriVariable.put("authToken", authToken);
		ResponseEntity<Object>responseEntityAuth=restTemplate.getForEntity("http://localhost:9999/oauth/check_token?token={authToken}", Object.class, uriVariable);  
		Object authresponse=responseEntityAuth.getStatusCode().value();
		System.out.println("authTokenValidatyResponse: "+authresponse);
		logger.info("{}", authresponse); 

		cartService.addItem(0, response.productName, quantity, response.price, quantity*response.price);
		System.out.println("Product Item added to Cart successfully");
		return "Product Item added to Cart successfully";	
			
		} catch (HttpStatusCodeException ex) {
			    System.out.println(ex.getRawStatusCode());
			    System.out.println(ex.getStatusCode().toString());
			    System.out.println(ex.getResponseBodyAsString());
			    HttpHeaders headers = ex.getResponseHeaders();
			    System.out.println(headers.get("Content-Type"));
			    System.out.println(headers.get("Server"));
			    
			    System.out.println("Product is not added to Cart due to token validation failure!");
				return  "Product is not added to Cart due to token validation failure!" +"\n\n"+ ex.getResponseBodyAsString();		
         }
	}
	
	@RequestMapping(value="/getProducts/", method=RequestMethod.GET)
	public List<Product> getProducts(){
		return productService.getProducts();
	}

}
