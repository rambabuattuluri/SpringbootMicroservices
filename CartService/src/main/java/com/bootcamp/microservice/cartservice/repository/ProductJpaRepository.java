package com.bootcamp.microservice.cartservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bootcamp.microservice.cartservice.model.Product;

public interface ProductJpaRepository extends JpaRepository<Product, Long>{
	
	public Product findByItemId(Long itemId);

}
