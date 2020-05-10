package com.bootcamp.microservice.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bootcamp.microservice.product.model.Product;


public interface ProductJpaRepository extends JpaRepository<Product, Long>{
	
	public Product findByItemId(Long itemId);

}
