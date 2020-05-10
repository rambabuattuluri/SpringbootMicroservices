package com.bootcamp.microservice.product.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bootcamp.microservice.product.model.Product;
import com.bootcamp.microservice.product.repository.ProductJpaRepository;

@Component
public class ProductService {

	@Autowired
	private ProductJpaRepository repository;
	
	public List<Product> getProducts(){
		return repository.findAll();
	}
	
    public Product getProduct(Long itemId) {
        return repository.findByItemId(itemId);
    }
    
    public boolean verifyProduct(Long itemId) {
        return repository.existsById(itemId);
    }
    
    public void addProduct(Product product) {
    	repository.save(toProduct(product));
    }
    
    public Product toProduct(Product product) {
    	Product entity = new Product();
    	entity.setItemId(product.getItemId());
    	entity.setProductName(product.getProductName());
        entity.setProductType(product.getProductType());
        entity.setPrice(product.getPrice());
        return entity;
    }
    
    public void updateProduct(Product product) {
    	Product entity = repository.findByItemId(product.getItemId());
    	entity.setProductName(product.getProductName());
        entity.setProductType(product.getProductType());
        entity.setPrice(product.getPrice());
        repository.save(entity);
    }
    
    public void delete(Long itemId) {
    	repository.deleteById(itemId);
    }
	
}
