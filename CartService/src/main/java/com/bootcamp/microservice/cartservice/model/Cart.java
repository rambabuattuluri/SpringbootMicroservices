package com.bootcamp.microservice.cartservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="cart")
public class Cart {
	
	@Id
	@GeneratedValue
	@Column(name="id")
	private int id;
	@Column(name="productName")
	private String productName;
	@Column(name="quantity")
	private int quantity;
	@Column(name="productPrice")
	private double productPrice;
	@Column(name="cartTotal")
	private double cartTotal;
	
	public Cart() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Cart(int id, String productName, int quantity, double productPrice, double cartTotal) {
		super();
		this.id = id;
		this.productName = productName;
		this.quantity = quantity;
		this.productPrice = productPrice;
		this.cartTotal = cartTotal;
	}

	@Override
	public String toString() {
		return "Cart [id=" + id + ", productName=" + productName + ", quantity=" + quantity + ", productPrice="
				+ productPrice + ", cartTotal=" + cartTotal + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}

	public double getCartTotal() {
		return cartTotal;
	}

	public void setCartTotal(double cartTotal) {
		this.cartTotal = cartTotal;
	}

}
