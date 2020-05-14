package com.bootcamp.microservice.cartservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bootcamp.microservice.cartservice.model.Product;
import com.bootcamp.microservice.cartservice.repository.ProductJpaRepository;

@Component
public class ProductService {
	
	@Autowired
	private ProductJpaRepository productRepository;
	
	public List<Product> getProducts(){
		return productRepository.findAll();
	}
	
    public void addProduct(Product product, String authToken) {
    	productRepository.save(toProduct(product,authToken));
    }
    
    public Product toProduct(Product product, String authToken) {
    	Product entity = new Product();
    	entity.setItemId(product.getItemId());
    	entity.setProductName(product.getProductName());
        entity.setProductType(product.getProductType());
        entity.setPrice(product.getPrice());
        entity.setAuthToken(authToken);
        return entity;
    }
    
}
