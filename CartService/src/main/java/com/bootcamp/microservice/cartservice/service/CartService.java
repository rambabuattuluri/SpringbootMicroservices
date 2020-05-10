package com.bootcamp.microservice.cartservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bootcamp.microservice.cartservice.model.Cart;
import com.bootcamp.microservice.cartservice.repository.CartJpaRepository;

@Component
public class CartService {
		
	@Autowired
	CartJpaRepository cartRepository;
	
	public List<Cart> getCartList(){
		return cartRepository.findAll();
	}
	
    public void addItem(int id, String productName, int quantity, double productPrice, double cartTotal) {
    	cartRepository.save(toCart(id,productName,quantity,productPrice,cartTotal));
    }
    
    public Cart toCart(int id, String productName, int quantity, double productPrice, double cartTotal) {
    	Cart entity = new Cart();
    	entity.setId(id);
    	entity.setProductName(productName);
    	entity.setQuantity(quantity);
        entity.setProductPrice(productPrice);
        entity.setCartTotal(cartTotal);
        return entity;
    }

}
