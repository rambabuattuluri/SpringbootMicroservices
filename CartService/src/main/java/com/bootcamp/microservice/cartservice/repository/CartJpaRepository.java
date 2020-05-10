package com.bootcamp.microservice.cartservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bootcamp.microservice.cartservice.model.Cart;

public interface CartJpaRepository extends JpaRepository<Cart, Integer>{

}
